import java.util.Scanner;

abstract class TransporteBase {
    private final String nombre;
    TransporteBase(String nombre) { this.nombre = nombre; }
    String getNombre() { return nombre; }
    abstract int costo(int kilometros);
}
class Bicicleta extends TransporteBase {
    Bicicleta() { super("Bicicleta compartida"); }
    @Override int costo(int kilometros) { return 5 + kilometros * 2; }
}
class Taxi extends TransporteBase {
    Taxi() { super("Taxi"); }
    @Override int costo(int kilometros) { return 20 + kilometros * 8; }
}
class Autobus extends TransporteBase {
    Autobus() { super("Autobús"); }
    @Override int costo(int kilometros) { return 12; }
}
public class Transporte {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("¿Cuántos kilómetros recorrerás? (entero entre 1 y 1000): ");
        if (!entrada.hasNextInt()) { System.out.println("Escribe un número entero válido."); return; }
        int kilometros = entrada.nextInt();
        if (kilometros < 1 || kilometros > 1000) { System.out.println("La distancia debe estar entre 1 y 1000 km."); return; }
        TransporteBase[] opciones = {new Bicicleta(), new Taxi(), new Autobus()};
        TransporteBase masBarato = opciones[0];
        for (TransporteBase opcion : opciones) {
            int precio = opcion.costo(kilometros);
            System.out.println(opcion.getNombre() + ": $" + precio);
            if (precio < masBarato.costo(kilometros)) masBarato = opcion;
        }
        System.out.println("Una opción más económica: " + masBarato.getNombre());
    }
}
