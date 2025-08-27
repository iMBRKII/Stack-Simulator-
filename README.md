# Stack Simulator

A Java-based stack data structure simulator that provides a visual and interactive way to understand stack operations including push, pop, peek, and more.

## Features

- **Interactive Stack Operations**: Push, pop, peek, and clear operations
- **Visual Representation**: Real-time visualization of stack elements
- **Size Management**: Configurable stack size with overflow/underflow protection
- **Educational Tool**: Perfect for learning data structures and algorithms
- **GUI Interface**: User-friendly graphical interface built with Java Swing

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Git (for cloning the repository)
- Gradle (optional, for build automation)

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/iMBRKII/Stack-Simulator-.git
   cd Stack-Simulator-
   ```

### Running the Application

**Using Gradle**:
```bash
# Run the application with Gradle
gradle run

# Or if you're using Gradle wrapper
./gradlew run
```

**Using Java directly**:
```bash
# Compile the Java files
javac -d bin src/*.java

# Run the application
java -cp bin StackSimulator
```

## Usage

1. Launch the application using either Gradle or Java
2. Use the input field to enter values you want to push onto the stack
3. Click the "Push" button to add elements to the stack
4. Use the "Pop" button to remove the top element
5. Click "Peek" to view the top element without removing it
6. Use "Clear" to empty the entire stack
7. The visual display will update in real-time to show stack operations

## Project Structure

```
src/
  StackSimulator.java    # Main application class with GUI
  Stack.java            # Stack implementation class
  StackPanel.java       # Custom panel for stack visualization
build.gradle           # Gradle build configuration
```

## Building with Gradle

To build the project using Gradle:

```bash
# Build the project
gradle build

# Create a JAR file
gradle jar
```

## Contributing

Feel free to fork this project and submit pull requests for any improvements or bug fixes.

## License

This project is open source and available under the [MIT License](LICENSE).

## Acknowledgments

Created as an educational tool to help students and developers understand stack data structure operations visually.
