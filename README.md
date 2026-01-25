# MasterMind Game in Java

A fully functional **MasterMind** game implemented in Java, featuring a graphical user interface (GUI) with color selection, feedback pins, and multiple rounds. This project demonstrates Java Swing usage, object-oriented design, and game logic implementation.

---

## Features

- **Graphical User Interface (GUI)** with Java Swing
  - Interactive game board with rows for guesses
  - Color selection panel
  - Feedback pins (black and white) indicating correct and partially correct guesses
- **Dynamic Game Logic**
  - Randomly generated secret code using a customizable palette
  - Accurate feedback system with black (correct position) and white (correct color, wrong position) pins
  - Support for multiple rounds and configurable secret length
- **User-Friendly**
  - Select a color and fill the current row
  - Check guesses and receive immediate feedback
  - Game ends when the secret code is guessed or after maximum rounds

## Conocimientos Técnicos y Tecnologías

Para realizar esta migración se han aplicado los siguientes estándares y configuraciones:

- **Java 17:** Configuración explícita del compilador mediante la propiedad `<maven.compiler.release>` para asegurar compatibilidad moderna.
- **Estructura Maven:** Reorganización del código fuente separando lógica de negocio (`src/main`) y pruebas (`src/test`).
- **Gestión de Manifiesto:** Implementación del plugin `maven-jar-plugin` para definir la clase principal (`Main-Class`) en el `MANIFEST.MF`, permitiendo que el JAR sea ejecutable.
- **Dependencias:** Gestión automatizada de librerías externas como JUnit para pruebas unitarias.

---

## Estructura del Repositorio

El proyecto sigue la convención de directorios estándar de Maven:

```text
master-mind-maven/
├── src/
│   ├── main/
│   │   └── java/com/game/
│   │       ├── Main.java            # Punto de entrada (Launcher)
│   │       ├── MasterMindLogic.java # Lógica del juego
│   │       └── MasterMindUI.java    # Interfaz Gráfica (Swing)
│   └── test/
│       └── java/com/game/
│           └── AppTest.java         # Pruebas Unitarias
├── target/                          # Directorio de salida (generado)
└── pom.xml                          # Configuración del proyecto
