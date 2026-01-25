# 🎮 Proyecto MasterMind - Migración a Maven

Este repositorio contiene la implementación del juego de mesa **MasterMind** desarrollado en Java con Swing. Esta versión representa una refactorización y migración técnica del proyecto original para utilizar **Apache Maven** como gestor de construcción y dependencias.

## 📋 Descripción del Proyecto

El objetivo de este trabajo ha sido modernizar un proyecto Java tradicional, estableciendo una arquitectura robusta y estandarizada. La migración a Maven permite automatizar la compilación, la gestión de librerías y la generación de ejecutables.

### 🛠 Conocimientos Técnicos y Tecnologías

Para realizar esta migración se han aplicado los siguientes estándares y configuraciones:

- **Java 17:** Configuración explícita del compilador mediante la propiedad `<maven.compiler.release>` para asegurar compatibilidad moderna.
- **Estructura Maven:** Reorganización del código fuente separando lógica de negocio (`src/main`) y pruebas (`src/test`).
- **Gestión de Manifiesto:** Implementación del plugin `maven-jar-plugin` para definir la clase principal (`Main-Class`) en el `MANIFEST.MF`, permitiendo que el JAR sea ejecutable.
- **Dependencias:** Gestión automatizada de librerías externas como JUnit para pruebas unitarias.

---

## 📂 Estructura del Repositorio

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
