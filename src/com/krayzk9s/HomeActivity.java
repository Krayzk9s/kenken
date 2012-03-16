package com.krayzk9s;

import android.R.drawable;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View.DragShadowBuilder;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        
        int size = 5;
        for(int i = 0; i < size; i++)
        {
	        TableRow row = new TableRow(this);
	        row.setGravity(Gravity.CENTER);
	        table.addView(row);
	        for(int j = 0; j < size; j++)
	        {
	        	final ImageView box = new ImageView(this);
	        	box.setPadding(2, 2, 2, 2);
	        	
	        	box.setImageResource(R.drawable.box);
	        	box.setId(i*size+j);
	        	box.setClickable(true);
	        	box.setOnClickListener(new View.OnClickListener() {
	                public void onClick(View v) {
	                    Log.d("Box Clicked:", "" + v.getId());
	                }
	            });
		        row.addView(box);
	        }
        }
        
        LinearLayout tiles = (LinearLayout) findViewById(R.id.SelectTile);
        
        for(int i = 0; i<size;i++)
        {
        	final Button tile = new Button(this);
        	tile.setOnTouchListener(new View.OnTouchListener() {

        	    // Defines the one method for the interface, which is called when the View is long-clicked
        	    public boolean onTouch(View v, MotionEvent e) {
        	    Log.d("drag", "dragging");
        	    // Create a new ClipData.
        	    // This is done in two steps to provide clarity. The convenience method
        	    // ClipData.newPlainText() can create a plain text ClipData in one step.

        	    // Create a new ClipData.Item from the ImageView object's tag

        	    // Create a new ClipData using the tag as a label, the plain text MIME type, and
        	    // the already-created item. This will create a new ClipDescription object within the
        	    // ClipData, and set its MIME type entry to "text/plain"
        	    ClipData dragData = ClipData.newPlainText("", "");

        	    // Instantiates the drag shadow builder.
        	    DragShadowBuilder myShadow = new View.DragShadowBuilder(tile);

        	    // Starts the drag

        	            v.startDrag(dragData,  // the data to be dragged
        	                        myShadow,  // the drag shadow builder
        	                        tile,      // no need to use local data
        	                        0          // flags (not currently used, set to 0)
        	            );
        	            return true;

        	    }
        	});
        	tile.setText("" + (i+1));
        	tile.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        	tile.setBackgroundDrawable(getResources().getDrawable(drawable.btn_default));
        	tile.setId(size*size + i);
        	tile.setClickable(true);
        	tile.setOnClickListener(new View.OnClickListener() {
        		public void onClick(View v){
        			Log.d("HomeActivity", "" + v.getId());
        		}
        	}
        	);
        	tiles.addView(tile);
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