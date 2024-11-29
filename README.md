# AngryBirds1

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.




# Angry Birds Game

This is a Java-based Angry Birds game developed using LibGDX.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Gradle
- IntelliJ IDEA (recommended)

## Setup

1. **Clone the repository:**

    ```sh
    git clone https://github.com/NikhilAggrawal/angry-birds-game.git
    cd angry-birds-game
    ```

2. **Import the project into IntelliJ IDEA:**

    - Open IntelliJ IDEA.
    - Select `File` > `Open...`.
    - Navigate to the cloned repository and select the `build.gradle` file.
    - Click `OK` to import the project.

3. **Build the project:**

    ```sh
    ./gradlew build
    ```

## Running the Game

1. **Run the game from IntelliJ IDEA:**

    - Open the `AngryBirds1.java` file.
    - Right-click on the file and select `Run 'AngryBirds1.main()'`.

2. **Run the game from the command line:**

    ```sh
    ./gradlew desktop:run
    ```

## Testing

1. **Run tests from IntelliJ IDEA:**

    - Open the `LevelTest.java` file.
    - Right-click on the file and select `Run 'LevelTest'`.

2. **Run tests from the command line:**

    ```sh
    ./gradlew test
    ```

## Project Structure

- `core/src/main/java/com/birds/game/` - Main game source code.
- `core/src/test/java/com/birds/game/` - Test source code.
- `assets/` - Game assets (images, sounds, etc.).


## Online Sources Referred
- AngryBirds Wiki
- Figma (Creating Buttons)
- libGDX
- Google (for Miscellaneous Questions)
