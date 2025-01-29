# Roketin Time Converter (CHALLENGE 1)

This project converts time from Earth to a fictional planet called Roketin using the **Strategy Pattern**.

## Overview

The program reads an Earth time input from the user and converts it to the corresponding time on Roketin. The conversion is performed using a time converter strategy. The strategy pattern is used here to allow for different time conversion strategies to be interchangeable without modifying the context.


### Explanation of the Strategy Pattern

- **Main Class**: The `Main` class is responsible for orchestrating the flow of the program. It interacts with `TimeValidator` to validate the Earth time and uses `TimeConverter` (interface) to perform the conversion. In the current implementation, `RoketinTimeConverter` is used as the strategy to convert Earth time into Roketin time.

- **TimeValidator Class**: This class ensures the user inputs a valid time (formatted correctly). It is used by the `Main` class to retrieve and validate the Earth time input.

- **TimeConverter Interface**: This is the Strategy interface that defines the method `convert()`. It is implemented by different concrete strategies (like `RoketinTimeConverter`) to allow interchangeable strategies for time conversion.

- **RoketinTimeConverter Class**: This class implements the `TimeConverter` interface and provides the actual conversion logic from Earth time to Roketin time.

- **Time Class**: This is a simple data class that stores time (hours, minutes, and seconds) and includes methods like `toString()` for formatting and `toSeconds()` for easier time calculations.

### How the Strategy Pattern Works:

1. The **Strategy** is defined by the `TimeConverter` interface, which declares the method `convert()` that all time conversion classes must implement.

2. The **Concrete Strategy**, in this case `RoketinTimeConverter`, implements the `convert()` method, which contains the specific algorithm to convert Earth time into Roketin time.

3. The **Context**, which is the `Main` class, uses the `TimeConverter` interface to call the conversion method, but it is unaware of which specific strategy (e.g., `RoketinTimeConverter`) is being used.

## How to Run

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Compile and run the application using your preferred IDE (Intellij).

    ```bash
    mvn clean install
    ```
4. On Main.Java right click and choose run
4. Enter a valid time in the format `hh:mm:ss` when prompted.
5. The program will output the equivalent time on Roketin.

## Running Unit Tests with JUnit

This project uses **JUnit 5** for unit testing. To run the unit tests, follow the steps below:

1. Ensure that your project is set up with **JUnit 5** and **Maven** (or any other build tool of your choice).

2. **Using Maven**:

   To run the tests with Maven and generate a test report, run the following commands:

    ```bash
    mvn clean test
    ```

   This will compile the tests, execute them, and generate the results in the default target directory. Maven Surefire Plugin will automatically handle the execution of JUnit tests and generate a report.

3. **Surefire Plugin Explanation**:

   The **Maven Surefire Plugin** is used to run tests in the project. It is compatible with various testing frameworks, including JUnit. When you run `mvn clean test`, Surefire automatically detects the test classes (those with `@Test` annotations) and runs them.

4. **JUnit 5 Unit Test**:

   The unit test for the time conversion logic looks like this:

    ```java
    @Test
    void testConvertEarthTimeToRoketinTime() {
        Time earthTime = new Time(12, 0, 0);
        Time expectedRoketinTime = new Time(05, 0, 0);

        Time actualRoketinTime = converter.convert(earthTime);

        assertEquals(expectedRoketinTime, actualRoketinTime);
    }
    ```

   This test verifies that the conversion from Earth time to Roketin time works as expected. It compares the expected Roketin time (`05:00:00`) with the actual result from the converter.

### How the Unit Test Works

- **Test Setup**: A valid Earth time (`12:00:00`) is provided as input.
- **Expected Result**: The expected Roketin time is `1200:00:00`.
- **Test Execution**: The `convert()` method from the `RoketinTimeConverter` is called to get the actual conversion.
- **Assertion**: The `assertEquals()` method is used to check that the expected and actual results match.

This unit test ensures that the `RoketinTimeConverter` is functioning correctly by comparing the expected converted time with the output from the converter.
