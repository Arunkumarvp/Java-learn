@echo off
:: ═══════════════════════════════════════════════════════════════════
::  start.bat — JavaBot AI Tutor · Fully Automatic Launcher (Windows)
::  Double-click this to start everything automatically
:: ═══════════════════════════════════════════════════════════════════
title JavaBot AI Tutor Launcher

echo.
echo  ╔══════════════════════════════════════════════════╗
echo  ║   🤖  JavaBot AI Tutor — Smart Launcher          ║
echo  ╚══════════════════════════════════════════════════╝
echo.
echo  Launching PowerShell smart setup...
echo.

powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0start.ps1"

if %errorlevel% neq 0 (
    echo.
    echo  Something went wrong. Check the output above.
    pause
)
