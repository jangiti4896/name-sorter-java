package com.dnd.namesorter;

import java.util.Arrays;
import java.util.List;

final class NameParser {
  Name parseLine(String raw, int lineNumber) {
    if (raw == null) throw new IllegalArgumentException("Line " + lineNumber + " is null.");
    String norm = raw.trim().replaceAll("\\s+", " ");
    if (norm.isEmpty()) throw new IllegalArgumentException("blank line");
    String[] parts = norm.split(" ");
    if (parts.length < 2) throw new IllegalArgumentException("missing last name: '" + raw + "'");
    String last = parts[parts.length - 1];
    List<String> given = Arrays.asList(parts).subList(0, parts.length - 1);
    if (given.size() > 3) {
      System.err.println("[warn] line " + lineNumber + ": >3 given names (" + given.size() + ")");
    }
    return new Name(given, last);
  }
}
