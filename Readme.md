## Build and Run Instructions

### Prerequisites

* **Java 21+** (check with `java -version`)
* **Maven 3.8+** (check with `mvn -version`)

### Build the Project (it should be the same for windows and linux)

From the project root (where `pom.xml` is located), run:

`mvn clean install`

This command compiles the source code, runs tests, and packages the application into a JAR file (typically located in `target/`).

### Run the Application

After building, run the generated JAR file:

`java -jar target/TheLivingOfAdam.jar`

## How to play

Use WASD for navigation, Enter for confirmation/interaction. Escape for pause.
NPC's have a few sentences to say, then they loop.
Sand tiles indicates something is programmed onto them.
There's a special item on a map which can help in succeding faster.
The goal is to open the chest.
