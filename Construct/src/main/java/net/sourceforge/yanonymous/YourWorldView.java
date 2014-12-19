/*
 * YourWorldView.java
 *
 * YANonymous
 * https://sourceforge.net/projects/yanonymous/
 *
 * Copyright (C) 2010, Dr. B�tfai Norbert
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
 * változata szerint.
 *

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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.PorterDuff.Mode;
import android.content.Intent;
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap.Config; 
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import static android.util.Log.i;

public class YourWorldView extends android.view.View {

    public static int i=0;
    public static String[] si = new String[30];
    private float zoom = 1.0f;	
    private int state = 0;
    private long sincelast;
    private boolean zoomed = false;
    private boolean b_zoomed = false;
    private ScaleGestureDetector scaleGestureDetector;
    private static float gx = 0;
    private static float gy = 0;
    private static boolean largeMapOn = false;
    private static float maptopleftx;
    private static float maptoplefty;
    private static float mapcenterx;
    private static float mapcentery;
    private static float mapScale = 10;
    protected static float width;
    protected static float height;
    private static boolean first = true, newNode = false;
    protected float tx, ty;
    private static List<Anonymous> anonyms = new ArrayList<Anonymous>();
    private static Anonymous rootAnon = null;
    Anonymous selectedA = null;
    Anonymous selectedA2 = null;
    public static int deletedd=0;
    private static List<Relation> relations = new ArrayList<Relation>();
    Relation selectedR = null;
    private static Paint bgrndPaint = new Paint();
    private static Paint pagerBgrndPaint = new Paint();
    private static Paint generalNodePaint = new Paint();
    private static Paint generalNodeBrdPaint = new Paint();
    private static Paint generalEdgePaint = new Paint();
    private static Paint deleteRelationPaint = new Paint();
    private static Paint mapBrdPaint = new Paint();
    private static Paint textPaint = new Paint();
    private static Paint textBrdPaint = new Paint();
    private static int textSize = 44;
    private static int soundOn = 1;
    private static Paint edgeTextPaint = new Paint();
    private static boolean deleting = false;
    private static int oldColor;
    private static boolean moveAnonymus = false;
    public static boolean deleteMode = false;
    protected float deleteLineX, deleteLineY;
    public static ArrayList<YanoTheme> YanoThemes;
    public static ArrayList<YanoRelationModel> YanoRelationModels;
    public static ArrayList<String> NodeLayouts;
    private static String actualTheme;
    private static String actualRelation;
    private static String actualNodeLayout;
    private Drawable themeImage;




    public static int k=0;
   public static int select;
private int semmi;

    
    
    private static boolean mIsSelectedImage = false;
    private static List<Drawable> mImage = new ArrayList<Drawable>();
    private static int mPicCount = -1;
    /*commit test*/
    public static int cinterell(int h , int hk){
  
        select=hk;
      k=h;
      return k;
  }
  public static int cinter(int j){
  
      i=j;
      return i;
  }
 public static String[] cinters(String j){
  
      si[k-1]=j;
      return si;
  }
      
    public YourWorldView(Context context) {
        super(context);
        cinit( k ,i , si,select);
    }

    public YourWorldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cinit(k ,i , si,select);
    }

    public YourWorldView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        cinit(k ,i , si,select);
    }
    
   
    public static void setLogo(Bitmap _decodedBitmap)
    {
  	  mIsSelectedImage = true;
      mPicCount++;
      mImage.add(getCroppedBitmap(_decodedBitmap)) ;
  	  
    }
    public static void setIsSelectedImage(boolean _isSelected)
    {
  	  mIsSelectedImage = _isSelected;
    }
    
    
    public static Drawable getCroppedBitmap(Bitmap bitmap) 
    {
  	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
  	    Canvas canvas = new Canvas(output);

  	    final int color = 0xFFFFFFFF;
  	    final Paint paint = new Paint();
  	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

  	    paint.setAntiAlias(true);
  	    canvas.drawARGB(0, 0, 0, 0);
  	    paint.setColor(color);
  	    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
  	            bitmap.getWidth() / 2, paint);
  	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
  	    canvas.drawBitmap(bitmap, rect, rect, paint);
  	   
  		Drawable drawable = new BitmapDrawable( output); 

  		return drawable;
  	}
    
    
    public static boolean getisSajatkep()
    {
  	  return isSajatkep;
    }
    private static boolean isSajatkep = false;
        
    private static android.graphics.drawable.Drawable logo;

    public static void setTheme(String theme) {
        actualTheme = theme;
    }

    public static void setNodeLayout(String layout) {
        actualNodeLayout = layout;
    }

    public static String getTheme() {
        return actualTheme;
    }

    public static String getNodeLayout(){
        return actualNodeLayout;
    }

    public static void setRelationScheme(String theme) {
        actualRelation = theme;
    }

    public static String getRelationScheme() {
        return actualRelation;
    }

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

        anonyms.get(0).name = actualTheme;
    
        mImage.clear();     

        relations.clear();
    }

    public static void setDeleting(boolean b) {
        deleting = b;
    }

    public static void setMoveAnonymus(boolean m) {
        moveAnonymus = m;
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

    public static int soundOnOff(){
    	soundOn = (-1)*soundOn;
    	return soundOn;
    }

    public void Rdelete(float x, float y) {
        Anonymous a = nearestAnonymous(gx + x, gy + y);
        if ((a != anonyms.get(0)) && (a != null)) {
            RD(a);
        }
 
    }

    public void RD(Anonymous a) {
        Anonymous b = null;
        int children = 0;
        for (Relation r : relations) {
            if (r.nodeA == a) {
                children++;
            }
        }

        while (children > 0) {
            for (Relation r : relations) {
                if (r.nodeA == a) {
                    b = r.nodeB;
                    children--;
                    break;
                }
            }
            RD(b);
        }

        for (Relation r : relations) {
            if (r.nodeB == a) {
                relations.remove(r);
                break;
            }
        }
        anonyms.remove(a);
       
        if (a.name.equals("Photo") && a.getImage() != null)
        {
            mImage.remove(mImage.size()-1);
        }
        
    }

    public void delete(float x, float y) {
        Anonymous a = nearestAnonymous(gx + x, gy + y);

        if ((a != anonyms.get(0)) && (a != null)) {
	    
			for(Anonymous child : a.children){
		
				for(Relation r : relations){
				
					if(r.nodeB == child){
						relations.remove(r);
						break;
					}
					
				}
				
				a.parent.children.add(child);
				relations.add(new Relation(a.parent, child, actualRelation));
				child.parent = a.parent;
			
			}
			
            for(Relation r : relations){
            
				if(r.nodeB == a){
				
					relations.remove(r);
					break;

				}
				
            }
            
			anonyms.remove(a);
			a.parent.children.remove(a);
			updateChildren(rootAnon);
			
			
        
        }
        
    }


    @Override
    protected void onSizeChanged(int newx, int newy, int x, int y) {

        super.onSizeChanged(newx, newy, x, y);

        width = newx;
        height = newy;

        if (largeMapOn) {
            maptopleftx = 5;
            maptoplefty = height/2;
        } else {
            maptopleftx = width - width/4;
            maptoplefty = height - height/6;
        }
        mapcenterx = maptopleftx + (width - 5 - maptopleftx)/2;
        mapcentery = maptoplefty + (height - 5 - maptoplefty)/2; 

        if (first) {

            first = false;
            rootAnon = new Anonymous(null, true, actualTheme, gx + width / 2, gy + height / 2, actualTheme);
            anonyms.add(rootAnon);

            int resId = getResources().getIdentifier("ic_launcher", "drawable",
                    "net.sourceforge.yanonymous");
            logo = getResources().getDrawable(resId);

        }
    }

   public  void cinit(int k ,int i , String [] uj,int sel) {
        try {

            YanoThemes
                    = YanoXmlParser.parseThemes(getContext().getApplicationContext().getAssets(),i,k,uj,sel);

            YanoRelationModels
                    = YanoXmlParser.parseRelations(getContext().getApplicationContext().getAssets());

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        }

        actualTheme = YanoThemes.get(0).getThemeName();
        actualRelation = YanoRelationModels.get(0).getRelationName();
        pagerBgrndPaint.setColor(android.graphics.Color.argb(0xb0, 0x0f, 0x0f, 0x0f));
        pagerBgrndPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        pagerBgrndPaint.setAntiAlias(true);

        NodeLayouts = new ArrayList<String>();
        NodeLayouts.add("Manual");
        NodeLayouts.add("Automatic");
        actualNodeLayout = "Automatic";

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

        mapBrdPaint.setColor(Color.rgb(0xdd, 0xdd, 0x00));
        mapBrdPaint.setStyle(Paint.Style.STROKE);
        mapBrdPaint.setAntiAlias(true);
        mapBrdPaint.setStrokeWidth(3);

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

        scaleGestureDetector = new ScaleGestureDetector(getContext(),
                new ScaleListener());
    }

    public float d(float x1, float y1, float x2, float y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    private Anonymous nearestAnonymous(float x, float y) {
        Anonymous r = null;
        float max = /* Float.MAX_VALUE */ size * size / 2, m;

        for (Anonymous a : anonyms) {
        
			m = d(a.x, a.y, x, y);
        
            if (m < max) {
                //max = m;
                r = a;
                r.distexy = m;
            }
        }
        return r;
    }

    private Anonymous nearestAnonymous2(float x, float y) {

        float max = 0, m;

        max = size * size * 4;

        Anonymous r = null;

        for (Anonymous a : anonyms) {

            if ((m = d(a.x, a.y, x, y)) < max) {
                //max = m;
                r = a;
                r.distexy = m;
            }
        }
        return r;
    }

    private Relation nearestRelation(float x, float y) {

        Relation r = null;
        float max = /* Float.MAX_VALUE */ size * size / 2, m;

        for (Relation a : relations) {
            if ((m = d((a.nodeA.x + a.nodeB.x) / 2,
                    (a.nodeA.y + a.nodeB.y) / 2, x, y)) < max) {
                //max = m;
                r = a;
                r.distexy = m;
            }
        }
        return r;
    }

    private void updateChildren(Anonymous anon)
    {
        int numConnections = 0;
        if(anon.parent == null)
        {
          numConnections = anon.children.size();
        }
        else
        {
          numConnections = anon.children.size() + 1;
        }

        double angle = 0.0;
        if(anon.parent != null)
        {
          angle = Math.atan2(anon.parent.y - anon.y, anon.parent.x - anon.x);
          angle += 2*Math.PI/numConnections;
        }

        for(Anonymous it : anon.children)
        {
          float radius = calculateRadius(it);
          it.updateCoords(anon.x + radius*(float)Math.cos(angle), anon.y + radius*(float)Math.sin(angle));
          angle += 2*Math.PI/numConnections;

          if(!it.children.isEmpty())
          {
            updateChildren(it);
          }
        }
    }

    private float calculateRadius(Anonymous anon)
    {
        int numConnections = 0;
        if(anon.parent.parent == null) {
          numConnections = anon.parent.children.size();
        } else {
          numConnections = anon.parent.children.size() + 1;
        }

        float r = size/(float)Math.sin(Math.PI/(numConnections<2 ? 2 : numConnections));

        if(anon.children.isEmpty())
        {
            return ( r < 3*size ? 3*size : r );
        }
        else
        {
            float max = 0;
            for(Anonymous child : anon.children)
            {
                float x = calculateRadius(child);
                if(max < x) max = x;
            }

            return (max + r + size)*2;
        }
    }

    private int parentRank(Anonymous anon)
    {
        int max = 0;
        for(Anonymous it : anon.children)
        {
          int rank = parentRank(it) + 1;
          if(rank > max)
          {
            max = rank;
          }
        }

        return max;
    }

    protected boolean moved = false;
    protected float mx, my;
    protected float sgx, sgy;
    protected float startx, starty;
    protected boolean minimizeMap = false;

    MediaPlayer partySound = MediaPlayer.create(getContext(), R.raw.party_sound);
    MediaPlayer changeSound = MediaPlayer.create(getContext(), R.raw.change_sound);
    MediaPlayer deleteSound = MediaPlayer.create(getContext(), R.raw.delete_sound);

    
    
    
    
    
    public static void getMyRandom()
    
    {
  	boolean rajzoljon=true;
  	boolean elsovaltoztat=true;
  	
  	int novel=0;
  	
  for(int nodecounter=0;nodecounter<YanonymousActivity.nodes;nodecounter++)
  {
  	
  	int randomX = -0 + (int)(Math.random()*width);
  	int randomY =-0 + (int)(Math.random()*height);
  	
  	if (rajzoljon==false)
  	{

   	 randomX = 0 + (int)(Math.random()*(width+novel));
  		 randomY =0 + (int)(Math.random()*(height+novel));
  	}
  	rajzoljon=true;
  	System.out.println(novel);
  	 for (Anonymous a : anonyms) {

            if ((randomX-a.x<width/4 && randomX-a.x>-width/4) ||
            		(randomY-a.y<height/6 && randomY-a.y>height/6))
            {
            	rajzoljon=false; 
            	novel+=1;
            	break;
            	
            }
  	 }
  	if(rajzoljon==true)
  	{
  		novel-=1;
  		
  		
  		
  		
  	Anonymous ujrandomnode = new Anonymous(null,actualTheme,(float) randomX,(float) randomY,actualTheme);
  	int randomKey =0 + (int)(Math.random()*10);
  	for (int j=0;j<randomKey;j++)
  	{	
  		if(elsovaltoztat==true)
  		{
  			anonyms.get(0).next();
  		}
  		ujrandomnode.next();
  	}

  	elsovaltoztat=false;
  	anonyms.add(ujrandomnode);
  	
  	int randomNode = 0 + (int)(Math.random()*anonyms.size()-1);
  	Relation ujrelation = new Relation(anonyms.get(randomNode), ujrandomnode,actualRelation);
  	for (int h=0;h<randomKey;h++)
  	{
  		ujrelation.next();
  	}
  	relations.add(ujrelation );
  	}
  	
  	else nodecounter--;
  }
  	

    }

    
    
    
    
    
    
    
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
            {

            float x = event.getX();
            float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                if (!YanonymousActivity.getMiniMapOn() || !largeMapOn || (y <= maptoplefty)) {

                    if (deleting) {
                        Rdelete(x, y);
                    } else if (deleteMode && zoom == 1) {
                        selectedA = null;
                        selectedR = null;
                        delete(x, y);
                       deletedd++;
                    if(soundOn == 1){
                        deleteSound.seekTo(0);
                        deleteSound.start();
                    }
                    } else if (moveAnonymus && zoom == 1) {
                        if (nearestAnonymous(gx + x, gy + y) != null) {
                            selectedA = nearestAnonymous(gx + x, gy + y);
                        }

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
                       
                        if (selectedA!= null)

                            if (selectedA.getNext().equals("Photo") && selectedA.getImage() == null)
                            {
                                isSajatkep = true;   
                            }
                            else
                            {
                              isSajatkep = false;
                            }
                        /////////////////////
                    }
                }
                else
                {
                    selectedA = null;
                    selectedR = null;
                    if (((x >= maptopleftx) && (x <= maptopleftx + 20)) &&
						(y <= maptoplefty + 20)){
						minimizeMap = true;
					}
                    
                }               
                	
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                if (selectedA == null && selectedR == null) {

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
                    if (d(x, y, startx, starty) > 8.0) {
                        moved = true;
                    }

                    if(actualNodeLayout.equals("Manual")){
                        if (zoom == 1) {
                            selectedA2 = nearestAnonymous2(gx + x, gy + y);
                            if (selectedA != null && selectedA2 == null) {
                                newNode = true;
                            } else {
                                newNode = false;
                            }

                            tx = x;
                            ty = y;
                        }
                    } else if(actualNodeLayout.equals("Automatic")) {
                        if (zoom == 1) {
                            if (selectedA != null) {
                                newNode = true;
                            } else {
                                newNode = false;
                            }

                            tx = x;
                            ty = y;
                        }
                    }
                }

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (minimizeMap) {

					state = 0;
					largeMapOn = false;
					minimizeMap = false;
					maptopleftx = width - width/4;
					maptoplefty = height - height/6;
					mapcenterx = maptopleftx + (width - 5 - maptopleftx)/2;
					mapcentery = maptoplefty + (height - 5 - maptoplefty)/2;
				
				}
                if (deleting && zoom == 1) {
                    deleting = false;
                } else if ((moveAnonymus || deleteMode) && zoom == 1) {
                    selectedA = null;
                } else if (moved && zoom == 1) {

                    if (newNode && selectedA != null) {

                        if (d(selectedA.x, selectedA.y, gx + x, gy + y) > 4 * size * size){

                          if(actualNodeLayout.equals("Automatic")){

                            Anonymous a;
                            if(!selectedA.children.isEmpty()){

                            double angle = 0.0;
                            int index = 0;

                            int numConnections = (selectedA.parent == null ? selectedA.children.size() : 
                                                                             selectedA.children.size() + 1);
                            if(selectedA.parent != null){
                              angle = -1*Math.atan2(selectedA.parent.y - selectedA.y, 
                                                 selectedA.parent.x - selectedA.x);
                              index = -1;
                            }

                            angle += Math.atan2((gy + y) - selectedA.y, 
                                                (gx + x) - selectedA.x);
                            if(angle > 2*Math.PI) angle -= 2*Math.PI;
                            if(angle < 0) angle += 2*Math.PI;

                            index += (int)Math.ceil(angle/((2*Math.PI)/numConnections));


                            a = new Anonymous(selectedA, actualTheme, 0, 0, actualTheme);
                            anonyms.add(a);

                            a.parent.children.remove(a);
                            a.parent.children.add(index, a);
                          } else {
                            a = new Anonymous(selectedA, actualTheme, 0, 0, actualTheme);
                            anonyms.add(a);
                          }

                          relations.add(new Relation(selectedA, a, actualRelation));
                          if(soundOn == 1)
                          changeSound.start();

                          updateChildren(rootAnon);
                          } else if(actualNodeLayout.equals("Manual")){

                            Anonymous nearest;                        
                            if (selectedA.name.equals("Photo") && selectedA.getImage() == null){

                              Anonymous a = new Anonymous(selectedA.parent, actualTheme, gx + x, gy + y, actualTheme);
                              anonyms.add(a);
                              relations.remove(relations.size()-1);
                              relations.add(new Relation(anonyms.get(anonyms.indexOf(selectedA)-1), a, actualRelation));
                              anonyms.remove(anonyms.indexOf(selectedA));
                            } else {

                              Anonymous a = new Anonymous(selectedA, actualTheme, gx + x, gy + y, actualTheme);
                              anonyms.add(a);
                                
                              relations.add(new Relation(selectedA, a, actualRelation));
                              if(soundOn == 1)
                              partySound.start();
                            }
                          }
                        }
                      }
                } else if (!moved) {
					/*
                    if (minimizeMap) {

                        state = 0;
                        largeMapOn = false;
                        minimizeMap = false;
                        maptopleftx = width - width/4;
                        maptoplefty = height - height/6;
                        mapcenterx = maptopleftx + (width - 5 - maptopleftx)/2;
                        mapcentery = maptoplefty + (height - 5 - maptoplefty)/2;
                    
                    } else */if (selectedR != null) {
                        state = 0;
                        selectedR.next();
                    if(soundOn == 1){
                    	changeSound.seekTo(0);	
                    	changeSound.start();}

                    } else if (selectedA != null) {
                        state = 0;
                        selectedA.next();
                    if(soundOn == 1){
                    	changeSound.seekTo(0);
                    	changeSound.start();}
                    } else {
                        long ms = new Date().getTime();

                        if (state == 1) {
                            if (sincelast - ms < 500) {
                                if (YanonymousActivity.getMiniMapOn() && !largeMapOn && (x > maptopleftx) && (y > maptoplefty)) {
                                    largeMapOn = true;
                                    maptopleftx = 5;
                                    maptoplefty = height/2;
                                    mapcenterx = maptopleftx + (width - 5 - maptopleftx)/2;
                                    mapcentery = maptoplefty + (height - 5 - maptoplefty)/2;
                                } else if (YanonymousActivity.getMiniMapOn() && largeMapOn && (y > maptoplefty)) {
                                    gx += (x - mapcenterx) * mapScale;
                                    gy += (y - mapcentery) * mapScale;
                                } else {
                                    if (zoom == 1) {
                                        zoom = 0.5f;
                                    } else {
                                        zoom = 1;
                                    }
                                    setZoom(zoom);
                                }
                            }
                            state = 0;
                        } else {
                            state++;
                        }

                        sincelast = ms;
                    }
                }

                newNode = false;
                moved = false;

            }
        }

        invalidate();

        return true;
    }

    public void setZoom(float z) {
        textSize = (int) (44 * z);
        textPaint.setTextSize(textSize);
        textBrdPaint.setTextSize(textSize);
        edgeTextPaint.setTextSize(textSize / 2);
        generalNodeBrdPaint.setStrokeWidth((float) (3 * z));

        for (Anonymous a:anonyms)
            a.updateCoordsOnZooming(z, zoomed);
        
        if(zoomed)
            zoomed = false;
        else
            zoomed = true;
    }

    Rect boundsRect = new Rect();
    int size;
    Path edgePath = new Path();

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {

        canvas.drawRect(0, 0, width, height, bgrndPaint);

        generalEdgePaint.setStrokeWidth((float) (25 * zoom));
        for (Relation r : relations) {

            generalEdgePaint.setColor(r.getColor());
            canvas.drawLine((float) ((-gx + r.nodeA.x)), (float) ((-gy + r.nodeA.y)), (float) ((-gx + r.nodeB.x)),
                    (float) ((-gy + r.nodeB.y)), generalEdgePaint);

            if (r.nodeB.x < r.nodeA.x) {
                edgePath.reset();
                edgePath.moveTo((float) ((-gx + r.nodeB.x)), (float) ((-gy + r.nodeB.y)));
                edgePath.lineTo((float) ((-gx + r.nodeA.x)), (float) ((-gy + r.nodeA.y)));
                canvas.drawTextOnPath(r.name, edgePath, 6, 6, edgeTextPaint);
            } else {
                edgePath.reset();
                edgePath.moveTo((float) ((-gx + r.nodeA.x)), (float) ((-gy + r.nodeA.y)));
                edgePath.lineTo((float) ((-gx + r.nodeB.x)), (float) ((-gy + r.nodeB.y)));
                canvas.drawTextOnPath(r.name, edgePath, 6, 6, edgeTextPaint);
            }
        }

        if (selectedA != null && newNode) {
            generalEdgePaint.setColor(android.graphics.Color.argb(0xb0, 0x00, 0xaa, 0x00));

            canvas.drawLine((float) ((-gx + selectedA.x) * zoom), (float) ((-gy + selectedA.y) * zoom), tx, ty,
                    generalEdgePaint);
        }

        for (Anonymous a : anonyms) {

            a.setTheme(actualTheme);
            textPaint.getTextBounds(a.name, 0, a.name.length(), boundsRect);
            size = boundsRect.width() / 2 + 10;

            if (a.currentTheme.getThemeType().equals("TEXT")) 
            {
                generalNodePaint.setColor(a.getColor());
                canvas.drawCircle(-gx + a.x, -gy + a.y, size, generalNodePaint);
                canvas.drawCircle(-gx + a.x, -gy + a.y, size, generalNodeBrdPaint);

                if (a.me) 
                {
                    logo.setBounds((int) (-gx + a.x), (int) (-gy + a.y - 100 / 2),
                            (int) (-gx + a.x + 100 / 2), (int) (-gy + a.y));
                    logo.draw(canvas);
                }
             
               int sizeH = 0;
               if (getTheme().equals("Images"))
               {
	          	      if  (a.name.equals("Photo"))
	          	      { 
		          	    	if (a.getImage() == null)
		          	    	{
		          	    		if (mImage.size() != 0)
		          	    		{
			          	    		  if (mIsSelectedImage)
			          	    		  {
			          	    		  anonyms.get(anonyms.indexOf(a)).setImage(mImage.get(mPicCount));
			          	    		   
			          	    		  sizeH = (int) (a.getImage().getIntrinsicHeight() * size / (double) a.getImage().getIntrinsicWidth()); // casts to avoid truncating
			          	    		  a.getImage().setBounds((int) (-gx + a.x - size), (int) (-gy + a.y - sizeH),
			          										 (int) (-gx + a.x + size), (int) (-gy + a.y + sizeH)); 
			          	  	    	
			          	      		  a.getImage().draw(canvas);
			          	      		  mIsSelectedImage = false;
			          	    		  }
		          	    		 }		 
		          	    	}
		          	    	else
		          			{
		          	    	sizeH = (int) (a.getImage().getIntrinsicHeight() * size / (double) a.getImage().getIntrinsicWidth()); // casts to avoid truncating
		
		          	    	a.getImage().setBounds((int) (-gx + a.x - size), (int) (-gy + a.y - sizeH),
		          	    						   (int) (-gx + a.x + size), (int) (-gy + a.y + sizeH)); 
		          		    	
		          	  		a.getImage().draw(canvas);
		          			}
	          	      } 
                  }
          	      if (a.name.equals("Photo") && a.getImage() != null)
          	      {
          	      canvas.drawText("", -gx + a.x, -gy + a.y - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
          	      canvas.drawText("", -gx + a.x, -gy + a.y - ((textPaint.descent() + textPaint.ascent()) / 2), textBrdPaint);
          	      }
          	      else
          	      {
          	    	  canvas.drawText(a.name, -gx + a.x, -gy + a.y - ((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
          	          canvas.drawText(a.name, -gx + a.x, -gy + a.y - ((textPaint.descent() + textPaint.ascent()) / 2), textBrdPaint);
          	      }
              }
           
            
            else if (a.currentTheme.getThemeType().equals("IMAGE")) 
            {

                themeImage = a.currentTheme.getThemeImages().get(a.name);
                themeImage.setBounds((int) (-gx + a.x - 50), (int) (-gy + a.y - 50),
                        (int) (-gx + a.x + 50), (int) (-gy + a.y + 50));
                themeImage.draw(canvas);

                if (a.me) 
                {
                    logo.setBounds((int) (-gx + a.x), (int) (-gy + a.y - 100 / 2),
                            (int) (-gx + a.x + 100 / 2), (int) (-gy + a.y));
                    logo.draw(canvas);
                }
            }
        }

    	if (YanonymousActivity.getMiniMapOn()) {
	
            canvas.clipRect(maptopleftx, maptoplefty, width - 5, height - 5);
            canvas.drawRect(maptopleftx, maptoplefty, width - 5, height - 5, pagerBgrndPaint);

            generalEdgePaint.setStrokeWidth(2);
            for (Relation r : relations) {

                generalEdgePaint.setColor(r.getColor());
                canvas.drawLine(
                    (float) (mapcenterx + ((-(width/2 + gx) + r.nodeA.x) * zoom)/mapScale),
                    (float) (mapcentery + ((-(height/2 + gy) + r.nodeA.y) * zoom)/mapScale),
                    (float) (mapcenterx + ((-(width/2 + gx) + r.nodeB.x) * zoom)/mapScale),
                    (float) (mapcentery + ((-(height/2 + gy) + r.nodeB.y) * zoom)/mapScale),
                    generalEdgePaint);
            }

            for (Anonymous a : anonyms) {

                generalNodePaint.setColor(a.getColor());
                canvas.drawCircle(
                    (float) (mapcenterx + ((-(width/2 + gx) + a.x) * zoom)/mapScale),
                    (float) (mapcentery + ((-(height/2 + gy) + a.y) * zoom)/mapScale),
                    5, generalNodePaint);

            }

            canvas.drawRect(maptopleftx, maptoplefty, width - 5, height - 5, mapBrdPaint);

            if (largeMapOn) {
                
                canvas.drawLine(
                    (float) (maptopleftx + 5),
                    (float) (maptoplefty + 5),
                    (float) (maptopleftx + 20),
                    (float) (maptoplefty + 20),
                    mapBrdPaint);
                canvas.drawLine(
                    (float) (maptopleftx + 10),
                    (float) (maptoplefty + 20),
                    (float) (maptopleftx + 20),
                    (float) (maptoplefty + 20),
                    mapBrdPaint);
                canvas.drawLine(
                    (float) (maptopleftx + 20),
                    (float) (maptoplefty + 10),
                    (float) (maptopleftx + 20),
                    (float) (maptoplefty + 20),
                    mapBrdPaint);
                mapBrdPaint.setStrokeWidth(1);
                
                canvas.drawRect(
                    mapcenterx - (width / 2)/mapScale, mapcentery - (height / 2)/mapScale, 
                    mapcenterx + (width / 2)/mapScale, mapcentery + (height / 2)/mapScale, 
                    mapBrdPaint);
                mapBrdPaint.setStrokeWidth(3);
            }
	    }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            zoom *= detector.getScaleFactor();

            zoom = Math.max(0.1f, Math.min(zoom, 2.0f));
            setZoom(zoom);
            invalidate();
            return true;
        }

    }
}
