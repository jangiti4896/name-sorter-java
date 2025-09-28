// src/main/java/com/dnd/namesorter/NameParser.java
package com.dnd.namesorter;

import java.util.*;

final class NameParser {
  private static final Set<String> SUFFIXES = Set.of(
      "jr", "jr.", "sr", "sr.", "ii", "iii", "iv", "v"
  );

  Name parseLine(String raw, int lineNumber) {
    if (raw == null) throw new IllegalArgumentException("Line " + lineNumber + " is null.");
    String norm = raw.trim().replaceAll("\\s+", " ");
    if (norm.isEmpty()) throw new IllegalArgumentException("blank line");

    String[] parts = norm.split(" ");
    if (parts.length < 2) throw new IllegalArgumentException("missing last name: '" + raw + "'");

    // optional suffix at end
    int i = parts.length - 1;
    String suffix = null;
    String tailLower = parts[i].toLowerCase(Locale.ROOT);
    if (SUFFIXES.contains(tailLower)) {
      suffix = parts[i];
      i--;
      if (i < 1) throw new IllegalArgumentException("missing last name: '" + raw + "'");
    }

    // take last token as last name (simple rule)
    String last = parts[i];
    int givenEndIdx = i - 1;
    if (givenEndIdx < 0) throw new IllegalArgumentException("At least one given name required: '" + raw + "'");
    List<String> given = Arrays.asList(parts).subList(0, givenEndIdx + 1);

    if (given.size() > 3) {
      System.err.println("[warn] line " + lineNumber + ": >3 given names (" + given.size() + ")");
    }

    return new Name(given, last, suffix);
  }
}
