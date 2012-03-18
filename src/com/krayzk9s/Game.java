package com.krayzk9s;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

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
	
	private String sequence;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
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
    	XmlResourceParser level = this.getResources().getXml(R.xml.size3);
        Map<String,String> boxes = new HashMap<String,String>();
        try {
			while(level.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if(level.getEventType() == XmlResourceParser.START_TAG) {
					String s = level.getName();
					if(s.equals("solution")){
						level.next();
						sequence = level.getText();
					}
					if(s.equals("boxes")) {
						level.next();
						Log.d("",""+level.getName());
						while(level.getName().equals("box")) {
							level.next();
							level.next();
							String key = level.getText();
							level.next();
							level.next();
							level.next();
							String value = level.getText();
							level.next();
							level.next();
							level.next();
							boxes.put(key, value);
							Log.d("",""+key+value);
						}
					}
				}
				level.next();
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