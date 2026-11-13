import java.util.Scanner;

abstract class Pista {
    private final String titulo;
    Pista(String titulo) { this.titulo = titulo; }
    String getTitulo() { return titulo; }
    abstract void reproducir();
}
class Cancion extends Pista {
    private final String artista;
    Cancion(String titulo, String artista) { super(titulo); this.artista = artista; }
    @Override void reproducir() { System.out.println("♪ Canción: " + getTitulo() + " — " + artista); }
}
class Podcast extends Pista {
    private final int episodio;
    Podcast(String titulo, int episodio) { super(titulo); this.episodio = episodio; }
    @Override void reproducir() { System.out.println("🎙 Podcast: " + getTitulo() + ", episodio " + episodio); }
}
public class Musica {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Pista[] lista = {new Cancion("Viaje espacial", "Los Planetas"), new Podcast("Ideas curiosas", 3), new Cancion("Amanecer", "Luna")};
        System.out.println("MINI REPRODUCTOR (simulación: no reproduce audio)");
        for (Pista pista : lista) System.out.println("- " + pista.getTitulo());
        System.out.print("Pulsa Enter para recorrer la lista: ");
        if (entrada.hasNextLine()) entrada.nextLine();
        for (Pista pista : lista) pista.reproducir();
        System.out.println("Fin de la lista.");
    }
}
