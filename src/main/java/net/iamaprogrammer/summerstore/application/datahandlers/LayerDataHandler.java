package net.iamaprogrammer.summerstore.application.datahandlers;

import java.util.*;

public class LayerDataHandler {
  private List<DataType> dataTypes = new ArrayList<>();
  private int dataId;

  public LayerDataHandler(DataType<?>... dataTypes) {
    this(0, dataTypes);
  }
  
  public LayerDataHandler(int dataId, DataType<?>... dataTypes) {
    this.dataId = dataId;
    for (DataType<?> dataType : dataTypes) {
      this.dataTypes.add(dataType);
    }
  }

  public<T> T get(Class<T> clazz) {
    return this.get(0, clazz);
  }

  public<T> T get(int index, Class<T> clazz) {
    return clazz.cast(this.dataTypes.get(index).getValue());
  }

  public int getDataId() {
    return this.dataId;
  }
}