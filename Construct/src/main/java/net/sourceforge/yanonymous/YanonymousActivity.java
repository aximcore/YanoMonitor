/*
 * YanonymousActivity.java
 * 
 * YANonymous https://sourceforge.net/projects/yanonymous/
 * 
 * Copyright (C) 2010, Dr. Bátfai Norbert
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 * 
 * Ez a program szabad szoftver; terjeszthető illetve módosítható a Free Software Foundation által
 * kiadott GNU General Public License dokumentumában leírtak; akár a licenc 3-as, akár (tetszőleges)
 * későbbi változata szerint.
 * 
 * Ez a program abban a reményben kerül közreadásra, hogy hasznos lesz, de minden egyéb GARANCIA
 * NÉLKÜL, az ELADHATÓSÁGRA vagy VALAMELY CÉLRA VALÓ ALKALMAZHATÓSÁGRA való származtatott garanciát
 * is beleértve. További részleteket a GNU General Public License tartalmaz.
 * 
 * A felhasználónak a programmal együtt meg kell kapnia a GNU General Public License egy példányát;
 * ha mégsem kapta meg, akkor tekintse meg a <http://www.gnu.org/licenses/> oldalon.
 * 
 * 
 * 
 * Version history:
 * 
 * 0.0.1, 2013.szept.26., az Eclipse projekt átírása IDE független Maven projektbe.
 * http://progpater.blog.hu/2013/09/17/o_mondd_te_kit_valasztanal_525
 */
package net.sourceforge.yanonymous;

import android.content.res.AssetManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.yanomsg.YanoMsg.Graph;
import net.sourceforge.yanomsg.YanoMsg.Graph.Edge;
import net.sourceforge.yanomsg.YanoMsg.Graph.Edge.Node;
import net.sourceforge.yanonymous.PuzzleClient.PuzzleListener;

import android.*;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.renderscript.Element;
import static android.util.Log.i;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.sourceforge.yanonymous.YourWorldView.YanoThemes;

public class YanonymousActivity extends android.app.Activity implements PuzzleListener {

  int[] color = new int[3];
  static int[] defaultcolor = {51, 102, 255};
  
  static boolean  miniMapOn = true;

  private static int RESULT_LOAD_IMAGE = 1;


	private static final int NOTIFICATION_ID = 1;
	
	  static int nodes;
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			YourWorldView.setLogo(BitmapFactory.decodeFile(picturePath));
		}

}


 

  public static boolean getMiniMapOn() {
    return miniMapOn;
  }

  public static void setMiniMapOn(boolean value) {
    miniMapOn = value;
  }

  public void miniMapOnOff()
  {
     if (getMiniMapOn()) {
        setMiniMapOn(false);
        findViewById(android.R.id.content).invalidate();
     }
     else {
        setMiniMapOn(true);
        findViewById(android.R.id.content).invalidate();
     }
  }
 LinearLayout layout1;
  EditText numtext1;
  Button calcButton ,calc2Button;
  TextView akt,akt2;
  
  String lol;
int k=0;
public void feltoltes()
{

YanoXwrite();

}
  public void YanoXwrite() {

    layout1 = new LinearLayout(this);
    numtext1 = new EditText(this);
    calcButton = new Button(this);
    calc2Button = new Button(this);
    layout1.setOrientation(LinearLayout.VERTICAL);
    akt = new TextView(this);
    akt2 = new TextView(this);
    TextView akt3 = new TextView(this);
    akt3.setText("Warning reset job");
akt2.setText("Error");
    calcButton.setText("Read");
    calc2Button.setText("Close");
    //calcButton.setOnClickListener(multiplyClicke);
    layout1.addView(numtext1);
    layout1.addView(calcButton);
    layout1.addView(calc2Button);
    lol=YourWorldView.getTheme();
    akt.setText("Just Only TEXT !! Max item 30"+YourWorldView.getTheme() +" "+seles);
    layout1.addView(akt3);
    layout1.addView(akt);
setContentView(layout1);
 calcButton.setOnClickListener(new View.OnClickListener() {

     @Override
     public void onClick(View v) {
k++;if(k<30){
         String first = numtext1.getText().toString();
         YourWorldView.setTheme(YanoThemes.get(0).getThemeName());
         YourWorldView.cinterell(k,seles); 
         YourWorldView.cinter(1);
         YourWorldView.cinters(first);
         YourWorldView.clearAll();
         YourWorldView.setTheme(lol);
                     Toast toast =
                Toast.makeText(getApplicationContext(), "Items added ".concat(lol),
                    Toast.LENGTH_SHORT );
            toast.show();
          //
}
else layout1.addView(akt2);
       //setResult(Activity.RESULT_OK);

      setContentView(R.layout.main);
      YourWorldView.setTheme(lol);
      YourWorldView.clearAll();
     }
     });
 
  calc2Button.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
      
      // setResult(Activity.RESULT_OK);    
        YourWorldView.setTheme(YanoThemes.get(0).getThemeName());
        YourWorldView.clearAll();
            setContentView(R.layout.main);
        YourWorldView.setTheme(lol);
        YourWorldView.clearAll();
      }


    });
     
}

  ///////////////////////////////////////////////////////////////////////////////////////////
  //punkboy project
  //////////////////////////////////////////////////////////////////////////////////////////

  String[] slots = new String[3];
  String[] loadSlots = {"Slot 1 <Empty>", "Slot 2 <Empty>", "Slot 3 <Empty>"};

  private PuzzleClient puzzleclient = new PuzzleClient(this);

  CheckBox move;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences configPref = getSharedPreferences("configpref", 0);
    slots[0] = configPref.getString("slot0", loadSlots[0]);
    slots[1] = configPref.getString("slot1", loadSlots[1]);
    slots[2] = configPref.getString("slot2", loadSlots[2]);
    setContentView(R.layout.main);

    SharedPreferences colors = getSharedPreferences("bgclour", 0);
    color[0] = colors.getInt("red", 51);
    color[1] = colors.getInt("green", 102);
    color[2] = colors.getInt("blue", 255);
  
    





    Button buttonLoadImage = (Button) findViewById(R.id.callcamera);
    buttonLoadImage.setOnClickListener(new View.OnClickListener() 
    {
  		
  		@Override
  		public void onClick(View arg0) 
  		{
  			if(YourWorldView.getisSajatkep())
  			{
  			Intent i = new Intent(
  					Intent.ACTION_PICK,
  					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
  			
  			startActivityForResult(i, RESULT_LOAD_IMAGE);
  			}
  			
  		}
  	});
    
        
    
    move = (CheckBox) findViewById(R.id.move);
    if(YourWorldView.getNodeLayout().equals("Automatic")){
      move.setEnabled(false);
    }else{
      move.setEnabled(true);
    }
    move.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final boolean isChecked = move.isChecked();
        YourWorldView.setMoveAnonymus(isChecked);
      }
    });

    final CheckBox deleteM = (CheckBox) findViewById(R.id.deleteMode);
    deleteM.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {  
      
       YourWorldView.deleteMode = deleteM.isChecked();
         
         if (deleteM.isChecked()) {
                    StringBuffer result = new StringBuffer();
                    result.append("Starting counter...");

                    Toast.makeText(YanonymousActivity.this, result.toString(),
                            Toast.LENGTH_LONG).show();
                    YourWorldView.deletedd=0;
               }
               else
               {
	  
	  showNotification();
       }
      

      }
    });

    YourWorldView.setNewColor(color);
    findViewById(android.R.id.content).invalidate();
  }




  @Override
  public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
    if (keyCode == android.view.KeyEvent.KEYCODE_MENU) {
      View myView = findViewById(android.R.id.content);
      newMenu(myView);
      return true;
    }

    return super.onKeyDown(keyCode, event);
  }


public void showNotification()
	{
	   String deletedd = String.valueOf(YourWorldView.deletedd);
	   
		NotificationManager mNotificationManager =
			(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent notificationIntent =
			new Intent(this, YanonymousActivity.class);
		PendingIntent contentIntent =
			PendingIntent.getActivity(this, 0,
					notificationIntent, 0);
	
		///
		Notification notification =
			new Notification(R.drawable.ic_launcher,
					"New notification!!! Pull Down!",
					System.currentTimeMillis());
		notification.defaults |= Notification.DEFAULT_SOUND;
		       
		       notification.setLatestEventInfo(this,
                "Deleted items: ", deletedd + " pieces",
                contentIntent);
		
		mNotificationManager.notify(NOTIFICATION_ID,
				notification);
	}



  public void customizing() {
    final Dialog myDialog = new Dialog(this);
    myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
    View layout = inflater.inflate(R.layout.customize, (ViewGroup) findViewById(R.id.LinLayout));
    myDialog.setContentView(layout);
    Button cancelButton = (Button) layout.findViewById(R.id.cancel);
    Button okButton = (Button) layout.findViewById(R.id.ok);
    Button defaultButton = (Button) layout.findViewById(R.id.defaultColor);
    SeekBar redBar = (SeekBar) layout.findViewById(R.id.redBar);
    final TextView redProgress = (TextView) layout.findViewById(R.id.redProgress);
    SeekBar greenBar = (SeekBar) layout.findViewById(R.id.greenBar);
    final TextView greenProgress = (TextView) layout.findViewById(R.id.greenProgress);
    SeekBar blueBar = (SeekBar) layout.findViewById(R.id.blueBar);
    final TextView blueProgress = (TextView) layout.findViewById(R.id.blueProgress);
    color[0] = YourWorldView.getRed();
    color[1] = YourWorldView.getGreen();
    color[2] = YourWorldView.getBlue();
    redBar.setProgress(color[0]);
    greenBar.setProgress(color[1]);
    blueBar.setProgress(color[2]);
    OnSeekBarChangeListener redBarListener = new OnSeekBarChangeListener() {
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        // add code here
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        // add code here
      }

      @Override
      public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
        color[0] = progress;
        YourWorldView.setNewColor(color);
        findViewById(android.R.id.content).invalidate();
        redProgress.setText(getResources().getString(R.string.red)
            + java.lang.Integer.toString(progress));
      }
    };
    redBar.setOnSeekBarChangeListener(redBarListener);

    OnSeekBarChangeListener greenBarListener = new OnSeekBarChangeListener() {
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        // add code here
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        // add code here
      }

      @Override
      public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
        color[1] = progress;
        YourWorldView.setNewColor(color);
        findViewById(android.R.id.content).invalidate();
        greenProgress.setText(getResources().getString(R.string.green)
            + java.lang.Integer.toString(progress));
      }
    };
    greenBar.setOnSeekBarChangeListener(greenBarListener);

    OnSeekBarChangeListener blueBarListener = new OnSeekBarChangeListener() {
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        // add code here
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        // add code here
      }

      @Override
      public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
        color[2] = progress;
        YourWorldView.setNewColor(color);
        findViewById(android.R.id.content).invalidate();
        blueProgress.setText(getResources().getString(R.string.blue)
            + java.lang.Integer.toString(progress));
      }
    };
    blueBar.setOnSeekBarChangeListener(blueBarListener);

    cancelButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        YourWorldView.restoreBgColor();
        findViewById(android.R.id.content).invalidate();
        myDialog.dismiss();
      }
    });

    okButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        SharedPreferences bgPref = getSharedPreferences("bgclour", 0);
        SharedPreferences.Editor bgeditor = bgPref.edit();
        bgeditor.clear();
        bgeditor.putInt(getResources().getString(R.string.red), color[0]);
        bgeditor.putInt(getResources().getString(R.string.green), color[1]);
        bgeditor.putInt(getResources().getString(R.string.blue), color[2]);
        bgeditor.commit();

        myDialog.dismiss();

      }
    });

    defaultButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        YourWorldView.setNewColor(defaultcolor);
        findViewById(android.R.id.content).invalidate();

        SharedPreferences bgPref = getSharedPreferences("bgclour", 0);
        SharedPreferences.Editor bgeditor = bgPref.edit();
        bgeditor.clear();
        bgeditor.putInt(getResources().getString(R.string.red), defaultcolor[0]);
        bgeditor.putInt(getResources().getString(R.string.green), defaultcolor[1]);
        bgeditor.putInt(getResources().getString(R.string.blue), defaultcolor[2]);
        bgeditor.commit();

        myDialog.dismiss();
      }
    });

    myDialog.show();
  }

  public void saveData(int slot) {
    FileOutputStream anonymsFile;
    ObjectOutputStream anonymsObject;

    try {
	  anonymsFile = openFileOutput("ASlot" + slot, Context.MODE_PRIVATE);
      anonymsObject = new ObjectOutputStream(anonymsFile);
      anonymsObject.writeObject(YourWorldView.getAnonyms());
      anonymsObject.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    FileOutputStream relationsFile;
    ObjectOutputStream relationsObject;

    try {
      relationsFile = openFileOutput("RSlot" + slot, Context.MODE_PRIVATE);
      relationsObject = new ObjectOutputStream(relationsFile);
      relationsObject.writeObject(YourWorldView.getRelations());
      relationsObject.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");
    slots[slot - 1] = sdf.format(date);

    SharedPreferences configPref = getSharedPreferences("configpref", 0);
    SharedPreferences.Editor configeditor = configPref.edit();
    configeditor.clear();
    configeditor.putString("slot0", slots[0]);
    configeditor.putString("slot1", slots[1]);
    configeditor.putString("slot2", slots[2]);
    configeditor.commit();

    Toast.makeText(YanonymousActivity.this, "Saved on Slot " + slot, Toast.LENGTH_SHORT).show();
  }

  @SuppressWarnings("unchecked")
  public void loadData(int slot) {
    FileInputStream anonymsFile;
    ObjectInputStream anonymsObject;

    try {
      anonymsFile = openFileInput("ASlot" + slot);
      anonymsObject = new ObjectInputStream(anonymsFile);
      List<Anonymous> aList = (List<Anonymous>) anonymsObject.readObject();
      YourWorldView.setAnonyms(aList);
      anonymsObject.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    FileInputStream relationsFile;
    ObjectInputStream relationsObject;

    try {
      relationsFile = openFileInput("RSlot" + slot);
      relationsObject = new ObjectInputStream(relationsFile);
      List<Relation> rList = (List<Relation>) relationsObject.readObject();
      YourWorldView.setRelations(rList);
      relationsObject.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    findViewById(android.R.id.content).invalidate();
  }

  public void saveMenu() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(getResources().getString(R.string.save));
    builder.setItems(slots, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int item) {
        switch (item) {
          case 0:
            saveData(1);
            break;

          case 1:
            saveData(2);
            break;

          case 2:
            saveData(3);
            break;
        }
      }
    });
    AlertDialog alert = builder.create();
    alert.show();
  }

  public void loadMenu() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(getResources().getString(R.string.load));
    builder.setItems(slots, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int item) {
        switch (item) {
          case 0:
            loadData(1);
            break;

          case 1:
            loadData(2);
            break;

          case 2:
            loadData(3);
            break;
        }
      }
    });
    AlertDialog alert = builder.create();
    alert.show();
  }
int selesold =0;
int seles;
  AlertDialog myDialog = null;
  private String selectedItemName;
  private int selected = 0;
  private int selectedTmp = 0;

  public void themeChooser() {
    selectedItemName = YourWorldView.getTheme();
    ArrayList<String> itemsTemp = new ArrayList<String>();

    for (YanoTheme t : YourWorldView.YanoThemes) {
      itemsTemp.add(t.getThemeName());
    }

    final String[] items = itemsTemp.toArray(new String[0]);

    for (int i = 0; i < items.length; i++) {
      String item = items[i];

      if (item.equals(selectedItemName)) {
        selected = i;
      }
    }

  AlertDialog.Builder builder = new AlertDialog.Builder(YanonymousActivity.this);
  builder.setCancelable(true);
  builder.setTitle("Choose a theme");
  builder.setSingleChoiceItems(items, selected, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
      selectedItemName = items[which];
seles = which;
    }
  });

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        AlertDialog delete;
        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(YanonymousActivity.this);
        deleteBuilder.setTitle("Remove Attention!");
        deleteBuilder.setMessage("This will erase all your existing node!");

        deleteBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            YourWorldView.setTheme(selectedItemName);
            YourWorldView.clearAll();
            if(seles != selesold){k=0;selesold=seles;}
            selected = selectedTmp;
            Toast toast =
                Toast.makeText(getApplicationContext(), "Selected: ".concat(selectedItemName),
                    Toast.LENGTH_SHORT );
          }
        });

        deleteBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            myDialog.cancel();
          }
        });

        delete = deleteBuilder.create();
        delete.show();
      }
    });

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        myDialog.cancel();
      }
    });

    builder.setCancelable(false);
    myDialog = builder.create();
    myDialog.show();
  }

  public void relationChooser() {
    selectedItemName = YourWorldView.getRelationScheme();
    ArrayList<String> itemsTemp = new ArrayList<String>();

    for (YanoRelationModel r : YourWorldView.YanoRelationModels) {
      itemsTemp.add(r.getRelationName());
    }

    final String[] items = itemsTemp.toArray(new String[0]);

    for (int i = 0; i < items.length; i++) {
      String item = items[i];

      if (item.equals(selectedItemName)) {
        selected = i;
      }
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(YanonymousActivity.this);
    builder.setCancelable(true);
    builder.setTitle("Choose a relation scheme");
    builder.setSingleChoiceItems(items, selected, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        selectedItemName = items[which];
        selectedTmp = which;
      }
    });

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        AlertDialog delete;
        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(YanonymousActivity.this);
        deleteBuilder.setTitle("Remove Attention!");
        deleteBuilder.setMessage("This will erase all your existing node!");

        deleteBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            YourWorldView.setRelationScheme(selectedItemName);
            YourWorldView.clearAll();
            selected = selectedTmp;
            Toast toast =
                Toast.makeText(getApplicationContext(),
                    "Selected relation: ".concat(selectedItemName), Toast.LENGTH_SHORT);
            toast.show();
          }
        });

        deleteBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            myDialog.cancel();
          }
        });

        delete = deleteBuilder.create();
        delete.show();
      }
    });

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        myDialog.cancel();
      }
    });

    builder.setCancelable(false);
    myDialog = builder.create();
    myDialog.show();

  }


  public void nodeLayoutChooser() {

    selectedItemName = YourWorldView.getNodeLayout();

    final String[] items = YourWorldView.NodeLayouts.toArray(new String[0]);

    for (int i = 0; i < YourWorldView.NodeLayouts.size(); i++) {
      String item = YourWorldView.NodeLayouts.get(i);

      if (item.equals(selectedItemName)) {
        selected = i;
      }
    }

  AlertDialog.Builder builder = new AlertDialog.Builder(YanonymousActivity.this);
  builder.setCancelable(true);
  builder.setTitle("Choose a node layout model");
  builder.setSingleChoiceItems(items, selected, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
      selectedItemName = items[which];
    }
  });

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        if(selectedItemName.equals("Automatic")){
        AlertDialog rearrange;
        AlertDialog.Builder rearrangeBuilder = new AlertDialog.Builder(YanonymousActivity.this);
        rearrangeBuilder.setTitle("Rearrange Attention!");
        rearrangeBuilder.setMessage("This will rearrange all your existing node!");

        rearrangeBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            YourWorldView.setNodeLayout(selectedItemName);
            selected = selectedTmp;
            Toast toast =
                Toast.makeText(getApplicationContext(), "Selected: ".concat(selectedItemName),
                    Toast.LENGTH_SHORT);
            toast.show();
            move.setEnabled(false);
            if(move.isChecked()){
              move.setChecked(false);
              YourWorldView.setMoveAnonymus(false);
            }
          }
        });

        rearrangeBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            myDialog.cancel();
          }
        });

        rearrange = rearrangeBuilder.create();
        rearrange.show();
      } else if(selectedItemName.equals("Manual")) {
        YourWorldView.setNodeLayout(selectedItemName);
        selected = selectedTmp;
            Toast toast =
                Toast.makeText(getApplicationContext(), "Selected: ".concat(selectedItemName),
                    Toast.LENGTH_SHORT);
            toast.show();
        move.setEnabled(true);
      }
    }
    });

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        myDialog.cancel();
      }
    });

    builder.setCancelable(false);
    myDialog = builder.create();
    myDialog.show();
  }

  public void newMenu(View v) {
    final String[] items =
        {"Clear", 
         "Delete", 
         "Change Background Colour", 
         "Save", 
         "Load", 
         "Choose theme",
         "Choose relation", 
         "Choose node layout", 
         "Insert into Puzzle", 
         "View Puzzle", 
         "Minimap On/Off",
         "New Item to theme Reset JOB!",
         getResources().getString(R.string.sound),
         "Test with Random Nodes (<20)"};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Menu");
    builder.setItems(items, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int item) {
        switch (item) {
          case 0:
            YourWorldView.clearAll();
            findViewById(android.R.id.content).invalidate();
            break;

          case 1:
            YourWorldView.setDeleting(true);
            break;

          case 2:
            YourWorldView.setCustomizing();
            customizing();
            findViewById(android.R.id.content).invalidate();
            break;

          case 3:
            saveMenu();
            break;

          case 4:
            loadMenu();
            break;

          case 5:
            themeChooser();
            break;

          case 6:
            relationChooser();
            break;

          case 7:
            nodeLayoutChooser();
            break;

          case 8:
            insertIntoPuzzle();
            break;

          case 9:
            viewPuzzle();
            break;

          case 10:
            miniMapOnOff();
            break;

                    case 11:
            feltoltes();
              break;
          case 12:
        	int soundStatus = YourWorldView.soundOnOff();
        	Context context = getApplicationContext();
        	CharSequence text_on = getResources().getString(R.string.soundOn);
        	CharSequence text_off = getResources().getString(R.string.soundOff);
        	int duration = Toast.LENGTH_SHORT;
        	if(soundStatus == 1)
        		Toast.makeText(context, text_on, duration).show();
        	else
        		Toast.makeText(context, text_off, duration).show();
        	break;
        	
        	/*ide jon a random menu*/
          case 13:
	    		randomUI();
	    		findViewById(android.R.id.content).invalidate();
	    		break;
        }
      }
    });
    AlertDialog alert = builder.create();
    alert.show();
  }
  
  
  public void randomUI(){
	  	final Dialog myDialog = new Dialog(this);
	  	myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	  	myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	  	LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
	  	View layout = inflater.inflate(R.layout.random, (ViewGroup)findViewById(R.id.LinLayout));
	  	myDialog.setContentView(layout);
	  	Button cancelButton = (Button)layout.findViewById(R.id.cancel);
	  	Button okButton = (Button)layout.findViewById(R.id.ok);
	  	
	  	SeekBar randomBar = (SeekBar)layout.findViewById(R.id.randomBar);
	  	final TextView randomProgress = (TextView)layout.findViewById(R.id.randomProgress);
	  
	  	OnSeekBarChangeListener randomBarListener = new OnSeekBarChangeListener() {
	  	    @Override
	  	    public void onStopTrackingTouch(SeekBar seekBar) {
	  	           
	  	    }

	  	    @Override
	  	    public void onStartTrackingTouch(SeekBar seekBar) {
	  	            
	  	    }

	  	    @Override
	  	    public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
	  	    	nodes=progress;
	  	    	
	  	    	
	          	findViewById(android.R.id.content).invalidate();
	          	randomProgress.setText("Nodes " + java.lang.Integer.toString(progress));
	          	
	  	    }
	  	 };
	  	 randomBar.setOnSeekBarChangeListener(randomBarListener);
	  	 cancelButton.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View v) {
	           	findViewById(android.R.id.content).invalidate();
	           	myDialog.dismiss();
	           }
	       });
	       
	       okButton.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View v) {
	          	 YourWorldView.clearAll();
	          	 YourWorldView.getMyRandom();
	          	 findViewById(android.R.id.content).invalidate();
	       	   myDialog.dismiss();
	       	   

	           }
	       });
	       
	    
	  	 
	   	myDialog.show();
	  }
  
  
  
  private void insertIntoPuzzle() {
    if (!isOnline()) {
      Toast.makeText(this, getString(R.string.offline), Toast.LENGTH_SHORT).show();
      return;
    }

    if (YourWorldView.getRelations().size() == 0) {
      Toast.makeText(this, getString(R.string.empty_network), Toast.LENGTH_SHORT).show();
      return;
    }

    final Graph.Builder graph = Graph.newBuilder();

    Edge.Builder edge = Edge.newBuilder();
    for (Relation r : YourWorldView.getRelations()) {
      edge.setFrom(Node.newBuilder().setLabel(r.nodeA.name));
      edge.setTo(Node.newBuilder().setLabel(r.nodeB.name));
      edge.setLabel(r.name);

      graph.addEdge(edge);
    }

    new Thread() {
      public void run() {
        puzzleclient.execute(PuzzleActivity.BASE_URL, graph.build());
      }
    }.start();
  }

  private boolean isOnline() {
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
  }

  private void viewPuzzle() {
    if (isOnline()) {
      Intent intent = new Intent(this, PuzzleActivity.class);
      startActivity(intent);
    } else {
      Toast.makeText(this, getString(R.string.offline), Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onResponse(String message) {
    Log.i("YANO", "Server response: " + message);
    if (message.startsWith("OK")) {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          Toast.makeText(YanonymousActivity.this, getString(R.string.sent), Toast.LENGTH_SHORT)
              .show();
        }
      });
    }
  }

  @Override
  public void onError(final String message) {
    Log.i("YANO", "Server error: " + message);
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (message.endsWith("404")) {
          Toast.makeText(YanonymousActivity.this, getString(R.string.service_unavailable),
              Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(YanonymousActivity.this, getString(R.string.error), Toast.LENGTH_SHORT)
              .show();
        }
      }
    });
  }
}
