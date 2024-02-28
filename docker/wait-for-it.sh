#!/bin/bash
# wait-for-it.sh: Ждет доступности указанного хоста и порта.

host="$1"
port="$2"
timeout="${3:-60}"

echo "Ожидание доступности $host:$port..."

while ! nc -z "$host" "$port" >/dev/null 2>&1 < /dev/null; do
  timeout=$((timeout - 1))
  if [ $timeout -eq 0 ]; then
    echo >&2 "Ошибка: Таймаут ожидания $host:$port"
    exit 1
  fi
  sleep 1
done

echo "Сервер $host:$port доступен!"
