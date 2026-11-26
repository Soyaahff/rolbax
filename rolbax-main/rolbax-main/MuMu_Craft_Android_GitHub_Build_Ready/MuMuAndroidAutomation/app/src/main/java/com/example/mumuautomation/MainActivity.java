package com.example.mumuautomation;

import android.app.Activity; import android.os.Bundle; import android.content.*; import android.provider.Settings; import android.net.Uri; import android.view.*; import android.widget.*;

public class MainActivity extends Activity {
 String[] labels={"Open bag / Inventory","Craft menu","Halter recipe","Select tier","Prismatic variant","Craft button","Close inventory / exit UI","Talk to Larry (Buy)","Larry Buy","Leather item","Prismatic Gem item","Quantity MAX","Confirm Buy","Close shop","Talk to Larry (Sell)","Larry Sell","Select Halters / all","Done","Sounds good","Next","No, sorry / exit dialogue"};
 EditText[] fields=new EditText[labels.length]; EditText loops, delay;
 int dp(float v){return (int)(v*getResources().getDisplayMetrics().density+0.5f);} 
 @Override public void onCreate(Bundle b){super.onCreate(b); build();}
 void build(){
  ScrollView sc=new ScrollView(this); LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(16),dp(14),dp(16),dp(20)); root.setBackgroundColor(0xff202632); sc.addView(root); setContentView(sc);
  TextView title=new TextView(this); title.setText("MuMu Craft Automation"); title.setTextSize(23); title.setTextColor(-1); title.setTypeface(null,1); root.addView(title);
  TextView note=new TextView(this); note.setText("Floating Start / Stop controls • Coordinate-based taps"); note.setTextColor(0xffb9c5d4); root.addView(note);
  Button perm=new Button(this); perm.setText("1. Allow floating window"); perm.setOnClickListener(v->{if(!Settings.canDrawOverlays(this)) startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()))); else Toast.makeText(this,"Overlay permission already enabled",0).show();}); root.addView(perm);
  Button acc=new Button(this); acc.setText("2. Enable Accessibility service"); acc.setOnClickListener(v->startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))); root.addView(acc);
  loops=new EditText(this); loops.setSingleLine(); loops.setInputType(2); loops.setText("0"); loops.setHint("Loops (0 = unlimited)"); addField(root,"Loops (0 = unlimited)",loops);
  delay=new EditText(this); delay.setSingleLine(); delay.setInputType(8194); delay.setText("900"); addField(root,"Delay after each tap (milliseconds)",delay);
  TextView info=new TextView(this); info.setText("Enter coordinates as X,Y for each screen target. Use MuMu at the same resolution. Coordinates are physical screen pixels. Configure only the actions you want; empty coordinates skip that step."); info.setTextColor(0xffd8e0ea); info.setPadding(0,dp(10),0,dp(10)); root.addView(info);
  for(int i=0;i<labels.length;i++){ fields[i]=new EditText(this); fields[i].setSingleLine(); fields[i].setHint("X,Y (e.g. 420,760)"); fields[i].setTextSize(14); fields[i].setTextColor(-1); fields[i].setHintTextColor(0xffaab5c4); addField(root,labels[i],fields[i]); }
  Button save=new Button(this); save.setText("Save settings"); save.setOnClickListener(v->save()); root.addView(save);
  Button launch=new Button(this); launch.setText("Show floating control panel"); launch.setOnClickListener(v->{save(); if(!Settings.canDrawOverlays(this)){Toast.makeText(this,"Allow overlay permission first",1).show();return;} sendBroadcast(new Intent("com.example.mumuautomation.CONTROL").setAction("SHOW")); Toast.makeText(this,"Floating panel requested",0).show();}); root.addView(launch);
  Button overlay=new Button(this); overlay.setText("Hide floating panel"); overlay.setOnClickListener(v->sendBroadcast(new Intent("com.example.mumuautomation.CONTROL").setAction("HIDE"))); root.addView(overlay);
  load();
 }
 void addField(LinearLayout root,String label,EditText e){TextView t=new TextView(this); t.setText(label); t.setTextColor(0xffdce4ef); t.setPadding(0,dp(9),0,dp(2)); root.addView(t); e.setTextColor(-1); e.setHintTextColor(0xffaab5c4); e.setBackgroundColor(0xff364253); e.setPadding(dp(8),dp(7),dp(8),dp(7)); root.addView(e);}
 void save(){ SharedPreferences p=getSharedPreferences("cfg",0); SharedPreferences.Editor ed=p.edit(); ed.putString("loops",loops.getText().toString()).putString("delay",delay.getText().toString()); for(int i=0;i<fields.length;i++) ed.putString("p"+i,fields[i].getText().toString()); ed.apply(); Toast.makeText(this,"Settings saved",0).show(); }
 void load(){SharedPreferences p=getSharedPreferences("cfg",0); loops.setText(p.getString("loops","0")); delay.setText(p.getString("delay","900")); for(int i=0;i<fields.length;i++)fields[i].setText(p.getString("p"+i,""));}
}
