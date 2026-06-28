#!/usr/bin/env bash
# ═══════════════════════════════════════════════════════════════════════
#  start.sh — JavaBot AI Tutor · Fully Automatic Launcher
#  Detects system specs → picks best model → starts everything → opens browser
#  Works on Linux & macOS · No manual steps required
# ═══════════════════════════════════════════════════════════════════════
set -euo pipefail

# ── Colors ──────────────────────────────────────────────────────────────
R='\033[0;31m' G='\033[0;32m' Y='\033[1;33m'
C='\033[0;36m' B='\033[1m'   D='\033[2m' NC='\033[0m'

ok()   { echo -e "  ${G}✓${NC} $*"; }
warn() { echo -e "  ${Y}⚠${NC} $*"; }
info() { echo -e "  ${C}→${NC} $*"; }
fail() { echo -e "  ${R}✗${NC} $*"; }
hdr()  { echo -e "\n${C}${B}── $* ──${NC}"; }

# ── Change to script directory ───────────────────────────────────────────
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# ── State file (persists config across runs) ─────────────────────────────
STATE_FILE="$SCRIPT_DIR/.tutor-state"
OLLAMA_PID_FILE="$SCRIPT_DIR/.ollama.pid"

echo ""
echo -e "${C}${B}╔══════════════════════════════════════════════════╗${NC}"
echo -e "${C}${B}║   🤖  JavaBot AI Tutor — Smart Launcher          ║${NC}"
echo -e "${C}${B}╚══════════════════════════════════════════════════╝${NC}"

# ════════════════════════════════════════════════════════════════════════
#  SECTION 1 — DETECT SYSTEM SPECIFICATIONS
# ════════════════════════════════════════════════════════════════════════
hdr "Detecting System Specifications"

OS="$(uname -s)"
ARCH="$(uname -m)"

# ── RAM detection ────────────────────────────────────────────────────────
case "$OS" in
  Linux*)
    PLATFORM="linux"
    TOTAL_RAM_MB=$(awk '/^MemTotal:/{print int($2/1024)}' /proc/meminfo)
    FREE_RAM_MB=$(awk '/^MemAvailable:/{print int($2/1024)}' /proc/meminfo)
    CPU_CORES=$(nproc 2>/dev/null || grep -c ^processor /proc/cpuinfo)
    ;;
  Darwin*)
    PLATFORM="mac"
    TOTAL_RAM_MB=$(( $(sysctl -n hw.memsize) / 1024 / 1024 ))
    FREE_RAM_MB=$(( $(vm_stat | awk '/Pages free/{gsub(/\./,"",$3); print $3}') * 4096 / 1024 / 1024 ))
    CPU_CORES=$(sysctl -n hw.logicalcpu)
    ;;
  *)
    fail "Unsupported OS: $OS. Use setup-tutor.ps1 on Windows."
    exit 1
    ;;
esac

# ── GPU detection (optional, for Ollama GPU offload) ─────────────────────
GPU_NAME="None"
GPU_VRAM_MB=0
HAS_GPU=false

if command -v nvidia-smi &>/dev/null; then
  GPU_NAME=$(nvidia-smi --query-gpu=name --format=csv,noheader 2>/dev/null | head -1 || echo "")
  GPU_VRAM_MB=$(nvidia-smi --query-gpu=memory.total --format=csv,noheader,nounits 2>/dev/null | head -1 || echo "0")
  [[ -n "$GPU_NAME" && "$GPU_VRAM_MB" -gt 0 ]] && HAS_GPU=true
elif [[ "$PLATFORM" == "mac" ]] && [[ "$ARCH" == "arm64" ]]; then
  GPU_NAME="Apple Silicon (Unified Memory)"
  HAS_GPU=true  # M1/M2/M3 — Metal acceleration
fi

# ── Print detected specs ──────────────────────────────────────────────────
echo ""
printf "  %-18s %s\n" "OS:"       "$OS ($ARCH)"
printf "  %-18s %s\n" "CPU Cores:"  "$CPU_CORES"
printf "  %-18s %s MB total / %s MB free\n" "RAM:" "$TOTAL_RAM_MB" "$FREE_RAM_MB"
printf "  %-18s %s\n" "GPU:" "$GPU_NAME"
[[ "$HAS_GPU" == true && "$GPU_VRAM_MB" -gt 0 ]] && \
  printf "  %-18s %s MB\n" "GPU VRAM:" "$GPU_VRAM_MB"
echo ""

# ════════════════════════════════════════════════════════════════════════
#  SECTION 2 — MODEL SELECTION (based on available RAM)
# ════════════════════════════════════════════════════════════════════════
hdr "Selecting Optimal Model"

# Model tiers (RAM required at runtime, including OS overhead)
select_model() {
  local ram=$1
  if   [[ $ram -ge 7000 ]]; then echo "qwen2.5-coder:7b"
  elif [[ $ram -ge 3500 ]]; then echo "qwen2.5-coder:1.5b"
  else                           echo "tinyllama"
  fi
}

select_model_label() {
  case "$1" in
    "qwen2.5-coder:7b")   echo "Qwen2.5-Coder-7B  · Best quality · ~4.5GB RAM" ;;
    "qwen2.5-coder:1.5b") echo "Qwen2.5-Coder-1.5B · Great quality · ~1.2GB RAM" ;;
    "tinyllama")           echo "TinyLlama-1.1B     · Lightweight · ~700MB RAM" ;;
    *)                     echo "$1" ;;
  esac
}

# Use free RAM for model selection (be conservative — leave 1.5GB headroom)
EFFECTIVE_RAM=$(( FREE_RAM_MB - 1500 ))
[[ $EFFECTIVE_RAM -lt 0 ]] && EFFECTIVE_RAM=0

# If GPU is available and has enough VRAM, we can use a larger model
if [[ "$HAS_GPU" == true && "$GPU_VRAM_MB" -ge 6000 ]]; then
  SELECTED_MODEL="qwen2.5-coder:7b"
  info "GPU detected with ${GPU_VRAM_MB}MB VRAM — upgrading to 7B model"
elif [[ "$HAS_GPU" == true && "$GPU_VRAM_MB" -ge 2000 ]]; then
  SELECTED_MODEL="qwen2.5-coder:1.5b"
  info "GPU detected — using 1.5B with GPU acceleration"
else
  SELECTED_MODEL=$(select_model "$EFFECTIVE_RAM")
fi

# Override from state file if user previously chose manually
if [[ -f "$STATE_FILE" ]]; then
  SAVED_MODEL=$(grep "^MODEL=" "$STATE_FILE" 2>/dev/null | cut -d= -f2 || echo "")
  if [[ -n "$SAVED_MODEL" ]]; then
    SELECTED_MODEL="$SAVED_MODEL"
    info "Using previously saved model preference: $SELECTED_MODEL"
  fi
fi

ok "Selected model: $(select_model_label "$SELECTED_MODEL")"
echo ""

# ── Determine Ollama thread count based on CPU ────────────────────────────
OLLAMA_THREADS=$(( CPU_CORES > 4 ? CPU_CORES - 2 : CPU_CORES ))
export OLLAMA_NUM_PARALLEL=1
export OLLAMA_MAX_LOADED_MODELS=1

# ════════════════════════════════════════════════════════════════════════
#  SECTION 3 — INSTALL OLLAMA (if not present)
# ════════════════════════════════════════════════════════════════════════
hdr "Checking Ollama"

if command -v ollama &>/dev/null; then
  OLLAMA_VER=$(ollama --version 2>/dev/null | head -1 || echo "installed")
  ok "Ollama already installed ($OLLAMA_VER)"
else
  info "Ollama not found. Installing..."
  if curl -fsSL https://ollama.ai/install.sh | sh 2>&1 | tail -5; then
    ok "Ollama installed successfully"
  else
    fail "Ollama install failed."
    echo -e "\n  ${Y}Manual install:${NC} https://ollama.ai"
    exit 1
  fi
fi

# ════════════════════════════════════════════════════════════════════════
#  SECTION 4 — START OLLAMA SERVICE
# ════════════════════════════════════════════════════════════════════════
hdr "Starting Ollama Service"

OLLAMA_PORT=11434

start_ollama_service() {
  info "Starting Ollama service (threads: $OLLAMA_THREADS)..."
  OLLAMA_NUM_THREAD=$OLLAMA_THREADS ollama serve > /tmp/ollama-javabot.log 2>&1 &
  echo $! > "$OLLAMA_PID_FILE"
  # Wait up to 12s for service to be ready
  for i in {1..12}; do
    sleep 1
    if curl -sf "http://localhost:${OLLAMA_PORT}/api/tags" &>/dev/null; then
      ok "Ollama service ready (PID: $(cat $OLLAMA_PID_FILE))"
      return 0
    fi
  done
  fail "Ollama service did not start in time. Check /tmp/ollama-javabot.log"
  return 1
}

if curl -sf "http://localhost:${OLLAMA_PORT}/api/tags" &>/dev/null; then
  ok "Ollama already running on port ${OLLAMA_PORT}"
else
  start_ollama_service
fi

# ════════════════════════════════════════════════════════════════════════
#  SECTION 5 — PULL MODEL (if not already downloaded)
# ════════════════════════════════════════════════════════════════════════
hdr "Model Check"

MODEL_BASE="${SELECTED_MODEL%%:*}"
MODEL_EXISTS=$(curl -sf "http://localhost:${OLLAMA_PORT}/api/tags" \
  | python3 -c "import sys,json; m=json.load(sys.stdin).get('models',[]); print('yes' if any('$MODEL_BASE' in x['name'] for x in m) else 'no')" 2>/dev/null || echo "no")

if [[ "$MODEL_EXISTS" == "yes" ]]; then
  ok "Model $SELECTED_MODEL already downloaded"
else
  info "Pulling $SELECTED_MODEL (this takes a few minutes on first run)..."
  echo ""
  if ollama pull "$SELECTED_MODEL"; then
    ok "Model downloaded: $SELECTED_MODEL"
  else
    warn "Failed to pull $SELECTED_MODEL. Trying tinyllama as fallback..."
    if ollama pull tinyllama; then
      SELECTED_MODEL="tinyllama"
      ok "Fallback model ready: tinyllama"
    else
      fail "Could not pull any model. Check internet connection."
      exit 1
    fi
  fi
fi

# Save selected model to state file
echo "MODEL=$SELECTED_MODEL" > "$STATE_FILE"

# ════════════════════════════════════════════════════════════════════════
#  SECTION 6 — FIND FREE PORT & DETECT SERVER TOOL
# ════════════════════════════════════════════════════════════════════════
hdr "Starting Web Server"

# Find a free port starting from preferred candidates
find_free_port() {
  local candidates=(8080 8000 3000 3001 8081 9000 5000 5500)
  for port in "${candidates[@]}"; do
    if ! (echo "" 2>/dev/null > /dev/tcp/localhost/$port) 2>/dev/null; then
      echo "$port"
      return 0
    fi
  done
  # If all candidates busy, use a random available one
  python3 -c "import socket; s=socket.socket(); s.bind(('',0)); print(s.getsockname()[1]); s.close()"
}

WEB_PORT=$(find_free_port)
WEB_URL="http://localhost:${WEB_PORT}"

# Detect best available server
SERVER_CMD=""
SERVER_NAME=""

if command -v python3 &>/dev/null; then
  SERVER_CMD="python3 -m http.server ${WEB_PORT}"
  SERVER_NAME="Python 3"
elif command -v python &>/dev/null && python --version 2>&1 | grep -q "Python 3"; then
  SERVER_CMD="python -m http.server ${WEB_PORT}"
  SERVER_NAME="Python 3"
elif command -v node &>/dev/null && command -v npx &>/dev/null; then
  SERVER_CMD="npx -y serve . -p ${WEB_PORT}"
  SERVER_NAME="Node.js (npx serve)"
elif command -v php &>/dev/null; then
  SERVER_CMD="php -S localhost:${WEB_PORT}"
  SERVER_NAME="PHP built-in server"
elif command -v ruby &>/dev/null; then
  SERVER_CMD="ruby -run -e httpd . -p ${WEB_PORT}"
  SERVER_NAME="Ruby"
else
  fail "No web server found! Install Python 3, Node.js, PHP, or Ruby."
  echo -e "  Install Python: ${C}https://python.org/downloads${NC}"
  exit 1
fi

ok "Web server: $SERVER_NAME on port $WEB_PORT"

# ════════════════════════════════════════════════════════════════════════
#  SECTION 7 — INJECT DYNAMIC CONFIG INTO SITE
# ════════════════════════════════════════════════════════════════════════

# Write a dynamic config.js that the tutor page can read
cat > "$SCRIPT_DIR/js/tutor-config.js" << JSEOF
/* Auto-generated by start.sh — do not edit */
window.TUTOR_CONFIG = {
  ollamaUrl:     "http://localhost:${OLLAMA_PORT}",
  preferredModel:"${SELECTED_MODEL}",
  webPort:        ${WEB_PORT},
  generatedAt:   "$(date -u +%Y-%m-%dT%H:%M:%SZ)",
  systemInfo: {
    platform:   "${PLATFORM}",
    arch:       "${ARCH}",
    ramMb:      ${TOTAL_RAM_MB},
    cpuCores:   ${CPU_CORES},
    hasGpu:     ${HAS_GPU},
    gpuName:    "${GPU_NAME}"
  }
};
JSEOF
ok "Dynamic config written → js/tutor-config.js"

# ════════════════════════════════════════════════════════════════════════
#  SECTION 8 — CLEANUP HANDLER
# ════════════════════════════════════════════════════════════════════════
cleanup() {
  echo ""
  echo -e "  ${Y}Shutting down...${NC}"
  kill "$WEB_PID" 2>/dev/null || true
  # Don't kill Ollama — it can keep running in background
  echo -e "  ${G}✓${NC} Web server stopped. Ollama keeps running in background."
  echo -e "    (To stop Ollama: ${D}pkill ollama${NC})"
  exit 0
}
trap cleanup INT TERM

# ════════════════════════════════════════════════════════════════════════
#  SECTION 9 — LAUNCH WEB SERVER
# ════════════════════════════════════════════════════════════════════════

# Start server in background
$SERVER_CMD > /tmp/javabot-server.log 2>&1 &
WEB_PID=$!
sleep 1

# Verify server started
if ! kill -0 "$WEB_PID" 2>/dev/null; then
  fail "Web server failed to start. Check /tmp/javabot-server.log"
  exit 1
fi

# ════════════════════════════════════════════════════════════════════════
#  SECTION 10 — OPEN BROWSER
# ════════════════════════════════════════════════════════════════════════

open_browser() {
  local url="${WEB_URL}/tutor.html"
  sleep 1
  case "$PLATFORM" in
    linux)
      for cmd in xdg-open sensible-browser google-chrome chromium firefox; do
        if command -v "$cmd" &>/dev/null; then
          "$cmd" "$url" &>/dev/null &
          return
        fi
      done
      ;;
    mac)
      open "$url" &
      ;;
  esac
}
open_browser

# ════════════════════════════════════════════════════════════════════════
#  SECTION 11 — READY SUMMARY
# ════════════════════════════════════════════════════════════════════════
echo ""
echo -e "${G}${B}╔══════════════════════════════════════════════════╗${NC}"
echo -e "${G}${B}║   ✅  JavaBot AI Tutor is READY!                 ║${NC}"
echo -e "${G}${B}╚══════════════════════════════════════════════════╝${NC}"
echo ""
printf "  %-20s %s\n" "🌐 AI Tutor:"        "${WEB_URL}/tutor.html"
printf "  %-20s %s\n" "📚 DSA Hub:"         "${WEB_URL}/index.html"
printf "  %-20s %s\n" "📅 24-Day Console:"  "${WEB_URL}/schedule.html"
echo ""
printf "  %-20s %s\n" "🤖 AI Model:"        "$SELECTED_MODEL"
printf "  %-20s %s\n" "⚡ Ollama API:"      "http://localhost:${OLLAMA_PORT}"
printf "  %-20s %s\n" "🧠 RAM (Total):"     "${TOTAL_RAM_MB} MB"
printf "  %-20s %s\n" "🔧 CPU Threads:"     "${OLLAMA_THREADS} / ${CPU_CORES}"
printf "  %-20s %s\n" "🖥  GPU:"            "${GPU_NAME}"
echo ""
echo -e "  ${D}Press Ctrl+C to stop the web server${NC}"
echo ""

# Keep alive (wait for server process)
wait "$WEB_PID"
