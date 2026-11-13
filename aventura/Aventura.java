import java.util.Scanner;

// Cada personaje responde de forma distinta al mismo mensaje: atacar().
abstract class Personaje {
    private final String nombre;
    private int vida;

    Personaje(String nombre, int vida) { this.nombre = nombre; this.vida = vida; }
    String getNombre() { return nombre; }
    int getVida() { return vida; }
    boolean sigueEnPie() { return vida > 0; }
    void recibirDanio(int danio) { vida = Math.max(0, vida - danio); }
    abstract int atacar();
}
class Guerrero extends Personaje {
    Guerrero(String nombre) { super(nombre, 30); }
    @Override int atacar() { System.out.println(getNombre() + " usa su espada (8 puntos)."); return 8; }
}
class Mago extends Personaje {
    Mago(String nombre) { super(nombre, 24); }
    @Override int atacar() { System.out.println(getNombre() + " lanza un hechizo (10 puntos)."); return 10; }
}
class Monstruo extends Personaje {
    Monstruo() { super("Dragón", 25); }
    @Override int atacar() { System.out.println("El dragón lanza fuego (6 puntos)."); return 6; }
}
public class Aventura {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("EL DRAGÓN DEL CASTILLO\n1. Guerrero\n2. Mago");
        System.out.print("Elige tu personaje (1 o 2): ");
        String opcion = entrada.nextLine().trim();
        if (!opcion.equals("1") && !opcion.equals("2")) { System.out.println("Opción inválida."); return; }
        Personaje heroe = opcion.equals("1") ? new Guerrero("Alex") : new Mago("Alex");
        Personaje enemigo = new Monstruo();
        while (heroe.sigueEnPie() && enemigo.sigueEnPie()) {
            System.out.print("Enter para atacar, o escribe salir: ");
            if (!entrada.hasNextLine() || entrada.nextLine().trim().equalsIgnoreCase("salir")) break;
            enemigo.recibirDanio(heroe.atacar());
            if (enemigo.sigueEnPie()) heroe.recibirDanio(enemigo.atacar());
            System.out.println("Vida: " + heroe.getNombre() + " " + heroe.getVida() + " | " + enemigo.getNombre() + " " + enemigo.getVida());
        }
        if (!enemigo.sigueEnPie()) System.out.println("¡Ganaste!");
        else if (!heroe.sigueEnPie()) System.out.println("El dragón ganó.");
        else System.out.println("Aventura terminada.");
    }
}
