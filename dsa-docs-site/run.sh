#!/bin/bash

# Simple script to run a local web server for the DSA Docs site
echo "Starting local server on http://localhost:8000"
echo "Press Ctrl+C to stop the server."

# Change to the directory of this script
cd "$(dirname "$0")"

# Run python http.server
python3 -m http.server 8000
