/*
 * YanonymousActivity.java
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*import com.yano.screen.Person;*/
//import com.yano.screen;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import android.widget.EditText;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.CheckBox;

public class YanonymousActivity extends android.app.Activity {
  int[] color = new int[3];
  static int[] defaultcolor = {51, 102, 255};

  String[] slots = new String[3];
  String[] loadSlots = {"Slot 1 <Empty>", "Slot 2 <Empty>", "Slot 3 <Empty>"};
  List<Relation> rList;
  List<Anonymous> aList;
  boolean isconnected;
  Socket s;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences configPref = getSharedPreferences("configpref", 0);
    slots[0] = configPref.getString("slot0", loadSlots[0]);
    slots[1] = configPref.getString("slot1", loadSlots[1]);
    slots[2] = configPref.getString("slot2", loadSlots[2]);
    setContentView(R.layout.main);
    isconnected=false;
    rList = YourWorldView.getRelations();
    aList = YourWorldView.getAnonyms();
    connectedstring="Connect to screen";
    SharedPreferences colors = getSharedPreferences("bgclour", 0);
    color[0] = colors.getInt("red", 51);
    color[1] = colors.getInt("green", 102);
    color[2] = colors.getInt("blue", 255);



    final CheckBox move = (CheckBox) findViewById(R.id.move);
    final CheckBox deleteRelation = (CheckBox) findViewById(R.id.deleteRelation);

    move.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final boolean isChecked = move.isChecked();
        YourWorldView.setMoveAnonymus(isChecked);
        if(deleteRelation.isChecked() && isChecked){ deleteRelation.performClick(); 
          }
      }
    });

    deleteRelation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final boolean isChecked = deleteRelation.isChecked();
        YourWorldView.setDeleteRelations(isChecked);
        if(move.isChecked() && isChecked){ move.performClick(); }
      }
    });

    YourWorldView.setNewColor(color);
    findViewById(android.R.id.content).invalidate();
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
        //add code here
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        //add code here
      }

      @Override
      public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
        color[0] = progress;
        YourWorldView.setNewColor(color);
        findViewById(android.R.id.content).invalidate();
        redProgress.setText("Red " + java.lang.Integer.toString(progress));
      }
    };
    redBar.setOnSeekBarChangeListener(redBarListener);

    OnSeekBarChangeListener greenBarListener = new OnSeekBarChangeListener() {
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        //add code here
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        //add code here
      }

      @Override
      public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
        color[1] = progress;
        YourWorldView.setNewColor(color);
        findViewById(android.R.id.content).invalidate();
        greenProgress.setText("Green " + java.lang.Integer.toString(progress));
      }
    };
    greenBar.setOnSeekBarChangeListener(greenBarListener);

    OnSeekBarChangeListener blueBarListener = new OnSeekBarChangeListener() {
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        //add code here
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        //add code here
      }

      @Override
      public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
        color[2] = progress;
        YourWorldView.setNewColor(color);
        findViewById(android.R.id.content).invalidate();
        blueProgress.setText("Blue " + java.lang.Integer.toString(progress));
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
        bgeditor.putInt("red", color[0]);
        bgeditor.putInt("green", color[1]);
        bgeditor.putInt("blue", color[2]);
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
        bgeditor.putInt("red", defaultcolor[0]);
        bgeditor.putInt("green", defaultcolor[1]);
        bgeditor.putInt("blue", defaultcolor[2]);
        bgeditor.commit();


        myDialog.dismiss();
      }
    });

    myDialog.show();
  }

  public void sendData(OutputStream out){
    //Relationship.Builder r;
    //Person.Builder person;
    java.io.PrintWriter ow = new java.io.PrintWriter(out);
//    try{
      /*ow.println(rList.size());
      for (Relation rel : rList){
        r = Relationship.newBuilder();
        r.setId1(rel.id1).setId2(rel.id2).setName(rel.name).build().writeTo(out);
      }
      ow.println(aList.size());
     for (Anonymous node : aList){
       person = Person.newBuilder();
       person.setId(node.id).setName(node.name).setX((int)node.x).setY((int)node.y).setIsme(node.me).build().writeTo(out);
       ow.println("\t");
      }*/
      String buff;
      ow.println(aList.size());
      aList = YourWorldView.getAnonyms();
      rList = YourWorldView.getRelations();
      for (Anonymous node : aList){
        buff = "";
        buff += Integer.toString(node.id) + " " + node.name + " " + Integer.toString(((int)node.x)) + " " + Integer.toString(((int)node.y)) + " " + Boolean.toString(node.me);
        ow.println(buff);
      }

      ow.println(rList.size());
      for (Relation rel : rList ){
        buff = "";
        buff += Integer.toString(rel.id1) + " " + Integer.toString(rel.id2) + " " + rel.name;
        ow.println(buff);
      }
      ow.flush(); 
  //  }catch(IOException e){}
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
      aList = (List<Anonymous>) anonymsObject.readObject();
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
      rList = (List<Relation>) relationsObject.readObject();
      YourWorldView.setRelations(rList);
      relationsObject.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    findViewById(android.R.id.content).invalidate();
  }

  public void connectMenu(){
    if (!isconnected){

    Toast.makeText(YanonymousActivity.this,"Warning! Using this function may generate additional fees!", Toast.LENGTH_SHORT).show();
    final Dialog myDialog = new Dialog(this);
    //myDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
    View layout = inflater.inflate(R.layout.connect, (ViewGroup) findViewById(R.id.LinLayout));
    myDialog.setContentView(layout);
    Button cancelButton = (Button) layout.findViewById(R.id.cancel);
    Button connectButton = (Button) layout.findViewById(R.id.connect);
    final EditText ipbox = (EditText) layout.findViewById(R.id.ip_box);
    //TextView warningtext = (TextView) layout.findViewById(R.id.ip_message);
    myDialog.setTitle("Connect to:");
    if (previousIp!=null){
      ipbox.setText(previousIp);
    }
    cancelButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        myDialog.dismiss();
      }
    });


    connectButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
          Toast.makeText(YanonymousActivity.this,"Connecting to " + ipbox.getText().toString(), Toast.LENGTH_SHORT).show();
          myDialog.dismiss();
          previousIp=ipbox.getText().toString();          
          try{
            s = new Socket(ipbox.getText().toString(),4444);
            isconnected=true;
            connectedstring="Disconnect";
            sendData(s.getOutputStream());
          Toast.makeText(YanonymousActivity.this,"Connected to " + ipbox.getText().toString(), Toast.LENGTH_SHORT).show();
          }
          catch(Exception e){
            Toast.makeText(YanonymousActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();

          }
      }
    });


    myDialog.show();
    }
    else{
      connectedstring="Connect to screen";
      isconnected=false;
      try{
        java.io.PrintWriter out = new java.io.PrintWriter(s.getOutputStream());
        out.println("dc");
        s.close();
      }catch(IOException ex){}
      s=null;
      Toast.makeText(YanonymousActivity.this,"Disconnected", Toast.LENGTH_SHORT).show();
    }
  }

    String previousIp;

  public void saveMenu() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Save");
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
    builder.setTitle("Load");
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
  String connectedstring;
  public void newMenu(View v) {
    final String[] items = {"Clear", "Delete", "Change Background Colour", "Save", "Load", connectedstring};

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Menu");
    builder.setItems(items, new DialogInterface.OnClickListener() {
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
            break;
          case 3:
            saveMenu();
            break;
          case 4:
            loadMenu();
            break;
          case 5:
            connectMenu();
            break;
        }
      }
    });
    AlertDialog alert = builder.create();
    alert.show();
  }

}