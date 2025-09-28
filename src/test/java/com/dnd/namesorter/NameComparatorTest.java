package com.dnd.namesorter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class NameComparatorTest {
  @Test void byLastThenGiven() {
    Name a = new Name(List.of("Adonis","Julius"), "Archer");
    Name b = new Name(List.of("Beau","Tristan"), "Bentley");
    assertTrue(NameComparator.BY_LAST_THEN_GIVEN.compare(a,b) < 0);
  }

  @Test void tieBreakByGiven() {
    Name a = new Name(List.of("Janet"), "Parsons");
    Name b = new Name(List.of("Jason"), "Parsons");
    assertTrue(NameComparator.BY_LAST_THEN_GIVEN.compare(a,b) < 0);
  }

  @Test void caseInsensitive() {
    Name a = new Name(List.of("leo"), "gardner");
    Name b = new Name(List.of("Leo"), "Gardner");
    assertEquals(0, NameComparator.BY_LAST_THEN_GIVEN.compare(a,b));
  }
}
