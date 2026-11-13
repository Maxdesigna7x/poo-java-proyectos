import java.util.Scanner;

abstract class Mascota {
    private final String nombre;
    private int energia = 5;
    Mascota(String nombre) { this.nombre = nombre; }
    String getNombre() { return nombre; }
    int getEnergia() { return energia; }
    void alimentar() { energia = Math.min(10, energia + 2); System.out.println(nombre + " comió."); }
    void jugar() {
        if (energia < 2) { System.out.println(nombre + " necesita comer antes de jugar."); return; }
        energia -= 2;
        System.out.println(nombre + " juega. " + sonido());
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
public class Mascotas {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Elige mascota (1: perro, 2: gato): ");
        String tipo = entrada.nextLine().trim();
        if (!tipo.equals("1") && !tipo.equals("2")) { System.out.println("Opción inválida."); return; }
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine().trim();
        if (nombre.isEmpty()) nombre = "Amigo";
        Mascota mascota = tipo.equals("1") ? new Perro(nombre) : new Gato(nombre);
        while (true) {
            System.out.println("\n" + mascota.getNombre() + " | Energía: " + mascota.getEnergia() + "/10");
            System.out.print("1. Alimentar  2. Jugar  3. Salir: ");
            if (!entrada.hasNextLine()) break;
            String opcion = entrada.nextLine().trim();
            if (opcion.equals("1")) mascota.alimentar();
            else if (opcion.equals("2")) mascota.jugar();
            else if (opcion.equals("3")) break;
            else System.out.println("Opción inválida.");
        }
        System.out.println("¡Hasta luego!");
    }
}
