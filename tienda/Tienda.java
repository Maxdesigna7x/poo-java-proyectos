import java.util.ArrayList;
import java.util.Scanner;

abstract class Producto {
    private final String nombre;
    private final int precio; // Pesos enteros para mantener sencillo el ejemplo.
    Producto(String nombre, int precio) { this.nombre = nombre; this.precio = precio; }
    String getNombre() { return nombre; }
    int getPrecio() { return precio; }
    abstract String descripcion();
}
class Bebida extends Producto {
    Bebida(String nombre, int precio) { super(nombre, precio); }
    @Override String descripcion() { return "Bebida fría"; }
}
class Videojuego extends Producto {
    Videojuego(String nombre, int precio) { super(nombre, precio); }
    @Override String descripcion() { return "Juego digital"; }
}
public class Tienda {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Producto[] catalogo = {new Bebida("Limonada", 25), new Videojuego("Aventura espacial", 120)};
        ArrayList<Producto> carrito = new ArrayList<>();
        while (true) {
            System.out.println("\nTIENDA: elige un producto");
            for (int i = 0; i < catalogo.length; i++) {
                Producto p = catalogo[i];
                System.out.println((i + 1) + ". " + p.getNombre() + " - $" + p.getPrecio() + " (" + p.descripcion() + ")");
            }
            System.out.print("1 o 2 para agregar; 0 para pagar: ");
            if (!entrada.hasNextLine()) break;
            String opcion = entrada.nextLine().trim();
            if (opcion.equals("0")) break;
            if (opcion.equals("1") || opcion.equals("2")) {
                Producto elegido = catalogo[Integer.parseInt(opcion) - 1];
                carrito.add(elegido);
                System.out.println("Agregado: " + elegido.getNombre());
            } else System.out.println("Opción inválida.");
        }
        int total = 0;
        System.out.println("\nTICKET");
        for (Producto producto : carrito) {
            System.out.println(producto.getNombre() + " $" + producto.getPrecio());
            total += producto.getPrecio();
        }
        System.out.println("Total: $" + total);
    }
}
