#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
tmpdir=$(mktemp -d)
trap 'rm -rf "$tmpdir"' EXIT
for proyecto in aventura mascotas tienda musica transporte; do
  mkdir -p "$tmpdir/$proyecto"
  javac -encoding UTF-8 -d "$tmpdir/$proyecto" "$proyecto/"*.java
done
comprobar() {
  local proyecto=$1 clase=$2 entrada=$3 esperado=$4 salida
  salida=$(printf '%b' "$entrada" | java -cp "$tmpdir/$proyecto" "$clase")
  if [[ "$salida" != *"$esperado"* ]]; then
    printf 'FALLÓ: %s, se esperaba: %s\nSalida:\n%s\n' "$proyecto" "$esperado" "$salida" >&2
    exit 1
  fi
  printf 'OK: %s — %s\n' "$proyecto" "$esperado"
}
comprobar aventura Aventura '1\n\n\n\n\n' '¡Ganaste!'
comprobar aventura Aventura '2\nsalir\n' 'Aventura terminada.'
comprobar aventura Aventura '9\n' 'Opción inválida.'
comprobar mascotas Mascotas '1\nLuna\n2\n2\n2\n3\n' 'necesita comer'
comprobar mascotas Mascotas '2\nMichi\n2\n3\n' '¡Miau!'
comprobar tienda Tienda '1\n2\n0\n' 'Total: $145'
comprobar tienda Tienda '0\n' 'Total: $0'
comprobar musica Musica '\n' 'Podcast: Ideas curiosas, episodio 3'
comprobar transporte Transporte '5\n' 'Una opción más económica: Autobús'
comprobar transporte Transporte 'abc\n' 'Escribe un número entero válido.'
comprobar transporte Transporte '0\n' 'La distancia debe estar entre 1 y 1000 km.'
