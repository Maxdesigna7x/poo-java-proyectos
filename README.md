# Proyectos de programación orientada a objetos con Java

Colección en español para una primera clase de POO. Incluye cinco ejercicios de consola, sus cinco versiones gráficas con Swing y un proyecto final. Cada ejemplo se compila por separado con el JDK y usa solo la biblioteca estándar.

| Proyecto | Descripción | Consola | Swing |
|---|---|---|---|
| Aventura contra el dragón | Elige un guerrero o mago y combate por turnos. Cada personaje define su propio ataque. | [Guía](aventura/GUIA.md) | [Guía](swing/aventura/GUIA.md) |
| Mascota virtual | Adopta un perro o gato, aliméntalo y juega con él. Cada especie responde con su propio sonido. | [Guía](mascotas/GUIA.md) | [Guía](swing/mascotas/GUIA.md) |
| Tienda y carrito | Agrega productos, calcula el total y consulta el ticket. El carrito admite bebidas y videojuegos. | [Guía](tienda/GUIA.md) | [Guía](swing/tienda/GUIA.md) |
| Mini reproductor | Recorre una lista de canciones y podcasts. Cada pista muestra sus datos al simular la reproducción. | [Guía](musica/GUIA.md) | [Guía](swing/musica/GUIA.md) |
| Comparador de transporte | Introduce una distancia y compara bicicleta, taxi y autobús. Cada transporte calcula su propia tarifa. | [Guía](transporte/GUIA.md) | [Guía](swing/transporte/GUIA.md) |
| Laboratorio Robot | Empuja una caja sobre una placa para abrir la puerta de salida. El modelo aplica las reglas y Swing dibuja el tablero. | — | [Guía y plan del proyecto final](swing/robot/GUIA.md) |

Cada guía incluye instrucciones de ejecución, diagramas y una explicación de cómo se aplica la POO.

## Antes de comenzar

Necesitas un JDK instalado. Comprueba con `java -version` y `javac -version`. Los ejemplos usan características compatibles con Java 8 o posterior. Desde una terminal, entra en la carpeta de **un solo proyecto** y compila y ejecuta su archivo. Por ejemplo:

```bash
cd aventura
javac -encoding UTF-8 Aventura.java
java Aventura
```

En las demás carpetas sustituye `Aventura` por el nombre del archivo indicado en su guía. Los archivos `.class` son generados por Java y se pueden borrar; no son código fuente. Para ejecutar la comprobación automática de todos los proyectos desde esta carpeta, usa `bash probar.sh`. El script necesita Bash y un JDK; crea sus archivos compilados en una carpeta temporal y la elimina al terminar.

## Ideas para la clase

1. Ejecuta el programa y observa qué sucede.
2. Dibuja los objetos y sus datos. Localiza la clase padre y las clases hijas.
3. Encuentra una variable de tipo padre que almacene un objeto de tipo hijo.
4. Cambia un número o mensaje, compila de nuevo y predice el resultado.
5. Añade una clase hija nueva siguiendo los retos de la guía.

Cada carpeta es un proyecto independiente. `abstract` significa que la clase define una idea común, pero se crean objetos de sus clases hijas. `@Override` indica que una clase hija proporciona su propia versión de un método heredado. `private` protege los datos y los métodos permiten acceder a ellos de forma controlada. No es necesario entender cada detalle de `Scanner` o `ArrayList` antes de experimentar con los objetos.

## Los cuatro principios, en pocas palabras

| Principio | Pregunta para identificarlo | Ejemplo |
|---|---|---|
| Encapsulación | ¿Quién protege los datos y controla cómo se modifican? | `Mascota` mantiene privada su energía y la cambia al alimentar o jugar. |
| Abstracción | ¿Qué acciones comunes necesita conocer el programa? | `TransporteBase` define que un transporte puede calcular su costo. |
| Herencia | ¿Qué clase hija reutiliza lo que define una clase padre? | `Perro` y `Gato` heredan de `Mascota`. |
| Polimorfismo | ¿La misma llamada produce un comportamiento según el objeto? | `pista.reproducir()` muestra una canción o un podcast según el objeto. |

Las guías de cada proyecto explican los cuatro principios usando fragmentos del código real. Las clases abstractas son una herramienta para modelar abstracciones; no son la única forma de aplicar el principio de abstracción.
