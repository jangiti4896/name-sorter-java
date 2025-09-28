package com.dnd.namesorter;

import java.util.List;
import java.util.StringJoiner;

public record Name(List<String> givenNames, String lastName, String suffix) {

  public Name {
    if (givenNames == null || givenNames.isEmpty())
      throw new IllegalArgumentException("At least one given name required.");
    if (lastName == null || lastName.isBlank())
      throw new IllegalArgumentException("Last name required.");
    givenNames = List.copyOf(givenNames);
    suffix = (suffix != null && !suffix.isBlank()) ? suffix : null;
  }

  public Name(List<String> givenNames, String lastName) {
    this(givenNames, lastName, null);
  }

  public String fullName() {
    var sj = new StringJoiner(" ");
    givenNames.forEach(sj::add);
    sj.add(lastName);
    if (suffix != null) sj.add(suffix);
    return sj.toString();
  }
}
