/*
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
 * Ez a program szabad szoftver; terjeszthetõ illetve módosítható a
 * Free Software Foundation által kiadott GNU General Public License
 * dokumentumában leírtak; akár a licenc 3-as, akár (tetszõleges) késõbbi
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
 */
package net.sourceforge.yanonymous;

import java.util.ArrayList;

public class Relation extends LocalCommunityObject implements
  java.io.Serializable {

  public Anonymous nodeA, nodeB;
  public static ArrayList<YanoRelationModel> relationModels;
  public String relationTheme;

  public Relation(Anonymous nodeA, Anonymous nodeB, String relationName) {
    super(null);

    this.nodeA = nodeA;
    this.nodeB = nodeB;

    Relation.relationModels = YourWorldView.YanoRelationModels;

    this.name = relationName;
    this.relationTheme = this.name;
  }

  public int getColor() {
    for (YanoRelationModel r : relationModels) {
      if (relationTheme.equals(r.getRelationName())) {
        return r.getColor(name);
      }
    }

    return relationModels.get(0).getColor(name);
  }

  public void next() {
    String pKey = null;
    String nName = null;

    for (YanoRelationModel r : relationModels) {
      if (relationTheme.equals(r.getRelationName())) {
        for (String key : r.get().keySet()) {
          if (name.equals(pKey)) {
            nName = key;
            break;
          }

          pKey = key;
        }
      }
    }

    if (nName == null) {
      for (YanoRelationModel r : relationModels) {
        if (relationTheme.equals(r.getRelationName())) {
          name = r.get().keySet().iterator().next();
        }
      }
    } else {
      name = nName;
    }
  }
}
