package com.krayzk9s;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends ListActivity {
	
	static final String[] MENU = new String[] {"Play Game", "Options", "About", "Instructions", "Quit" };
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
	        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.menu_item, MENU));
		}
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id)
		{
		    super.onListItemClick(l, v, position, id);
		    String itemText = getListView().getItemAtPosition(position).toString();
		    if(itemText.equals("Play Game")) {
		    	Intent intent = new Intent(this, Game.class);
		    	intent.putExtra("Level", 1);
		    	startActivity(intent);		    	
		    }
		    Log.d("Clicked:", itemText);
		}
}
