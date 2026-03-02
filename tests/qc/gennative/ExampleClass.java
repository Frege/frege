package tests.qc.gennative;

import java.util.List;

public class ExampleClass<T extends java.lang.Comparable<T>> {

  public ExampleClass(T value) throws Exception {}

  public static final String staticField = "";
  public final int memberField = 0;

  public String getValue() {
    return "";
  }

  public int primitive() {
    return 0;
  }

  public List<? extends Number> getWildcardList() {
    return List.of();
  }
  public List getRawList() {
     return List.of();
  }

  public void setValue(T value) { }

  public <E extends Number> void processParameterizedType(List<E> value) throws Exception { }

  public List<String>[] getArrayOfLists() {
      return (List<String>[]) new List<?>[3];
  }

  public int[] getIntArray() {
      return new int[]{0,1};
  }

  public static int getInt() {
    return 0;
  }
}

