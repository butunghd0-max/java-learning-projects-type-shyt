# Java Learning Projects

A structured collection of Java console applications developed to build programming fundamentals. The repository progresses from basic syntax and logic to object-oriented programming, data structures, and external API integration. This project is part of Creativity, Activity and Service (CAS).

Most of these projects come from things I actually deal with or think about: robotics, electronics, food safety, and engineering math. Nothing here was built from a tutorial. Each project started as a problem like "how does Ohm's Law work on a breadboard?" or "can I simulate how a claw machine decides if you win?" and turned into working code.

## Table of Contents

- [Repository Structure](#repository-structure)
  - [Simple Projects](#1-simple-projects)
  - [Beginner Projects](#2-beginner-projects)
  - [Middle-Level Projects](#3-middle-level-projects)
- [Concepts Applied](#concepts-applied)
- [How to Run (If you want i guess)](#how-to-run-if-you-want-i-guess)
- [Project Details](#project-details)
- [References](#references)
- [License](#license)

## Repository Structure

The projects are organized by complexity into three main categories.

### 1. Simple Projects

Focused on basic I/O, control flow, loops, and math operations.

| Project                   | Description                                                                                  | File                        |
| ------------------------- | -------------------------------------------------------------------------------------------- | --------------------------- |
| Basic Calculator          | Performs addition, subtraction, multiplication, and division with formatted output.          | `Main.java`                 |
| Number Guessing Game      | Generates a random number between 1 and 100. Player has limited attempts with feedback.      | `Main.java`                 |
| Ohm's Law Calculator      | Calculates voltage, current, or resistance and checks if current levels are breadboard-safe. | `Main.java`                 |
| Age in Days Calculator    | Converts age in years to days with leap year approximation and age-based messages.           | `AgeInDaysCalculator.java`  |
| Tip Calculator            | Splits a bill among multiple people with tip percentage and input validation.                | `Main.java`                 |
| Dice Roller               | Rolls a die with a user-defined number of sides. Supports multiple rolls per session.        | `Main.java`                 |
| Grade Point Averager      | Averages grades across subjects and maps the result to a letter grade.                       | `GradePointAverager.java`   |
| Mad Libs Generator        | Prompts for word types and inserts them into a robot-maintenance themed story.               | `MadLibsGenerator.java`     |
| Motor RPM Estimator       | Estimates theoretical and real RPM from KV rating and voltage, with linear speed output.     | `Main.java`                 |
| Multiplication Table      | Generates an N x N table with diagonal values highlighted in brackets.                       | `Main.java`                 |
| Odd/Even Checker          | Checks if a whole number is odd or even using modulus.                                       | `Main.java`                 |
| Palindrome Checker        | Reverses and compares strings, ignoring spaces and case.                                     | `Main.java`                 |
| Password Strength Checker | Scores passwords on length, character variety, and checks against a blacklist.               | `Main.java`                 |
| Simple Login Prompt       | Simulates login authentication with a 3-attempt lockout.                                     | `Main.java`                 |
| String Reverser           | Demonstrates both manual and StringBuilder string reversal side by side.                     | `Main.java`                 |
| Temperature Converter     | Converts between Celsius and Fahrenheit with formatted decimal output.                       | `TemperatureConverter.java` |
| Vowel Counter             | Counts the number of vowels in a user-provided sentence.                                     | `Main.java`                 |

### 2. Beginner Projects

Introduces arrays, regex, modular methods, timing, and basic state management.

| Project                    | Description                                                                                                                           | File                              |
| -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------- |
| Scientific Calculator      | Command-driven calculator with trig, logarithms, roots, factorial, quadratic solver, memory, DEG/RAD mode, and history.               | `ScientificCalculator.java`       |
| Currency Converter         | Converts between USD, EUR, and GBP using hardcoded exchange rates with cross-rate math.                                               | `Main.java`                       |
| Unit Converter             | Converts length, weight, and temperature units with optional scientific notation output.                                              | `UnitConverter.java`              |
| Book Catalog               | Manages a catalog of books using parallel arrays with add, remove, search, and rate.                                                  | `BookCatalog.java`                |
| To-Do List                 | Array-based task manager with priority levels and a robot-maintenance theme.                                                          | `Main.java`                       |
| Vending Machine Simulation | Full simulation with product browsing, coin insertion, greedy change-making, and a password-protected admin panel with sales reports. | `VendingMachine.java`             |
| ATM Simulation             | PIN-authenticated banking with deposit, withdraw, balance check, and attempt lockout.                                                 | `Main.java`                       |
| Study Timer                | Pomodoro-style countdown timer with in-place console updates and bell alerts.                                                         | `StudyTimer.java`                 |
| Food Temperature Alert     | Monitors temperature readings against safe ranges and generates summary reports.                                                      | `FoodTemperatureAlertSystem.java` |
| Claw Machine Drop Timer    | Simulates drop timing using distance or X/Y coordinates with Pythagorean calculation.                                                 | `ClawMachineDropTimer.java`       |
| Text Analyzer              | Uses compiled regex patterns for word count, keyword search, and complexity analysis.                                                 | `TextAnalyzer.java`               |
| Flowchart Syntax Generator | Generates valid Mermaid.js flowchart syntax from user input with label escaping.                                                      | `Main.java`                       |
| Flashcard Quizzer          | Robotics safety themed quiz with scoring and a 100% pass gate.                                                                        | `FlashcardQuizzer.java`           |
| Random Workout Generator   | Selects random exercises from four categories using ArrayList and Collections.shuffle.                                                | `RandomWorkoutGenerator.java`     |
| Rock Paper Scissors        | Multi-round game with session stats and computer choice distribution tracking.                                                        | `RockPaperScissors.java`          |

### 3. Middle-Level Projects

Focuses on Object-Oriented Programming, file handling, external APIs, algorithms, and multi-file architecture.

| Project                      | Description                                                                                                       | Files                                                      |
| ---------------------------- | ----------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------- |
| Robotics Component Inventory | File-based inventory system with CSV persistence, add/remove/search, and malformed line handling.                 | `Component.java`, `InventoryManager.java`, `Main.java`     |
| Local Weather Dashboard      | Fetches real-time weather data from the Open-Meteo API using HttpURLConnection with manual JSON parsing.          | `WeatherDashboard.java`                                    |
| Sorting Algorithm Visualizer | Console-animated comparison of Bubble Sort and Selection Sort with comprehensive performance metrics.             | `SortingVisualizer.java`                                   |
| Tic Tac Toe AI               | Unbeatable AI opponent using the Minimax algorithm with depth-adjusted scoring.                                   | `TicTacToeAI.java`                                         |
| Manufacturing Assembly Line  | Uses a Queue (LinkedList) as a FIFO conveyor belt with production simulation and quality control.                 | `Product.java`, `AssemblyLine.java`, `Main.java`           |
| Automated Study Scheduler    | Greedy scheduling algorithm that sorts subjects by priority and allocates hours across available days.            | `Subject.java`, `Day.java`, `Scheduler.java`, `Main.java`  |
| Expense Tracker              | Logs expenses with category aggregation using LinkedHashMap.merge and saves formatted reports to file.            | `Expense.java`, `ExpenseTracker.java`, `Main.java`         |
| Claw Machine Arcade Tracker  | Probability-based simulation with dynamic payout pressure that adjusts grip strength based on target payout rate. | `Prize.java`, `Claw.java`, `GameSession.java`, `Main.java` |
| Food Safety Database Logger  | CRUD operations on temperature logs with danger zone analysis and ID-based lookup.                                | `TempLog.java`, `FoodSafetyLogger.java`, `Main.java`       |

## Concepts Applied

Across these projects, the following Java concepts are utilized:

**Core Java**

- Variables, loops, conditionals, `switch` statements, and the `Math` class.
- `Scanner` for all user input with validation loops.
- `DecimalFormat` and `printf` for formatted numerical output.
- `Thread.sleep` for timing and console animation.

**Object-Oriented Programming**

- Classes with encapsulation, constructors, and getter methods.
- Immutable data models with `final` fields.
- Constructor injection for testability (`Claw` accepts a `Random` instance).
- Defensive programming with `IllegalArgumentException` on invalid inputs.

**Data Structures**

- Arrays and parallel arrays for simple data grouping.
- `ArrayList` for dynamic collections.
- `LinkedList` via the `Queue` interface for FIFO processing.
- `LinkedHashMap` with `merge()` for category aggregation.

**File I/O and Serialization**

- Reading and writing text files with `FileWriter` and `BufferedWriter`.
- File path management with `java.nio.file.Path` and `Files`.
- CSV-style persistence with line-by-line parsing and malformed line handling.

**Networking**

- HTTP GET requests with `java.net.HttpURLConnection`.
- URL encoding with `URLEncoder` and `StandardCharsets`.
- Manual JSON response parsing without external libraries.

**Algorithms**

- Minimax with depth-adjusted scoring for game AI.
- Greedy scheduling for resource allocation.
- Bubble Sort and Selection Sort with step-by-step visualization.
- Greedy change-making with denomination breakdown.
- Probability simulation with payout regulation.

**Regex**

- Compiled `Pattern` constants with word boundary matching.
- `Matcher` for keyword search and text complexity analysis.

## How to Run (If you want i guess)

Make sure the [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) is installed.

1. Clone the repository:

   ```bash
   git clone https://github.com/butunghd0-max/java-learning-projects-type-shyt.git
   ```

2. Navigate to the folder of the project you want to run.

3. Compile the Java file(s):

   ```bash
   javac Main.java
   ```

   Replace `Main.java` with the actual filename if it differs, e.g., `StudyTimer.java`.

   For multi-file projects, compile all files at once:

   ```bash
   javac *.java
   ```

4. Run the compiled class:
   ```bash
   java Main
   ```

## Project Details

Quick usage notes for each project. Every project includes input validation, so expect clear error messages if you type something wrong.

### Simple Projects

**Basic Calculator** - Enter two numbers and an operator. Handles division by zero and formats output to remove unnecessary decimals.

**Ohm's Law Calculator** - Choose which value to solve for (voltage, current, or resistance). After calculation, it checks if the current is safe for a breadboard (under 500mA warning, over 1A danger).

**Motor RPM Estimator** - Enter a motor's KV rating, voltage, and efficiency percentage. Outputs theoretical and real-world RPM. Optionally calculates linear speed from a wheel/propeller diameter. Warns if RPM exceeds 50,000.

**Password Strength Checker** - Checks length, uppercase, lowercase, digits, and special characters. Also runs the password against a blacklist of common passwords like "password123" and "qwerty".

### Beginner Projects

**Scientific Calculator** - Type a command like `sin`, `sqrt`, `quadratic`, or `pow` and follow the prompts. Supports `pi`, `e`, `mr` (memory recall), and `ans` (last answer) as number inputs. Toggle between degrees and radians with `mode`. View past calculations with `history`.

**Vending Machine Simulation** - Browse products by category (Drinks, Snacks, Candy, Health) with stock labels. Insert coins/bills by denomination. The machine calculates change using a greedy algorithm and shows the exact coin breakdown. Admin mode (password: `admin123`) has sales reports, restocking, price changes, and low stock alerts.

**Flowchart Syntax Generator** - Follow the prompts to define flowchart steps. Outputs valid Mermaid.js `graph TD` syntax that you can paste into any Mermaid renderer.

**Text Analyzer** - Paste in text and get character count, word count, sentence count, average word length, and complexity classification. Search for keywords with regex word boundary matching.

### Middle-Level Projects

**Claw Machine Arcade Tracker** - The simulation models real arcade machine behavior. The `payoutPressure` mechanic compares total money spent against the target payout rate and dynamically adjusts grip strength. Run 25 simulated plays and see win rate, money spent, and actual vs. target payout percentages.

**Tic Tac Toe AI** - Play against an unbeatable AI. The Minimax algorithm evaluates every possible board state. Depth-adjusted scoring means the AI prefers faster wins (scores `10 - depth` instead of just `10`).

**Sorting Algorithm Visualizer** - Generates a random array and visualizes Bubble Sort and Selection Sort step by step in the console using ANSI escape codes. Tracks outer loops, inner loops, comparisons, swaps, and total duration.

**Local Weather Dashboard** - Enter a city name. The program hits the Open-Meteo geocoding API to resolve coordinates, then fetches current weather data. JSON is parsed manually without any external library.

**Robotics Component Inventory** - Add, remove, update, and search components. Data persists to a local file between sessions. The `Component` class validates that names don't contain commas (which would break the CSV format).

**Manufacturing Assembly Line** - Products enter a `Queue` (conveyor belt), go through processing, and face a random quality control check. Failed products are rejected. Uses `LinkedList` implementing the `Queue` interface for FIFO behavior.

## References

Cormen, Thomas H., et al. _Introduction to Algorithms_. 4th ed., MIT Press, 2022.

"Mermaid Flowchart Syntax." _Mermaid_, Mermaid Contributors, mermaid.js.org/syntax/flowchart.html. Accessed 22 Jun. 2025.

"Open-Meteo Geocoding API." _Open-Meteo_, open-meteo.com/en/docs/geocoding-api. Accessed 5 Nov. 2025.

"Open-Meteo Weather API." _Open-Meteo_, open-meteo.com/en/docs. Accessed 5 Nov. 2025.

Oracle. "Class HttpURLConnection." _Java SE 21 API Specification_, Oracle Corporation, docs.oracle.com/en/java/javase/21/docs/api/java.base/java/net/HttpURLConnection.html. Accessed 3 Nov. 2025.

Oracle. "Class Pattern." _Java SE 21 API Specification_, Oracle Corporation, docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/regex/Pattern.html. Accessed 15 May 2025.

Oracle. "Class Scanner." _Java SE 21 API Specification_, Oracle Corporation, docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Scanner.html. Accessed 10 Dec. 2024.

Oracle. "Interface Queue\<E\>." _Java SE 21 API Specification_, Oracle Corporation, docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Queue.html. Accessed 25 Jan. 2026.

Oracle. "Java SE Documentation." _Oracle Help Center_, Oracle Corporation, docs.oracle.com/en/java/javase/. Accessed 6 Dec. 2024.

Oracle. "Package java.nio.file." _Java SE 21 API Specification_, Oracle Corporation, docs.oracle.com/en/java/javase/21/docs/api/java.base/java/nio/file/package-summary.html. Accessed 18 Jan. 2026.

Russell, Stuart J., and Peter Norvig. _Artificial Intelligence: A Modern Approach_. 4th ed., Pearson, 2021.

## License

This is a personal learning repository. Code is open for reference and educational use.
