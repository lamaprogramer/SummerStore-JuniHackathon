package net.iamaprogrammer.summerstore.application.datahandlers;

public class DataType<T> {
  private T value;

  public DataType(T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }

  public void setValue(T value) {
    this.value = value;
  }
}