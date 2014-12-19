package net.sourceforge.yanonymous;

import java.util.LinkedHashMap;

public class YanoRelationModel {

  private String relationName;
  private LinkedHashMap<String, Integer> data;

  public YanoRelationModel() {
    this.relationName = null;
    this.data = new LinkedHashMap<String, Integer>();
  }

  public YanoRelationModel(String name) {
    this.relationName = name;
    this.data = new LinkedHashMap<String, Integer>();
  }

  public LinkedHashMap<String, Integer> get() {
    return data;
  }

  public void setRelationName(String themeName) {
    this.relationName = themeName;
  }

  public String getRelationName() {
    return relationName;
  }

  public void addItem(String name, Integer color) {
    data.put(name, color);
  }

  public int getColor(String name) {
    return data.get(name);
  }

}
