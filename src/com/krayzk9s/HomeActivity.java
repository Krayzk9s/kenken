package com.krayzk9s;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class HomeActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //grab playtable from layout
        TableLayout table = (TableLayout) findViewById(R.id.PlayTable);
        
        //create play squares
        int size = 5;
        for(int i = 0; i < size; i++)
        {
	        TableRow row = new TableRow(this);
	        row.setGravity(Gravity.CENTER);
	        table.addView(row);
	        for(int j = 0; j < size; j++)
	        {
	        	ImageView square = new Square(this, size*i+j);
		        row.addView(square);
	        }
        }
        
        //create tiles
        LinearLayout tiles = (LinearLayout) findViewById(R.id.SelectTile);
        for(int i = 1; i<size+1;i++)
        {
        	ImageView tile = new Tile(this, i);        	
        	tiles.addView(tile);
        }
        
        //set up action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.mainmenu, menu);
        return true;
    }
}