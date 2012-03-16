package com.krayzk9s;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.View;
import android.util.Log;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TableLayout table = (TableLayout) findViewById(R.id.PlayTable);
        
        int rows = 5;
        int cols = 5;
        for(int i = 0; i < rows; i++)
        {
	        TableRow row = new TableRow(this);
	        table.addView(row);
	        for(int j = 0; j < cols; j++)
	        {
	        	ImageView box = new ImageView(this);
	        	box.setImageResource(R.drawable.box);
	        	box.setId(i*cols+j);
	        	box.setClickable(true);
	        	box.setOnClickListener(new View.OnClickListener() {
	                public void onClick(View v) {
	                    Log.d("Box Clicked:", "" + v.getId());
	                }
	            });
		        row.addView(box);
	        }
        }
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