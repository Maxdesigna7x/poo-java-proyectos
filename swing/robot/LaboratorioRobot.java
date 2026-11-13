import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LaboratorioRobot {
    private final Partida partida = new Partida();
    private final JPanel tablero = new JPanel(new GridLayout(Partida.FILAS, Partida.COLUMNAS, 4, 4));
    private final JLabel[][] celdas = new JLabel[Partida.FILAS][Partida.COLUMNAS];
    private final JLabel estado = new JLabel();

    private void abrir() {
        JFrame ventana = new JFrame("Laboratorio Robot");
        JPanel contenido = new JPanel(new BorderLayout(10, 10));
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        contenido.add(new JLabel("Empuja la caja sobre la placa y lleva el robot a la salida."), BorderLayout.NORTH);
        for (int fila = 0; fila < Partida.FILAS; fila++) {
            for (int columna = 0; columna < Partida.COLUMNAS; columna++) {
                JLabel celda = new JLabel("", SwingConstants.CENTER);
                celda.setOpaque(true);
                celda.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
                celda.setPreferredSize(new Dimension(82, 52));
                celdas[fila][columna] = celda;
                tablero.add(celda);
            }
        }
        contenido.add(tablero, BorderLayout.CENTER);
        JPanel pie = new JPanel(new BorderLayout(6, 6));
        JPanel botones = new JPanel();
        conectar(contenido, botones, "↑", "UP", -1, 0);
        conectar(contenido, botones, "↓", "DOWN", 1, 0);
        conectar(contenido, botones, "←", "LEFT", 0, -1);
        conectar(contenido, botones, "→", "RIGHT", 0, 1);
        JButton reiniciar = new JButton("Reiniciar");
        reiniciar.addActionListener(e -> {
            partida.reiniciar();
            dibujar();
        });
        botones.add(reiniciar);
        pie.add(botones, BorderLayout.NORTH);
        pie.add(estado, BorderLayout.SOUTH);
        contenido.add(pie, BorderLayout.SOUTH);
        dibujar();
        ventana.setContentPane(contenido);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void conectar(JPanel contenido, JPanel botones, String simbolo, String tecla, int df, int dc) {
        JButton boton = new JButton(simbolo);
        // Las flechas del teclado y los botones ejecutan la misma acción.
        Action mover = new AbstractAction(simbolo) {
            @Override public void actionPerformed(ActionEvent evento) {
                partida.mover(df, dc);
                dibujar();
            }
        };
        boton.setAction(mover);
        botones.add(boton);
        contenido.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);
        contenido.getActionMap().put(tecla, mover);
    }

    private void dibujar() {
        boolean abierta = partida.placaActiva();
        for (int f = 0; f < Partida.FILAS; f++) {
            for (int c = 0; c < Partida.COLUMNAS; c++) {
                Casilla casilla = partida.casilla(f, c);
                JLabel celda = celdas[f][c];
                String texto = casilla.etiqueta(abierta);
                Color fondo = new Color(229, 237, 243);
                if (!casilla.transitable(abierta)) fondo = new Color(157, 173, 188);
                if (partida.hayCaja(f, c)) {
                    texto = abierta ? "Caja/placa" : "Caja";
                    fondo = new Color(251, 201, 121);
                }
                if (partida.hayRobot(f, c)) {
                    texto = "Robot";
                    fondo = new Color(121, 208, 183);
                }
                celda.setText(texto);
                celda.setBackground(fondo);
            }
        }
        estado.setText(partida.mensaje() + "  ·  Pasos: " + partida.pasos());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LaboratorioRobot().abrir());
    }
}
