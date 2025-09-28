
# Name Sorter

A simple Java 17 console application that reads a list of unsorted full names from a text file, sorts them by **last name** and **given names**, prints the sorted list to the console, and writes it to `sorted-names-list.txt`.

## Requirements
- Java 17 or higher
- Maven 3.8+

## Build Instructions
Clone the repository and build the project with Maven:

```bash
mvn clean package
```

## Run Instructions
After building, run the application with your input file (e.g., the provided sample):

```bash
java -jar target/name-sorter-java-1.0-SNAPSHOT.jar src/test/resources/unsorted-names-list.txt
```

The sorted names will be printed to the console and written to `sorted-names-list.txt` in the current directory.

## Test Instructions
Run all unit and integration tests with:

```bash
mvn test
```

## Example

**Input (`unsorted-names-list.txt`):**
```
Janet Parsons
Vaughn Lewis
Adonis Julius Archer
Shelby Nathan Yoder
Marin Alvarez
London Lindsey
Beau Tristan Bentley
Leo Gardner
Hunter Uriah Mathew Clarke
Mikayla Lopez
Frankie Conner Ritter
```

**Output (`sorted-names-list.txt`):**
```
Marin Alvarez
Adonis Julius Archer
Beau Tristan Bentley
Hunter Uriah Mathew Clarke
Leo Gardner
Vaughn Lewis
London Lindsey
Mikayla Lopez
Janet Parsons
Frankie Conner Ritter
Shelby Nathan Yoder
```

## Project Structure

```
src/
	main/
		java/
			com/
				dnd/
					namesorter/
						app/           # Main application entry point
						controller/    # CLI controller logic
						dto/           # Data transfer objects
						mapper/        # Mapping/parsing logic
						repository/    # File I/O abstraction
						service/       # Sorting logic
	test/
		java/
			com/
				dnd/
					namesorter/      # Unit and integration tests
		resources/
			unsorted-names-list.txt
			expected-sorted-names-list.txt
```

## One-liner: Build and Run

```bash
mvn clean package && java -jar target/name-sorter-java-1.0-SNAPSHOT.jar src/test/resources/unsorted-names-list.txt
```

