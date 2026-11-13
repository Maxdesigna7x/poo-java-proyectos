import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class AventuraVisual {
    private final JComboBox<String> clase = new JComboBox<>(new String[]{"Guerrero", "Mago"});
    private final JProgressBar vidaHeroe = new JProgressBar();
    private final JProgressBar vidaDragon = new JProgressBar();
    private final JButton atacar = new JButton("Atacar");
    private final JLabel mensaje = new JLabel();
    private Personaje heroe;
    private Personaje dragon;

    private void abrir() {
        JFrame ventana = new JFrame("El dragón del castillo");
        JPanel controles = new JPanel();
        JButton reiniciar = new JButton("Nueva partida");
        controles.add(clase);
        controles.add(reiniciar);
        controles.add(atacar);
        JPanel vidas = new JPanel(new GridLayout(2, 2, 8, 8));
        vidas.add(new JLabel("Héroe"));
        vidas.add(vidaHeroe);
        vidas.add(new JLabel("Dragón"));
        vidas.add(vidaDragon);
        vidaHeroe.setStringPainted(true);
        vidaDragon.setStringPainted(true);
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        contenido.add(controles, BorderLayout.NORTH);
        contenido.add(vidas, BorderLayout.CENTER);
        contenido.add(mensaje, BorderLayout.SOUTH);
        reiniciar.addActionListener(e -> iniciar());
        atacar.addActionListener(e -> turno());
        iniciar();
        ventana.setContentPane(contenido);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(580, 210);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void iniciar() {
        heroe = clase.getSelectedIndex() == 0 ? new Guerrero() : new Mago();
        dragon = new Dragon();
        vidaHeroe.setMaximum(heroe.vida());
        vidaDragon.setMaximum(dragon.vida());
        actualizarBarras();
        atacar.setEnabled(true);
        mensaje.setText("Pulsa Atacar para jugar un turno.");
    }

    private void turno() {
        int golpe = heroe.ataque();
        dragon.recibir(golpe);
        if (dragon.vida() == 0) {
            mensaje.setText("¡Ganaste! El dragón cayó.");
            atacar.setEnabled(false);
        } else {
            int respuesta = dragon.ataque();
            heroe.recibir(respuesta);
            if (heroe.vida() == 0) {
                mensaje.setText("El dragón ganó.");
                atacar.setEnabled(false);
            } else {
                mensaje.setText("Causaste " + golpe + " de daño. El dragón respondió con " + respuesta + ".");
            }
        }
        actualizarBarras();
    }

    private void actualizarBarras() {
        vidaHeroe.setValue(heroe.vida());
        vidaHeroe.setString(heroe.vida() + " / " + vidaHeroe.getMaximum());
        vidaDragon.setValue(dragon.vida());
        vidaDragon.setString(dragon.vida() + " / " + vidaDragon.getMaximum());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AventuraVisual().abrir());
    }
}

abstract class Personaje {
    private int vida;
    Personaje(int vida) { this.vida = vida; }
    int vida() { return vida; }
    void recibir(int golpe) { vida = Math.max(0, vida - golpe); }
    abstract int ataque();
}

class Guerrero extends Personaje {
    Guerrero() { super(30); }
    @Override int ataque() { return 8; }
}

class Mago extends Personaje {
    Mago() { super(24); }
    @Override int ataque() { return 10; }
}

class Dragon extends Personaje {
    Dragon() { super(25); }
    @Override int ataque() { return 6; }
}
