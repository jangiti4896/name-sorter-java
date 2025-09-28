package com.dnd.namesorter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public final class App {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: name-sorter <path-to-unsorted-names-list.txt>");
      System.exit(1);
    }

    Path input = Path.of(args[0]);
    Path output = Path.of("sorted-names-list.txt");

    List<String> rawLines;
    try {
      rawLines = Files.readAllLines(input, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println("[error] Unable to read input file: " + e.getMessage());
      System.exit(2);
      return;
    }

    var parser = new NameParser();
    var names = new ArrayList<Name>();
    int lineNo = 0;
    for (String line : rawLines) {
      lineNo++;
      try {
        names.add(parser.parseLine(line, lineNo));
      } catch (IllegalArgumentException ex) {
        System.err.println("[warn] Skipping line " + lineNo + ": " + ex.getMessage());
      }
    }

    names.sort(NameComparator.BY_LAST_THEN_GIVEN);
    List<String> sorted = names.stream().map(Name::fullName).toList();

    // print to screen
    sorted.forEach(System.out::println);

    // write file (overwrite)
    try {
      Files.write(output, sorted, StandardCharsets.UTF_8,
          StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      System.err.println("[error] Failed to write " + output.toAbsolutePath() + ": " + e.getMessage());
      System.exit(3);
    }
  }
}
