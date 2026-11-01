# ⚔️ smh-le-jeu

> *Java text-based RPG developed for the Object-Oriented Software Analysis and Design course, with a strong focus on clean MVC architecture and design patterns implementation.*

## 🎮 About the Game

**SMH Le Jeu** is a terminal-based adventure game set on the Ensimag campus. The player explores buildings and streets, collects items (weapons, armor, keys, food), manages their inventory, and attempts to discover a hidden treasure.

## 🏗️ Architecture & Design Patterns

This project was built with a strong emphasis on software engineering and object-oriented design principles. The overall architecture follows the **MVC (Model-View-Controller)** pattern.

The following **Design Patterns** were implemented to ensure a robust, scalable, and maintainable codebase:

* **Observer**: For asynchronous communication between the Model and the View (`Listenable`, `Listener`).
* **Adapter**: To adapt different graphical interfaces (`TerminalUI`, `JFrame`) to the standard interface expected by the game engine (`GameView`).
* **Command**: To encapsulate player actions (movement, collecting, equipping) as standalone executable objects (`MoveCommand`, `DropCommand`, etc.).
* **State**: To seamlessly manage the different phases and menus of the game (`MenuState`, `PlayingState`, `InventoryState`, etc.).
* **Builder**: To cleanly and dynamically construct the main game instance along with its dependencies (`GameBuilder`).
* **Singleton**: To guarantee the uniqueness of the game engine instance (`GameEngine`).
* **Factory**: To centralize the instantiation and generation of the different world elements (`CampusFactory`).

## 📚 Documentation

* **Detailed Report**: You will find in-depth explanations of our implementation and architectural choices in the `compte-rendu` folder (in French).
* **UML Diagrams**: All class diagrams and state-transition diagrams are located in the `diagrams` folder.

## 🚀 Compilation and Execution

The project already includes the necessary dependencies to run out of the box: *Lanterna* (`lib/lanterna-3.2.0-alpha1.jar`) for the terminal UI and *JUnit* (`lib/junit-platform-console-standalone.jar`) for testing.

### Available commands (via `make`):

**Compile the project:**
```bash
make compile
```

**Run the game:**
```bash
make run
```

**Run unit tests (JUnit):**
```bash
make test
```

**Clean compiled files:**
```bash
make clean
```
