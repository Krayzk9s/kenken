package com.krayzk9s;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.krayzk9s.classes.Square;
import com.krayzk9s.classes.Tile;

public class Game extends Activity {
	
	private int level;
	private String sequence;
	private String[] tags;
	private Map<String,String> boxes;
	private Square[] squares;
	
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
        squares = new Square[size*size];
        for(int i = 0; i < size; i++)
        {
	        TableRow row = new TableRow(this);
	        row.setGravity(Gravity.CENTER);
	        table.addView(row);
	        for(int j = 0; j < size; j++)
	        {
	        	Square square = new Square(this, size*i+j);
	        	if(tags[size*i+j] != null) {
	        		square.tag = tags[size*i+j];
	        	}	        	
	        	else {
	        		square.tag = "";
	        	}
	        	square.truenumber = Character.getNumericValue(sequence.charAt(i*size+j));
		        row.addView(square);
		        squares[size*i+j] = square;
	        }
        }
        int[] colors = { Color.argb(50, 0, 0, 255), Color.argb(50, 0, 255, 0),Color.argb(50, 255, 0, 0), Color.argb(50, 255, 255, 0), Color.argb(50, 255, 0, 255), Color.argb(50, 0, 255, 255)};
        int colorindex = 0;
        Iterator<Entry<String, String>> it = boxes.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, String> pairs = (HashMap.Entry<String, String>) it.next();
			String[] inputsquares = pairs.getKey().split(",");
			for(int i=0; i<inputsquares.length;i++) {
				Log.d("coloring","" + Integer.parseInt(inputsquares[i]));
				ImageView square = squares[Integer.parseInt(inputsquares[i])];
				square.setColorFilter(colors[colorindex]);
				square.invalidate();
			}
		colorindex++;
		if (colorindex >= colors.length) colorindex = 0;
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
    	XmlResourceParser levelxml = this.getResources().getXml(R.xml.size4);
        boxes = new HashMap<String,String>();
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
									String key = "";
									String value = "";
									while(!levelxml.getName().equals("boxes") || levelxml.getEventType() != XmlResourceParser.END_TAG ) {
										
										Log.d("boxes", levelxml.getName());
										if(levelxml.getEventType() != XmlResourceParser.END_TAG) {
											if(levelxml.getName().equals("text")){
												levelxml.next();
												value = levelxml.getText();
											}
											else if(levelxml.getName().equals("squares")) {
												levelxml.next();
												key = levelxml.getText();
											}
										}
										if(!key.equals("") && !value.equals("")) {
											boxes.put(key, value);
											Log.d(key,value);
											key = "";
											value = "";
										}
										
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
        tags = new String[sequence.length()];
		Iterator<Entry<String, String>> it = boxes.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<String, String> pairs = (HashMap.Entry<String, String>) it.next();
			String[] squares = pairs.getKey().split(",");
			tags[Integer.parseInt(squares[0])] = pairs.getValue();
			Log.d(Integer.parseInt(squares[0]) + "", pairs.getValue());
		}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.mainmenu, menu);
        return true;
    }
	public void checkVictory() {
		boolean correct = true;
		for(int i = 0; i < squares.length; i++) {
			if(squares[i].truenumber != squares[i].getNumber()) {
				correct = false;
			}
		}
		if(correct) {
			Toast toast = Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT);
			toast.show();
			this.finish();
		}
	}
}