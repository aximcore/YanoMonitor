/*
 * Anonymous.java
 *
 * YANonymous
 * https://sourceforge.net/projects/yanonymous/
 *
 * Copyright (C) 2010, Dr. Bátfai Norbert
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Free Software Foundation által kiadott GNU General Public License
 * változata szerint.
 *
 * Ez a program abban a reményben kerül közreadásra, hogy hasznos lesz,
 * de minden egyéb GARANCIA NÉLKÜL, az ELADHATÓSÁGRA vagy VALAMELY CÉLRA
 * VALÓ ALKALMAZHATÓSÁGRA való származtatott garanciát is beleértve.
 * További részleteket a GNU General Public License tartalmaz.
 *
 * A felhasználónak a programmal együtt meg kell kapnia a GNU General
 * Public License egy példányát; ha mégsem kapta meg, akkor
 * tekintse meg a <http://www.gnu.org/licenses/> oldalon.
 *
 *
 *
 * Version history:
 *
 * 0.0.1, 2013.szept.26., az Eclipse projekt átírása IDE független Maven projektbe.
 * http://progpater.blog.hu/2013/09/17/o_mondd_te_kit_valasztanal_525
 *
 */
package net.sourceforge.yanonymous;

import java.util.ArrayList;
import android.graphics.drawable.Drawable;
public class Anonymous extends LocalCommunityObject {

  public boolean me = false;
  public static ArrayList<YanoTheme> themes;
  public YanoTheme currentTheme;
  public Anonymous parent;
  public ArrayList<Anonymous> children = new ArrayList<Anonymous>();

  private float base_x, base_y;
  private Drawable mImage;
  
  public Anonymous(Anonymous parent, String name, float x, float y, String theme) {
    this(parent, false, name, x, y, theme);
    base_x=x;
    base_y=y;
  }

  public Anonymous(Anonymous parent, boolean me, String name, float x, float y, String theme) {
    super(name, x, y, theme);
    base_x=x;
    base_y=y;
    this.me = me;
    Anonymous.themes = YourWorldView.YanoThemes;
    this.parent = parent;
    if(this.parent != null) this.parent.children.add(this);

    for (YanoTheme t : themes) {
      if (theme.equals(t.getThemeName())) {
        this.currentTheme = t;
      }
    }
  }

  public Drawable getImage()
  {
	  return mImage;
  }
 
  public void setImage(Drawable _image)
  {
	  this.mImage = _image;
  }
  
  public String getName()
  {
	  return name;
  }
  
  public String getNext() {
	    String pKey = null;
	    String nName = null;
        String _tmpName = name;
	    for (YanoTheme t : themes) {
	      if (theme.equals(t.getThemeName())) {
	        for (String key : t.get().keySet()) {
	          if (_tmpName.equals(pKey)) {
	            nName = key;
	            break;
	          }

	          pKey = key;
	        }
	      }
	    }

	    if (nName == null) {
	      for (YanoTheme t : themes) {
	        if (theme.equals(t.getThemeName())) {
	        	_tmpName = t.get().keySet().iterator().next();
	        }
	      }
	    } else {
	    	_tmpName = nName;
	    }
	    return _tmpName;
	  }
  	  
  public void setTheme(String actualTheme) {
    theme = actualTheme;

    for (YanoTheme t : themes) {
      if (theme.equals(t.getThemeName())) {
        this.currentTheme = t;
      }
    }
  }


  public void updateCoordsOnZooming(float z, boolean zoom) {
    if(!zoom){
    base_x=x;
    base_y=y;  
    x = x*z;
    y = y*z;
    }else{
    x=base_x;
    y=base_y;
    }


  }
  
  public void updateCoords(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public int getColor() {
    return currentTheme.getColor(name);
  }

  public void next() {
    String pKey = null;
    String nName = null;

    for (YanoTheme t : themes) {
      if (theme.equals(t.getThemeName())) {
        for (String key : t.get().keySet()) {
          if (name.equals(pKey)) {
            nName = key;
            break;
          }

          pKey = key;
        }
      }
    }

    if (nName == null) {
      for (YanoTheme t : themes) {
        if (theme.equals(t.getThemeName())) {
          name = t.get().keySet().iterator().next();
        }
      }
    } else {
      name = nName;
    }
  }
}
