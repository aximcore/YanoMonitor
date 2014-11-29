/*
 * YourWorldView.java
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


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class YourWorldView extends android.view.View {
  public int coreid = 0;
  private static float gx = 0;
  private static float gy = 0;
  protected float width;
  protected float height;
  private static boolean first = true, newNode = false;
  protected float tx, ty;
  private static List<Anonymous> anonyms = new ArrayList<Anonymous>();
  Anonymous selectedA = null;
  private static List<Relation> relations = new ArrayList<Relation>();
  Relation selectedR = null;
  private static Paint bgrndPaint = new Paint();
  private static Paint pagerBgrndPaint = new Paint();
  private static Paint generalNodePaint = new Paint();
  private static Paint generalNodeBrdPaint = new Paint();
  private static Paint generalEdgePaint = new Paint();
  private static Paint deleteRelationPaint = new Paint();
  private static Paint textPaint = new Paint();
  private static Paint textBrdPaint = new Paint();
  private static int textSize = 44;
  private static Paint edgeTextPaint = new Paint();
  private static boolean deleting = false;
  private static int oldColor;
  private static boolean moveAnonymus = false;
  private static boolean deleteRelations = false;
  protected float deleteLineX, deleteLineY;

  public YourWorldView(Context context) {
    super(context);

    cinit();

  }


  public YourWorldView(Context context,
                       AttributeSet attrs) {
    super(context, attrs);

    cinit();

  }

  public YourWorldView(Context context,
                       AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    cinit();

  }

  private static android.graphics.drawable.Drawable logo;

  public static List<Anonymous> getAnonyms() {
    return anonyms;
  }

  public static void setAnonyms(List<Anonymous> aList) {
    anonyms = aList;
  }

  public static void setRelations(List<Relation> rList) {
    relations = rList;
  }

  public static List<Relation> getRelations() {
    return relations;
  }

  public static void clearAll() {
    for (int i = anonyms.size() - 1; i > 0; i--) {
      anonyms.remove(i);
    }

    anonyms.get(0).name = "Party";

    relations.clear();

  }

  public static void setDeleting(boolean b) {
    deleting = b;
  }

  public static void setMoveAnonymus(boolean m) {
    moveAnonymus = m;
  }


  public static void setDeleteRelations(boolean d) {
    deleteRelations = d;
  }

  public static void setCustomizing() {
    oldColor = bgrndPaint.getColor();
  }

  public static int getRed() {
    return Color.red(bgrndPaint.getColor());
  }

  public static int getGreen() {
    return Color.green(bgrndPaint.getColor());
  }

  public static int getBlue() {
    return Color.blue(bgrndPaint.getColor());
  }

  public static void setNewColor(int[] color) {
    bgrndPaint.setColor(Color.rgb(color[0], color[1], color[2]));
  }

  public static void restoreBgColor() {
    bgrndPaint.setColor(oldColor);
  }

  public void delete(float x, float y) {
    Anonymous a = nearestAnonymous(gx + x, gy + y);

    Relation tmpR = null;

    if (a != anonyms.get(0)) {
      for (int i = 0; i < relations.size(); i++) {
        if ((relations.get(i).nodeA == a)) {
          tmpR.nodeB = relations.get(i).nodeB;
          tmpR.name = "Relationship";
          relations.add(tmpR);
          relations.remove(i);
          i--;
        } else if ((relations.get(i).nodeB == a)) {
          tmpR = relations.get(i);
          relations.remove(i);
          i--;
        }
      }

      anonyms.remove(a);
    }

  }

  @Override
  protected void onSizeChanged(int newx, int newy, int x, int y) {

    super.onSizeChanged(newx, newy, x, y);

    width = newx;
    height = newy;

    if (first) {

      first = false;
      anonyms.add(new Anonymous(true, "Party", gx + width / 2, gy
          + height / 2,coreid++)) ;

      int resId = getResources().getIdentifier("ic_launcher", "drawable",
          "net.sourceforge.yanonymous");
      logo = getResources().getDrawable(resId);

    }
  }

  private void cinit() {

    pagerBgrndPaint.setColor(Color.rgb(0x77, 0xcc, 0xff));
    pagerBgrndPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    pagerBgrndPaint.setAntiAlias(true);

    bgrndPaint.setColor(Color.rgb(0x33, 0x66, 0xff));
    bgrndPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    bgrndPaint.setAntiAlias(true);

    generalNodePaint.setColor(Color.argb(0xb0, 0x00, 0x22, 0xee));
    generalNodePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    generalNodePaint.setAntiAlias(true);

    generalNodeBrdPaint.setColor(Color.rgb(0xdd, 0xdd, 0x00));
    generalNodeBrdPaint.setStyle(Paint.Style.STROKE);
    generalNodeBrdPaint.setAntiAlias(true);
    generalNodeBrdPaint.setStrokeWidth(3);

    generalEdgePaint.setColor(Color.argb(0xb0, 0x21, 0xaa, 0xbf));
    generalEdgePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    generalEdgePaint.setAntiAlias(true);
    generalEdgePaint.setStrokeWidth(25);


    deleteRelationPaint.setColor(Color.RED);
    deleteRelationPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    deleteRelationPaint.setAntiAlias(true);
    deleteRelationPaint.setStrokeWidth(10);

    textPaint.setColor(Color.YELLOW);
    textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    textPaint.setAntiAlias(true);
    textPaint.setTextAlign(Paint.Align.CENTER);
    textPaint.setTextSize(textSize);

    textBrdPaint.setColor(Color.rgb(0x41, 0x41, 0x41));
    textBrdPaint.setStyle(Paint.Style.STROKE);
    textBrdPaint.setAntiAlias(true);
    textBrdPaint.setStrokeWidth(1);
    textBrdPaint.setTextAlign(Paint.Align.CENTER);
    textBrdPaint.setTextSize(textSize);

    edgeTextPaint.setColor(Color.argb(0xc0, 0x0e, 0x0d, 0x0d));
    edgeTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    edgeTextPaint.setAntiAlias(true);
    edgeTextPaint.setTextAlign(Paint.Align.CENTER);
    edgeTextPaint.setTextSize(textSize / 2);

  }

  public float d(float x1, float x2, float y1, float y2) {

    return (x1 - y1) * (x1 - y1) + (x2 - y2) * (x2 - y2);
  }

  private Anonymous nearestAnonymous(float x, float y) {

    Anonymous r = null;
    float max = /* Float.MAX_VALUE */size * size / 2, m;

    for (Anonymous a : anonyms) {

      if ((m = d(a.x, a.y, x, y)) < max) {
        max = m;
        r = a;
        r.distexy = m;
      }
    }
    return r;
  }

  private void deleteRelation(float deleteStartX, float deleteStartY, float deleteStopX, float deleteStopY) {
    List<Relation> newRelations = new ArrayList<Relation>();
    for (Relation a : relations) {
      if (!Intersect.linesIntersect(deleteStartX, deleteStartY, deleteStopX, deleteStopY, a.nodeA.x, a.nodeA.y, a.nodeB.x, a.nodeB.y)) {
        newRelations.add(a);
      }
    }
    relations = newRelations;
  }

  private Relation nearestRelation(float x, float y) {

    Relation r = null;
    float max = /* Float.MAX_VALUE */size * size / 2, m;

    for (Relation a : relations) {
      if ((m = d((a.nodeA.x + a.nodeB.x) / 2,
          (a.nodeA.y + a.nodeB.y) / 2, x, y)) < max) {
        max = m;
        r = a;
        r.distexy = m;
      }
    }
    return r;
  }

  protected boolean moved = false;
  protected float mx, my;
  protected float sgx, sgy;
  protected float startx, starty;

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    float x = event.getX();
    float y = event.getY();

    if (event.getAction() == MotionEvent.ACTION_DOWN) {

      if (deleting) {
        delete(x, y);
      } else if (deleteRelations) {

        deleteLineY = y;
        deleteLineX = x;
        tx = x;
        ty = y;
        deleteRelationPaint.setColor(Color.RED);

      } else if (moveAnonymus) {
        if (nearestAnonymous(gx + x, gy + y) != null)
          selectedA = nearestAnonymous(gx + x, gy + y);

      } else {

        startx = x;
        starty = y;

        selectedA = nearestAnonymous(gx + x, gy + y);
        selectedR = nearestRelation(gx + x, gy + y);

        if (selectedA != null && selectedR != null) {
          if (selectedA.distexy < selectedR.distexy) {
            selectedR = null;
          } else {
            selectedA = null;
          }
        }
      }


    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

      if (deleteRelations) {
        tx = x ;
        ty = y ;
      } else if (selectedA == null && selectedR == null) {

        if (!moved) {
          mx = x;
          my = y;
          sgx = gx;
          sgy = gy;

        } else {

          gx = sgx - x + mx;
          gy = sgy - y + my;
        }
      }

      if (moveAnonymus && selectedA != null) {
        selectedA.updateCoords(gx + x, gy + y);
      } else {
        if (d(x, y, startx, starty) > 8.0)
          moved = true;

        if (selectedA != null) {
          newNode = true;
        } else {
          newNode = false;
        }

        tx = x;
        ty = y;
      }


    } else if (event.getAction() == MotionEvent.ACTION_UP) {

      if (deleting) {
        deleting = false;
      } else if (moveAnonymus) {
        selectedA = null;
      } else if (deleteRelations) {

        deleteRelation(gx + deleteLineX, gy + deleteLineY,gx + tx,gy+ ty);

        deleteRelationPaint.setColor(Color.rgb(0x33, 0x66, 0xff));

      } else if (moved) {
        Anonymous nearest;
        if (selectedA != null && (nearest = nearestAnonymous(gx + x, gy + y)) != null && nearest != selectedA) {
          relations.add(new Relation(selectedA, nearest));

        } else if (newNode && selectedA != null) {

          if (d(selectedA.x, selectedA.y, gx + x, gy + y) > 4 * size * size) {
            Anonymous a = new Anonymous("Party", gx + x, gy + y,coreid++);
            anonyms.add(a);

            relations.add(new Relation(selectedA, a));
          }

        }


      } else {

        if (selectedR != null) {

          selectedR.next();

        } else if (selectedA != null) {

          selectedA.next();

        }
      }

      newNode = false;
      moved = false;

    }

    invalidate();

    return true;
  }

  Rect boundsRect = new Rect();
  int size;
  Path edgePath = new Path();

  @Override
  protected void onDraw(Canvas canvas) {

    canvas.drawRect(0, 0, width, height, bgrndPaint);

    generalEdgePaint.setStrokeWidth(25);
    for (Relation r : relations) {

      generalEdgePaint.setColor(r.getColor());
      canvas.drawLine(-gx + r.nodeA.x, -gy + r.nodeA.y, -gx + r.nodeB.x,
          -gy + r.nodeB.y, generalEdgePaint);

      if (r.nodeB.x < r.nodeA.x) {
        edgePath.reset();
        edgePath.moveTo(-gx + r.nodeB.x, -gy + r.nodeB.y);
        edgePath.lineTo(-gx + r.nodeA.x, -gy + r.nodeA.y);
        canvas.drawTextOnPath(r.name, edgePath, 6, 6, edgeTextPaint);
      } else {
        edgePath.reset();
        edgePath.moveTo(-gx + r.nodeA.x, -gy + r.nodeA.y);
        edgePath.lineTo(-gx + r.nodeB.x, -gy + r.nodeB.y);
        canvas.drawTextOnPath(r.name, edgePath, 6, 6, edgeTextPaint);
      }
    }

    if (selectedA != null && newNode && !deleteRelations) {
      canvas.drawLine(-gx + selectedA.x, -gy + selectedA.y, tx, ty, generalEdgePaint);
    }

    if (deleteRelations) {
      canvas.drawLine(deleteLineX, deleteLineY, tx, ty, deleteRelationPaint);
    }

    for (Anonymous a : anonyms) {

      textPaint.getTextBounds(a.name, 0, a.name.length(), boundsRect);
      size = boundsRect.width() / 2 + 10;

      generalNodePaint.setColor(a.getColor());
      canvas.drawCircle(-gx + a.x, -gy + a.y, size, generalNodePaint);
      canvas.drawCircle(-gx + a.x, -gy + a.y, size, generalNodeBrdPaint);

      if (a.me) {

        logo.setBounds((int) (-gx + a.x), (int) (-gy + a.y - 100 / 2),
            (int) (-gx + a.x + 100 / 2), (int) (-gy + a.y));
        logo.draw(canvas);
      }

      canvas.drawText(a.name, -gx + a.x, -gy + a.y - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
      canvas.drawText(a.name, -gx + a.x, -gy + a.y - ((textPaint.descent() + textPaint.ascent()) / 2), textBrdPaint);

    }

    canvas.clipRect(width - width / 4, height - height / 6, width - 5, height - 5);
    canvas.drawRect(width - width / 4, height - height / 6, width - 5, height - 5, pagerBgrndPaint);

    generalEdgePaint.setStrokeWidth(4);
    for (Relation r : relations) {

      generalEdgePaint.setColor(r.getColor());
      canvas.drawLine(width - width / 4 + (-gx + r.nodeA.x) / 10,
          height - height / 6 + (-gy + r.nodeA.y) / 10,
          width - width / 4 + (-gx + r.nodeB.x) / 10,
          height - height / 6 + (-gy + r.nodeB.y) / 10,
          generalEdgePaint);
    }

    for (Anonymous a : anonyms) {

      generalNodePaint.setColor(a.getColor());
      canvas.drawCircle(
          width - width / 4 + (-gx + a.x) / 10,
          height - height / 6 + (-gy + a.y) / 10,
          10, generalNodePaint);
      canvas.drawCircle(-gx + a.x, -gy + a.y, 10, generalNodeBrdPaint);

    }

    canvas.drawRect(width - width / 4, height - height / 6, width - 5, height - 5, generalNodeBrdPaint);

  }
}
