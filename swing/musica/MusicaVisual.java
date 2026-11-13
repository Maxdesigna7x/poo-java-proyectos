import java.awt.BorderLayout;
import javax.swing.*;

public class MusicaVisual {
    private final Pista[] pistas = {
        new Cancion("Viaje espacial", "Los Planetas"),
        new Podcast("Ideas curiosas", 3),
        new Cancion("Amanecer", "Luna")
    };

    private void abrir() {
        JFrame ventana = new JFrame("Mini reproductor · simulación");
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        String[] titulos = new String[pistas.length];
        for (int i = 0; i < pistas.length; i++) titulos[i] = pistas[i].titulo();
        JList<String> lista = new JList<>(titulos);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setSelectedIndex(0);
        JLabel estado = new JLabel("Selecciona una pista. La reproducción se simula con texto.");
        JButton reproducir = new JButton("Reproducir");
        JButton siguiente = new JButton("Siguiente");
        reproducir.addActionListener(e -> {
            int indice = lista.getSelectedIndex();
            if (indice >= 0) estado.setText(pistas[indice].reproducir());
        });
        siguiente.addActionListener(e -> {
            int indice = (lista.getSelectedIndex() + 1) % pistas.length;
            lista.setSelectedIndex(indice);
            estado.setText(pistas[indice].reproducir());
        });
        JPanel botones = new JPanel();
        botones.add(reproducir);
        botones.add(siguiente);
        contenido.add(estado, BorderLayout.NORTH);
        contenido.add(new JScrollPane(lista), BorderLayout.CENTER);
        contenido.add(botones, BorderLayout.SOUTH);
        ventana.setContentPane(contenido);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(520, 280);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MusicaVisual().abrir());
    }
}

abstract class Pista {
    private final String titulo;
    Pista(String titulo) { this.titulo = titulo; }
    String titulo() { return titulo; }
    abstract String reproducir();
}

class Cancion extends Pista {
    private final String artista;
    Cancion(String titulo, String artista) { super(titulo); this.artista = artista; }
    @Override String reproducir() { return "Canción: " + titulo() + " · " + artista; }
}

class Podcast extends Pista {
    private final int episodio;
    Podcast(String titulo, int episodio) { super(titulo); this.episodio = episodio; }
    @Override String reproducir() { return "Podcast: " + titulo() + " · episodio " + episodio; }
}
