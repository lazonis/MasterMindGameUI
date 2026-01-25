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

## Technical Knowledge and Technologies

The following standards and configurations were applied to carry out this migration:

- **Java 17:** Explicit compiler configuration using the `<maven.compiler.release>` property to ensure modern compatibility.
- **Maven Structure:** Source code reorganization separating business logic (`src/main`) from tests (`src/test`).
- **Manifest Management:** Implementation of the `maven-jar-plugin` to define the main class (`Main-Class`) in the `MANIFEST.MF`, allowing the JAR to be executable.
- **Dependencies:** Automated management of external libraries such as JUnit for unit testing.

---

## Repository Structure

The project follows the standard Maven directory convention:

```text
master-mind-maven/
├── src/
│   ├── main/
│   │   └── java/com/game/
│   │       ├── Main.java            # Entry point (Launcher)
│   │       ├── MasterMindLogic.java # Game logic
│   │       └── MasterMindUI.java    # Graphical User Interface (Swing)
│   └── test/
│       └── java/com/game/
│           └── AppTest.java         # Unit Tests
├── target/                          # Output directory (generated)
└── pom.xml                          # Project configuration

## Build Instructions

To generate the project executable, Maven must be installed. Run the following command in the root directory:

```bash
mvn clean package

### This command will perform the following tasks:
* Clean previous builds.
* Compile the source code.
* Run unit tests.