// El modelo aplica las reglas sin importar Swing.
class Partida {
    private static final String[] MAPA = {
        "########",
        "#R....##",
        "#.C.PGS#",
        "#..#..##",
        "########"
    };
    static final int FILAS = MAPA.length;
    static final int COLUMNAS = MAPA[0].length();

    private final Casilla[][] tablero = new Casilla[FILAS][COLUMNAS];
    private int filaRobot, columnaRobot, filaCaja, columnaCaja;
    private int pasos;
    private boolean ganada;
    private String mensaje;

    Partida() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                char simbolo = MAPA[f].charAt(c);
                switch (simbolo) {
                    case '#': tablero[f][c] = new Muro(); break;
                    case 'G': tablero[f][c] = new Puerta(); break;
                    case 'P': tablero[f][c] = new Suelo("Placa"); break;
                    case 'S': tablero[f][c] = new Suelo("Salida"); break;
                    default: tablero[f][c] = new Suelo(""); break;
                }
            }
        }
        reiniciar();
    }

    void reiniciar() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (MAPA[f].charAt(c) == 'R') { filaRobot = f; columnaRobot = c; }
                if (MAPA[f].charAt(c) == 'C') { filaCaja = f; columnaCaja = c; }
            }
        }
        pasos = 0;
        ganada = false;
        mensaje = "La puerta está cerrada.";
    }

    void mover(int df, int dc) {
        if (ganada || Math.abs(df) + Math.abs(dc) != 1) return;
        int f = filaRobot + df;
        int c = columnaRobot + dc;
        if (!dentro(f, c) || !casilla(f, c).transitable(placaActiva())) {
            mensaje = "El camino está bloqueado.";
            return;
        }
        if (hayCaja(f, c)) {
            int destinoF = f + df;
            int destinoC = c + dc;
            if (!dentro(destinoF, destinoC) || !admiteCaja(destinoF, destinoC)) {
                mensaje = "La caja no puede avanzar por ahí.";
                return;
            }
            filaCaja = destinoF;
            columnaCaja = destinoC;
        }
        filaRobot = f;
        columnaRobot = c;
        pasos++;
        ganada = MAPA[f].charAt(c) == 'S';
        if (ganada) mensaje = "¡Llegaste a la salida!";
        else if (placaActiva()) mensaje = "Placa activa. La puerta está abierta.";
        else mensaje = "La puerta está cerrada.";
    }

    private boolean dentro(int f, int c) {
        return f >= 0 && f < FILAS && c >= 0 && c < COLUMNAS;
    }

    private boolean admiteCaja(int f, int c) {
        char simbolo = MAPA[f].charAt(c);
        return simbolo == '.' || simbolo == 'P' || simbolo == 'R' || simbolo == 'C';
    }

    Casilla casilla(int f, int c) { return tablero[f][c]; }
    boolean hayRobot(int f, int c) { return filaRobot == f && columnaRobot == c; }
    boolean hayCaja(int f, int c) { return filaCaja == f && columnaCaja == c; }
    boolean placaActiva() { return MAPA[filaCaja].charAt(columnaCaja) == 'P'; }
    int pasos() { return pasos; }
    String mensaje() { return mensaje; }
}

abstract class Casilla {
    abstract boolean transitable(boolean placaActiva);
    abstract String etiqueta(boolean placaActiva);
}

class Suelo extends Casilla {
    private final String nombre;
    Suelo(String nombre) { this.nombre = nombre; }
    @Override boolean transitable(boolean placaActiva) { return true; }
    @Override String etiqueta(boolean placaActiva) { return nombre; }
}

class Muro extends Casilla {
    @Override boolean transitable(boolean placaActiva) { return false; }
    @Override String etiqueta(boolean placaActiva) { return "Muro"; }
}

class Puerta extends Casilla {
    @Override boolean transitable(boolean placaActiva) { return placaActiva; }
    @Override String etiqueta(boolean placaActiva) { return placaActiva ? "Abierta" : "Cerrada"; }
}
