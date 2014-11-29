/*
 * Unidentifiable.java
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
 * Ez a program szabad szoftver; terjeszthető illetve módosítható a
 * Free Software Foundation által kiadott GNU General Public License
 * dokumentumában leírtak; akár a licenc 3-as, akár (tetszőleges) későbbi
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

interface Unidentifiable {

    void next();

    int getColor();

    interface PoliticalAffinity extends Unidentifiable {

        @SuppressWarnings("serial")
        public static final java.util.Map<String, Integer> partyColors = java.util.Collections
                .unmodifiableMap(new java.util.LinkedHashMap<String, Integer>() {
            {
                put("Party",
                        android.graphics.Color.argb(0xb0, 0x01, 0x0a, 0xbf));
                put("Fidesz",
                        android.graphics.Color.argb(0xb0, 0xef, 0x9a, 0x2b));
                put("MSZP",
                        android.graphics.Color.argb(0xb0, 0xef, 0x07, 0x0c));
                put("KDNP",
                        android.graphics.Color.argb(0xb0, 0xf1, 0xec, 0x5b));
                put("Jobbik",
                        android.graphics.Color.argb(0xb0, 0x4d, 0x53, 0x53));
                put("LMP",
                        android.graphics.Color.argb(0xb0, 0x1d, 0x8e, 0x06));
                put("DK",
                        android.graphics.Color.argb(0xb0, 0x82, 0x34, 0x34));
                put("Együtt-PM",
                        android.graphics.Color.argb(0xb0, 0xB0, 0x66, 0x38));
                put("YANO",
                        android.graphics.Color.argb(0xb0, 0x80, 0xe3, 0x05));

            }
        });
    }

    interface Archetype extends Unidentifiable {

        @SuppressWarnings("serial")
        public static final java.util.Map<String, Integer> relationship = java.util.Collections
                .unmodifiableMap(new java.util.LinkedHashMap<String, Integer>() {
            {
                put("Relationship",
                        android.graphics.Color.argb(0x80, 0x21, 0xaa, 0xbf));
                put("Csajom",
                        android.graphics.Color.argb(0x80, 0xef, 0x9a, 0x2b));
                put("Fiúm",
                        android.graphics.Color.argb(0x80, 0xef, 0x07, 0x0c));
                put("Muter",
                        android.graphics.Color.argb(0x80, 0xf1, 0xec, 0x5b));
                put("Fater",
                        android.graphics.Color.argb(0x80, 0x4d, 0x53, 0x53));
                put("Tesó",
                        android.graphics.Color.argb(0x80, 0x1d, 0x8e, 0x06));
                put("Szomszéd",
                        android.graphics.Color.argb(0x80, 0x80, 0xe3, 0x05));

            }
        });
    }
}
