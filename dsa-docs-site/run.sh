#!/usr/bin/env bash
# run.sh — Redirect to smart launcher
# For full auto-setup (model selection, Ollama, browser), use start.sh
cd "$(dirname "$0")"
exec bash start.sh "$@"
