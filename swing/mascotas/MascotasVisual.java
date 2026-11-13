import java.awt.GridLayout;
import javax.swing.*;

public class MascotasVisual {
    private final JComboBox<String> especie = new JComboBox<>(new String[]{"Perro", "Gato"});
    private final JTextField nombre = new JTextField("Luna", 12);
    private final JProgressBar energia = new JProgressBar(0, 10);
    private final JLabel mensaje = new JLabel();
    private Mascota mascota;

    private void abrir() {
        JFrame ventana = new JFrame("Mascota virtual");
        JPanel contenido = new JPanel(new GridLayout(4, 1, 8, 8));
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JPanel seleccion = new JPanel();
        JButton adoptar = new JButton("Adoptar");
        seleccion.add(especie);
        seleccion.add(nombre);
        seleccion.add(adoptar);
        JPanel acciones = new JPanel();
        JButton alimentar = new JButton("Alimentar");
        JButton jugar = new JButton("Jugar");
        acciones.add(alimentar);
        acciones.add(jugar);
        energia.setStringPainted(true);
        contenido.add(seleccion);
        contenido.add(energia);
        contenido.add(acciones);
        contenido.add(mensaje);
        adoptar.addActionListener(e -> adoptar());
        alimentar.addActionListener(e -> mostrar(mascota.alimentar()));
        jugar.addActionListener(e -> mostrar(mascota.jugar()));
        adoptar();
        ventana.setContentPane(contenido);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(540, 240);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void adoptar() {
        String elegido = nombre.getText().trim();
        if (elegido.isEmpty()) elegido = "Amigo";
        mascota = especie.getSelectedIndex() == 0 ? new Perro(elegido) : new Gato(elegido);
        mostrar(elegido + " llegó a casa.");
    }

    private void mostrar(String texto) {
        energia.setValue(mascota.energia());
        energia.setString("Energía: " + mascota.energia() + " / 10");
        mensaje.setText(texto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MascotasVisual().abrir());
    }
}

abstract class Mascota {
    private final String nombre;
    private int energia = 5;
    Mascota(String nombre) { this.nombre = nombre; }
    int energia() { return energia; }

    String alimentar() {
        energia = Math.min(10, energia + 2);
        return nombre + " comió.";
    }

    String jugar() {
        if (energia < 2) return nombre + " necesita comer antes de jugar.";
        energia -= 2;
        return nombre + " juega. " + sonido();
    }

    abstract String sonido();
}

class Perro extends Mascota {
    Perro(String nombre) { super(nombre); }
    @Override String sonido() { return "¡Guau!"; }
}

class Gato extends Mascota {
    Gato(String nombre) { super(nombre); }
    @Override String sonido() { return "¡Miau!"; }
}
