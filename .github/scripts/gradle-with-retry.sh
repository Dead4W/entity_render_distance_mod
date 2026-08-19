#!/usr/bin/env bash
# Retry ./gradlew when NeoForged Maven or the network returns a transient error.
set +e
max_attempts=3
delay=30
attempt=1
while [ "$attempt" -le "$max_attempts" ]; do
  echo "::group::Gradle attempt ${attempt}/${max_attempts}: ./gradlew $*"
  log="$(mktemp)"
  ./gradlew "$@" 2>&1 | tee "$log"
  status="${PIPESTATUS[0]}"
  echo "::endgroup::"
  if [ "$status" -eq 0 ]; then
    exit 0
  fi
  if [ "$attempt" -eq "$max_attempts" ]; then
    echo "Gradle failed after ${max_attempts} attempts"
    exit "$status"
  fi
  if grep -Eqi 'Received status code 50[0-9]|Bad Gateway|Gateway Time-out|Could not GET |Could not GET resource|Connection reset|Connection timed out|Temporary failure in name resolution' "$log"; then
    echo "Transient Maven/network failure (exit ${status}). Retrying in ${delay}s..."
    sleep "$delay"
    delay=$((delay * 2))
    attempt=$((attempt + 1))
    continue
  fi
  echo "Non-retryable Gradle failure (exit ${status})"
  exit "$status"
done
