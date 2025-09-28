package com.dnd.namesorter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public final class App {
  public static void main(String[] args) {
    System.exit(run(args));
  }

  static int run(String[] args) {
    if (args.length < 1 || args.length > 2) {
      System.err.println("Usage: name-sorter <input> [--out=PATH]");
      return 1;
    }

    Path input = Path.of(args[0]);
    Path output = Path.of("sorted-names-list.txt");

    if (args.length == 2) {
      if (args[1].startsWith("--out=")) {
        output = Path.of(args[1].substring("--out=".length()));
      } else {
        System.err.println("Usage: name-sorter <input> [--out=PATH]");
        return 1;
      }
    }

    final List<String> rawLines;
    try {
      rawLines = Files.readAllLines(input, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.err.println("[error] Unable to read input file: " + e.getMessage());
      return 2;
    }

    var parser = new NameParser();
    var names = new ArrayList<Name>();
    int lineNo = 0, invalid = 0;
    for (String line : rawLines) {
      lineNo++;
      try {
        names.add(parser.parseLine(line, lineNo));
      } catch (IllegalArgumentException ex) {
        invalid++;
      }
    }
    if (invalid > 0) System.err.println("[warn] Skipped " + invalid + " invalid line(s).");

    if (names.isEmpty()) {
      System.err.println("[error] No valid names found. Writing message to output file.");
      try {
        Files.writeString(output, "No valid names found\n", StandardCharsets.UTF_8,
            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
      } catch (IOException e) {
        System.err.println("[error] Could not write output file: " + e.getMessage());
        return 3;
      }
      return 4;
    }

    names.sort(NameComparator.BY_LAST_THEN_GIVEN);
    List<String> sorted = names.stream().map(Name::fullName).toList();

    sorted.forEach(System.out::println);

    try {
      Files.write(output, sorted, StandardCharsets.UTF_8,
          StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      System.err.println("[error] Failed to write " + output.toAbsolutePath() + ": " + e.getMessage());
      return 3;
    }
    return 0;
  }
}
