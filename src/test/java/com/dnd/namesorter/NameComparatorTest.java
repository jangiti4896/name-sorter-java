package com.dnd.namesorter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class NameComparatorTest {
  @Test void byLastThenGiven() {
    var a = new Name(List.of("Adonis","Julius"), "Archer");
    var b = new Name(List.of("Beau","Tristan"), "Bentley");
    assertTrue(NameComparator.BY_LAST_THEN_GIVEN.compare(a,b) < 0);
  }

  @Test void tieBreakByGiven() {
    var a = new Name(List.of("Janet"), "Parsons");
    var b = new Name(List.of("Jason"), "Parsons");
    assertTrue(NameComparator.BY_LAST_THEN_GIVEN.compare(a,b) < 0);
  }

  @Test void caseInsensitive() {
    var a = new Name(List.of("leo"), "gardner");
    var b = new Name(List.of("Leo"), "Gardner");
    assertEquals(0, NameComparator.BY_LAST_THEN_GIVEN.compare(a,b));
  }
}
