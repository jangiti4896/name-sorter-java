package com.dnd.namesorter;

import java.util.Comparator;
import java.util.List;

final class NameComparator implements Comparator<Name> {
  static final NameComparator BY_LAST_THEN_GIVEN = new NameComparator();
  private NameComparator() {}

  @Override public int compare(Name a, Name b) {
    int last = String.CASE_INSENSITIVE_ORDER.compare(a.lastName(), b.lastName());
    if (last != 0) return last;
    return compareTokens(a.givenNames(), b.givenNames());
  }

  private static int compareTokens(List<String> a, List<String> b) {
    int min = Math.min(a.size(), b.size());
    for (int i = 0; i < min; i++) {
      int c = String.CASE_INSENSITIVE_ORDER.compare(a.get(i), b.get(i));
      if (c != 0) return c;
    }
    return Integer.compare(a.size(), b.size());
  }
}
