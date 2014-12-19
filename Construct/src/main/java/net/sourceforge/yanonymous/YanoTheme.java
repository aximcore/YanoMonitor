package net.sourceforge.yanonymous;

import android.graphics.drawable.Drawable;
import java.util.LinkedHashMap;

public class YanoTheme {

  private String themeName;
  private String themeType;

  private LinkedHashMap<String, Integer> data;
  private LinkedHashMap<String, Drawable> themeImages;

  public YanoTheme() {
    this.themeName = null;
    this.themeType = null;
    this.data = new LinkedHashMap<String, Integer>();
    this.themeImages = new LinkedHashMap<String, Drawable>();
  }

  public YanoTheme(String name) {
    this.themeName = name;
    this.themeType = null;
    this.data = new LinkedHashMap<String, Integer>();
    this.themeImages = new LinkedHashMap<String, Drawable>();
  }

  public YanoTheme(String name, String type) {
    this.themeName = name;
    this.themeType = type;
    this.data = new LinkedHashMap<String, Integer>();
    this.themeImages = new LinkedHashMap<String, Drawable>();
  }

  public LinkedHashMap<String, Integer> get() {
    return data;
  }

  public void setThemeName(String themeName) {
    this.themeName = themeName;
  }

  public String getThemeName() {
    return themeName;
  }

  public void setThemeType(String type) {
    this.themeType = type;
  }

  public String getThemeType() {
    return themeType;
  }

  public void addThemeImage(String name, Drawable image) {
    themeImages.put(name, image);
  }

  public LinkedHashMap<String, Drawable> getThemeImages() {
    return themeImages;
  }

  public void addItem(String name, Integer color) {
    data.put(name, color);
  }

  public int getColor(String name) {
    return data.get(name);
  }

}
