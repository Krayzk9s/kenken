package com.krayzk9s;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.krayzk9s.classes.Square;
import com.krayzk9s.classes.Tile;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Game extends Activity {
	
	private int level;
	private String sequence;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        level = this.getIntent().getExtras().getInt("Level");
        Log.d("Level", level+"");
        //grab playtable from layout
        TableLayout table = (TableLayout) findViewById(R.id.PlayTable);
        
        //load level
        loadLevel();
        
        //create play squares
        int size = (int) Math.sqrt(sequence.length());
        for(int i = 0; i < size; i++)
        {
	        TableRow row = new TableRow(this);
	        row.setGravity(Gravity.CENTER);
	        table.addView(row);
	        for(int j = 0; j < size; j++)
	        {
	        	Square square = new Square(this, size*i+j);
	        	square.truenumber = Character.getNumericValue(sequence.charAt(i*size+j));
		        row.addView(square);
	        }
        }
        
        //create tiles
        LinearLayout tiles = (LinearLayout) findViewById(R.id.SelectTile);
        for(int i = 1; i<size+1;i++)
        {
        	Tile tile = new Tile(this, i);        	
        	tiles.addView(tile);
        }
        
        //set up action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    private void loadLevel() {
    	XmlResourceParser levelxml = this.getResources().getXml(R.xml.size3);
        Map<String,String> boxes = new HashMap<String,String>();
        try {
			while(levelxml.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if(levelxml.getEventType() == XmlResourceParser.START_TAG) {
					Log.d("yes",levelxml.getName());
					if(levelxml.getName().equals("level") && levelxml.getIdAttribute().equals(level + "")) {
						Log.d("here","here");
						while(!levelxml.getName().equals("level") || levelxml.getEventType() != XmlResourceParser.END_TAG) {
							String s = levelxml.getName();
							Log.d("no",s);
							if(levelxml.getEventType() != XmlResourceParser.END_TAG) {
								if(s.equals("solution")) {
									levelxml.next();
									sequence = levelxml.getText();
									Log.d("sequence", sequence);
								}
								else if(s.equals("boxes")) {						
									while(!levelxml.getName().equals("boxes") || levelxml.getEventType() != XmlResourceParser.END_TAG ) {
										String key = "";
										String value = "";
										Log.d("boxes", levelxml.getName());
										if(levelxml.getEventType() != XmlResourceParser.END_TAG) {
											if(levelxml.getName().equals("text")){
												levelxml.next();
												key = levelxml.getText();
											}
											else if(levelxml.getName().equals("squares")) {
												levelxml.next();
												value = levelxml.getText();
											}
										}
										boxes.put(key, value);
										Log.d("",""+key+value);
										levelxml.next();
									}									
								}
							}
							levelxml.next();			
						}
					}
				}
				levelxml.next();
			}
		} catch (XmlPullParserException e) {
			Log.d("sequence","failPull");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("sequence","failIO");
			e.printStackTrace();
		}
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.mainmenu, menu);
        return true;
    }
}