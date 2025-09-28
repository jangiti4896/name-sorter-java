package com.dnd.namesorter;

import java.util.List;
import java.util.StringJoiner;

final class Name {
  private final List<String> givenNames;
  private final String lastName;

  Name(List<String> givenNames, String lastName) {
    if (givenNames == null || givenNames.isEmpty())
      throw new IllegalArgumentException("At least one given name required.");
    if (lastName == null || lastName.isBlank())
      throw new IllegalArgumentException("Last name required.");
    this.givenNames = List.copyOf(givenNames);
    this.lastName = lastName;
  }

  List<String> givenNames() { return givenNames; }
  String lastName() { return lastName; }

  String fullName() {
    StringJoiner sj = new StringJoiner(" ");
    givenNames.forEach(sj::add);
    sj.add(lastName);
    return sj.toString();
  }
}
