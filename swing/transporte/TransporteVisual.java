import java.awt.BorderLayout;
import javax.swing.*;

public class TransporteVisual {
    private final Transporte[] opciones = {new Bicicleta(), new Taxi(), new Autobus()};

    private void abrir() {
        JFrame ventana = new JFrame("Comparador de transporte");
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JPanel controles = new JPanel();
        JTextField entrada = new JTextField("5", 6);
        JButton calcular = new JButton("Comparar");
        controles.add(new JLabel("Distancia en km (1 a 1000)"));
        controles.add(entrada);
        controles.add(calcular);
        JTextArea resultados = new JTextArea(6, 30);
        resultados.setEditable(false);
        calcular.addActionListener(e -> resultados.setText(comparar(entrada.getText())));
        ventana.getRootPane().setDefaultButton(calcular);
        contenido.add(controles, BorderLayout.NORTH);
        contenido.add(new JScrollPane(resultados), BorderLayout.CENTER);
        calcular.doClick();
        ventana.setContentPane(contenido);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private String comparar(String texto) {
        int km;
        try {
            km = Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return "Escribe una distancia entera.";
        }
        if (km < 1 || km > 1000) return "La distancia debe estar entre 1 y 1000 km.";
        StringBuilder salida = new StringBuilder();
        Transporte mejor = opciones[0];
        for (Transporte opcion : opciones) {
            int precio = opcion.costo(km);
            salida.append(opcion.nombre()).append(": $").append(precio).append('\n');
            if (precio < mejor.costo(km)) mejor = opcion;
        }
        return salida + "\nMás económico: " + mejor.nombre();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TransporteVisual().abrir());
    }
}

abstract class Transporte {
    private final String nombre;
    Transporte(String nombre) { this.nombre = nombre; }
    String nombre() { return nombre; }
    abstract int costo(int km);
}

class Bicicleta extends Transporte {
    Bicicleta() { super("Bicicleta compartida"); }
    @Override int costo(int km) { return 5 + 2 * km; }
}

class Taxi extends Transporte {
    Taxi() { super("Taxi"); }
    @Override int costo(int km) { return 20 + 8 * km; }
}

class Autobus extends Transporte {
    Autobus() { super("Autobús"); }
    @Override int costo(int km) { return 12; }
}
