package com.dnd.namesorter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

final class NameParserTest {
  private final NameParser parser = new NameParser();

  @Test void parsesTwoPart() {
    Name n = parser.parseLine("Marin Alvarez", 1);
    assertEquals("Alvarez", n.lastName());
    assertEquals("Marin", n.givenNames().get(0));
  }

  @Test void parsesMultiGiven() {
    Name n = parser.parseLine("Hunter Uriah Mathew Clarke", 2);
    assertEquals("Clarke", n.lastName());
    assertEquals(3, n.givenNames().size());
  }

  @Test void collapsesWhitespace() {
    Name n = parser.parseLine("  Beau   Tristan   Bentley  ", 3);
    assertEquals("Bentley", n.lastName());
  }

  @Test void rejectsSingleToken() {
    assertThrows(IllegalArgumentException.class, () -> parser.parseLine("Madonna", 4));
  }
}
