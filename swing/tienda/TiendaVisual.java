import java.awt.BorderLayout;
import javax.swing.*;

public class TiendaVisual {
    private final Producto[] catalogo = {new Bebida(), new Videojuego()};
    private final DefaultListModel<String> carrito = new DefaultListModel<>();
    private final JLabel resumen = new JLabel("Total: $0");
    private int total;

    private void abrir() {
        JFrame ventana = new JFrame("Tienda y carrito");
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        JPanel botones = new JPanel();
        for (Producto producto : catalogo) {
            JButton agregar = new JButton(producto.nombre() + " $" + producto.precio());
            agregar.addActionListener(e -> agregar(producto));
            botones.add(agregar);
        }
        JButton pagar = new JButton("Pagar");
        pagar.addActionListener(e -> {
            if (carrito.isEmpty()) return;
            JOptionPane.showMessageDialog(ventana,
                String.join("\n", java.util.Collections.list(carrito.elements())) + "\n\nTotal: $" + total,
                "Ticket", JOptionPane.INFORMATION_MESSAGE);
            carrito.clear();
            total = 0;
            resumen.setText("Total: $0");
        });
        JPanel pie = new JPanel();
        pie.add(resumen);
        pie.add(pagar);
        contenido.add(botones, BorderLayout.NORTH);
        contenido.add(new JScrollPane(new JList<>(carrito)), BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);
        ventana.setContentPane(contenido);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(510, 320);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void agregar(Producto producto) {
        carrito.addElement(producto.nombre() + " · " + producto.descripcion() + " · $" + producto.precio());
        total += producto.precio();
        resumen.setText("Total: $" + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TiendaVisual().abrir());
    }
}

abstract class Producto {
    private final String nombre;
    private final int precio;
    Producto(String nombre, int precio) { this.nombre = nombre; this.precio = precio; }
    String nombre() { return nombre; }
    int precio() { return precio; }
    abstract String descripcion();
}

class Bebida extends Producto {
    Bebida() { super("Limonada", 25); }
    @Override String descripcion() { return "Bebida fría"; }
}

class Videojuego extends Producto {
    Videojuego() { super("Aventura espacial", 120); }
    @Override String descripcion() { return "Juego digital"; }
}
