# ═══════════════════════════════════════════════════════════════════════
#  start.ps1 — JavaBot AI Tutor · Fully Automatic Smart Launcher (Windows)
#  Detects specs → picks model → installs Ollama → finds port → opens browser
# ═══════════════════════════════════════════════════════════════════════
#Requires -Version 5.1

Set-Location -Path $PSScriptRoot
$ErrorActionPreference = "Stop"

function hdr($t)  { Write-Host ""; Write-Host "  ── $t ──" -ForegroundColor Cyan }
function ok($t)   { Write-Host "  ✓ $t" -ForegroundColor Green }
function warn($t) { Write-Host "  ⚠ $t" -ForegroundColor Yellow }
function info($t) { Write-Host "  → $t" -ForegroundColor White }
function fail($t) { Write-Host "  ✗ $t" -ForegroundColor Red }

Write-Host ""
Write-Host "  ╔══════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "  ║   🤖  JavaBot AI Tutor — Smart Launcher          ║" -ForegroundColor Cyan
Write-Host "  ╚══════════════════════════════════════════════════╝" -ForegroundColor Cyan

# ════════════════════════════════════════════════════════════════════════
#  1 — DETECT SYSTEM SPECS
# ════════════════════════════════════════════════════════════════════════
hdr "Detecting System Specifications"

# RAM
$cs       = Get-CimInstance Win32_ComputerSystem
$os       = Get-CimInstance Win32_OperatingSystem
$totalRam = [math]::Round($cs.TotalPhysicalMemory / 1MB)
$freeRam  = [math]::Round($os.FreePhysicalMemory / 1KB)

# CPU
$cpu      = Get-CimInstance Win32_Processor | Select-Object -First 1
$cpuName  = $cpu.Name
$cpuCores = (Get-CimInstance Win32_Processor | Measure-Object NumberOfLogicalProcessors -Sum).Sum

# GPU
$gpuName  = "None"
$gpuVram  = 0
$hasGpu   = $false
try {
  $gpu = Get-CimInstance Win32_VideoController | Where-Object { $_.AdapterRAM -gt 0 } | Select-Object -First 1
  if ($gpu) {
    $gpuName = $gpu.Name
    $gpuVram = [math]::Round($gpu.AdapterRAM / 1MB)
    if ($gpuVram -gt 500) { $hasGpu = $true }
  }
} catch {}

Write-Host ""
Write-Host ("  {0,-20} {1}" -f "OS:", "Windows $([System.Environment]::OSVersion.Version)")
Write-Host ("  {0,-20} {1} ({2} logical)" -f "CPU:", $cpuName.Trim(), $cpuCores)
Write-Host ("  {0,-20} {1} MB total / {2} MB free" -f "RAM:", $totalRam, $freeRam)
Write-Host ("  {0,-20} {1}" -f "GPU:", $gpuName)
if ($hasGpu) { Write-Host ("  {0,-20} {1} MB" -f "GPU VRAM:", $gpuVram) }
Write-Host ""

# ════════════════════════════════════════════════════════════════════════
#  2 — SELECT MODEL
# ════════════════════════════════════════════════════════════════════════
hdr "Selecting Optimal Model"

$stateFile = Join-Path $PSScriptRoot ".tutor-state"
$savedModel = ""
if (Test-Path $stateFile) {
  $savedModel = (Get-Content $stateFile | Where-Object { $_ -match "^MODEL=" }) -replace "^MODEL=", ""
}

if ($savedModel) {
  $selectedModel = $savedModel
  info "Using saved model preference: $selectedModel"
} elseif ($hasGpu -and $gpuVram -ge 6000) {
  $selectedModel = "qwen2.5-coder:7b"
  info "GPU with ${gpuVram}MB VRAM — using 7B model"
} elseif ($freeRam -ge 5000 -or ($hasGpu -and $gpuVram -ge 2000)) {
  $selectedModel = "qwen2.5-coder:1.5b"
} elseif ($freeRam -ge 2500) {
  $selectedModel = "qwen2.5-coder:1.5b"
} else {
  $selectedModel = "tinyllama"
  warn "Low RAM detected (${freeRam}MB free). Using TinyLlama."
}

$ollamaThreads = [math]::Max(1, $cpuCores - 2)

$modelLabel = switch ($selectedModel) {
  "qwen2.5-coder:7b"  { "Qwen2.5-Coder-7B  · Best quality   · ~4.5GB RAM" }
  "qwen2.5-coder:1.5b"{ "Qwen2.5-Coder-1.5B · Great quality · ~1.2GB RAM" }
  "tinyllama"         { "TinyLlama-1.1B     · Lightweight   · ~700MB RAM" }
  default             { $selectedModel }
}
ok "Model selected: $modelLabel"

# ════════════════════════════════════════════════════════════════════════
#  3 — INSTALL OLLAMA
# ════════════════════════════════════════════════════════════════════════
hdr "Checking Ollama"

if (Get-Command ollama -ErrorAction SilentlyContinue) {
  $ollamaVer = (ollama --version 2>&1) | Select-Object -First 1
  ok "Ollama installed: $ollamaVer"
} else {
  info "Installing Ollama..."
  $installer = "$env:TEMP\OllamaSetup.exe"
  try {
    Invoke-WebRequest -Uri "https://ollama.ai/download/OllamaSetup.exe" `
      -OutFile $installer -UseBasicParsing
    Start-Process -FilePath $installer -ArgumentList "/S" -Wait
    # Refresh PATH
    $env:PATH = [System.Environment]::GetEnvironmentVariable("PATH","Machine") + ";" +
                [System.Environment]::GetEnvironmentVariable("PATH","User")
    ok "Ollama installed"
  } catch {
    fail "Ollama install failed: $_"
    Write-Host "  Install manually: https://ollama.ai/download" -ForegroundColor Yellow
    pause; exit 1
  }
}

# ════════════════════════════════════════════════════════════════════════
#  4 — START OLLAMA SERVICE
# ════════════════════════════════════════════════════════════════════════
hdr "Starting Ollama Service"

$ollamaPort = 11434
$ollamaApi  = "http://localhost:$ollamaPort"

function Test-OllamaReady {
  try {
    Invoke-WebRequest -Uri "$ollamaApi/api/tags" -UseBasicParsing -TimeoutSec 2 | Out-Null
    return $true
  } catch { return $false }
}

if (Test-OllamaReady) {
  ok "Ollama already running on port $ollamaPort"
} else {
  info "Starting Ollama service (threads: $ollamaThreads)..."
  $env:OLLAMA_NUM_THREAD = $ollamaThreads
  $env:OLLAMA_NUM_PARALLEL = 1
  Start-Process "ollama" -ArgumentList "serve" -WindowStyle Hidden
  $ready = $false
  for ($i = 0; $i -lt 15; $i++) {
    Start-Sleep 1
    if (Test-OllamaReady) { $ready = $true; break }
  }
  if ($ready) { ok "Ollama service ready" }
  else { fail "Ollama did not start. Try running 'ollama serve' manually."; pause; exit 1 }
}

# ════════════════════════════════════════════════════════════════════════
#  5 — PULL MODEL IF NEEDED
# ════════════════════════════════════════════════════════════════════════
hdr "Model Check"

$modelBase = $selectedModel.Split(":")[0]
$tags = (Invoke-WebRequest -Uri "$ollamaApi/api/tags" -UseBasicParsing).Content | ConvertFrom-Json
$modelExists = $tags.models | Where-Object { $_.name -like "$modelBase*" }

if ($modelExists) {
  ok "Model already downloaded: $selectedModel"
} else {
  info "Pulling $selectedModel (first-time download, please wait)..."
  $pullResult = & ollama pull $selectedModel 2>&1
  if ($LASTEXITCODE -eq 0) {
    ok "Model ready: $selectedModel"
  } else {
    warn "Failed to pull $selectedModel. Trying tinyllama..."
    & ollama pull tinyllama 2>&1 | Out-Null
    if ($LASTEXITCODE -eq 0) { $selectedModel = "tinyllama"; ok "Fallback model ready: tinyllama" }
    else { fail "Could not pull any model."; pause; exit 1 }
  }
}

"MODEL=$selectedModel" | Set-Content $stateFile

# ════════════════════════════════════════════════════════════════════════
#  6 — FIND FREE PORT
# ════════════════════════════════════════════════════════════════════════
hdr "Starting Web Server"

function Find-FreePort {
  $candidates = @(8080, 8000, 3000, 3001, 8081, 9000, 5000, 5500)
  foreach ($p in $candidates) {
    $tcp = New-Object System.Net.Sockets.TcpClient
    try {
      $tcp.Connect("localhost", $p)
      $tcp.Close()
      # Port is in use, try next
    } catch {
      return $p  # Port is free
    }
  }
  # Fallback: let OS pick
  $s = New-Object System.Net.Sockets.TcpListener([System.Net.IPAddress]::Loopback, 0)
  $s.Start(); $p = $s.LocalEndpoint.Port; $s.Stop()
  return $p
}

$webPort = Find-FreePort
$webUrl  = "http://localhost:$webPort"
ok "Web server port: $webPort"

# ════════════════════════════════════════════════════════════════════════
#  7 — WRITE DYNAMIC CONFIG
# ════════════════════════════════════════════════════════════════════════

$configContent = @"
/* Auto-generated by start.ps1 -- do not edit */
window.TUTOR_CONFIG = {
  ollamaUrl:      "http://localhost:$ollamaPort",
  preferredModel: "$selectedModel",
  webPort:        $webPort,
  generatedAt:    "$(Get-Date -Format 'o')",
  systemInfo: {
    platform:  "windows",
    ramMb:     $totalRam,
    cpuCores:  $cpuCores,
    hasGpu:    $($hasGpu.ToString().ToLower()),
    gpuName:   "$gpuName"
  }
};
"@
$configContent | Set-Content -Path (Join-Path $PSScriptRoot "js\tutor-config.js") -Encoding UTF8
ok "Dynamic config written to js/tutor-config.js"

# ════════════════════════════════════════════════════════════════════════
#  8 — START WEB SERVER
# ════════════════════════════════════════════════════════════════════════

$serverName = ""
$serverJob  = $null

if (Get-Command python3 -ErrorAction SilentlyContinue) {
  $serverName = "Python 3"
  $serverJob  = Start-Job -ScriptBlock {
    param($d, $p) Set-Location $d; python3 -m http.server $p
  } -ArgumentList $PSScriptRoot, $webPort
} elseif (Get-Command python -ErrorAction SilentlyContinue) {
  $serverName = "Python"
  $serverJob  = Start-Job -ScriptBlock {
    param($d, $p) Set-Location $d; python -m http.server $p
  } -ArgumentList $PSScriptRoot, $webPort
} elseif (Get-Command node -ErrorAction SilentlyContinue) {
  $serverName = "Node.js"
  $serverJob  = Start-Job -ScriptBlock {
    param($d, $p) Set-Location $d; npx -y serve . -p $p
  } -ArgumentList $PSScriptRoot, $webPort
} else {
  fail "No web server found. Install Python 3 from https://python.org"
  pause; exit 1
}

Start-Sleep 1
ok "Web server started: $serverName on port $webPort"

# ════════════════════════════════════════════════════════════════════════
#  9 — OPEN BROWSER
# ════════════════════════════════════════════════════════════════════════

Start-Sleep 1
Start-Process "$webUrl/tutor.html"

# ════════════════════════════════════════════════════════════════════════
#  10 — READY SUMMARY
# ════════════════════════════════════════════════════════════════════════

Write-Host ""
Write-Host "  ╔══════════════════════════════════════════════════╗" -ForegroundColor Green
Write-Host "  ║   ✅  JavaBot AI Tutor is READY!                 ║" -ForegroundColor Green
Write-Host "  ╚══════════════════════════════════════════════════╝" -ForegroundColor Green
Write-Host ""
Write-Host ("  {0,-22} {1}" -f "🌐 AI Tutor:",       "$webUrl/tutor.html")
Write-Host ("  {0,-22} {1}" -f "📚 DSA Hub:",        "$webUrl/index.html")
Write-Host ("  {0,-22} {1}" -f "🤖 AI Model:",       $selectedModel)
Write-Host ("  {0,-22} {1}" -f "⚡ Ollama API:",     $ollamaApi)
Write-Host ("  {0,-22} {1} MB / {2} MB free" -f "🧠 RAM:", $totalRam, $freeRam)
Write-Host ("  {0,-22} {1}" -f "🖥  GPU:", $gpuName)
Write-Host ""
Write-Host "  Press Ctrl+C to stop the web server." -ForegroundColor DarkGray
Write-Host ""

# Keep alive
try {
  while ($true) {
    Start-Sleep 2
    # Restart server if it crashed
    if ($serverJob.State -eq 'Completed' -or $serverJob.State -eq 'Failed') {
      warn "Web server stopped unexpectedly. Restarting..."
      $serverJob = Start-Job -ScriptBlock {
        param($d, $p) Set-Location $d; python3 -m http.server $p
      } -ArgumentList $PSScriptRoot, $webPort
    }
  }
} finally {
  Stop-Job $serverJob -ErrorAction SilentlyContinue
  Remove-Job $serverJob -ErrorAction SilentlyContinue
  Write-Host "  Web server stopped." -ForegroundColor Yellow
}
