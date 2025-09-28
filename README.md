
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

This will produce a runnable JAR in `target/`.

---

## Run Instructions
After building, run the application with your input file (e.g., the provided sample):

```bash
java -jar target/name-sorter-1.0.0.jar ./unsorted-names-list.txt
```

---

## Example Input

`unsorted-names-list.txt`:

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

---

The sorted names will be printed to the console and written to `sorted-names-list.txt` in the current directory.


---

## Example Output (`sorted-names-list.txt`)

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

---

## Tests

Run all tests:

```bash
mvn test
```

This executes:

- **NameParserTest** – two-part, multi-given, whitespace, rejection  
- **NameComparatorTest** – last/given, tie-breaks, case-insensitive  
- **NameSortingWithSuffixesTest** – ensures `Jr.` sits between `da Silva` and `Smith-Jones`  
- **NameComparatorBeethovenTest** – ensures `Beethoven` sorts correctly among `Bentley` and `Beta`  

---

## Code Structure

```
src/
 ├─ main/java/com/dnd/namesorter/
 │   ├─ App.java              ← console entrypoint
 │   ├─ Name.java             ← immutable name record
 │   ├─ NameParser.java       ← parses raw lines into Name objects
 │   └─ NameComparator.java   ← sorts Name objects
 └─ test/java/com/dnd/namesorter/
     ├─ NameParserTest.java
     ├─ NameComparatorTest.java
     ├─ NameSortingWithSuffixesTest.java
     └─ NameComparatorBeethovenTest.java
```
---

## One-liner: Build and Run

```bash
mvn clean package && java -jar target/name-sorter-1.0.0.jar src/test/resources/unsorted-names-list.txt
```

