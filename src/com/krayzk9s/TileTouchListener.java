package com.krayzk9s;

import android.content.ClipData;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;


public class TileTouchListener implements View.OnTouchListener {
	
	public boolean onTouch(View v, MotionEvent event) {
		Log.d("touch", "touching");
		Tile t = (Tile)v;
	    ClipData dragData = ClipData.newPlainText("tilenumber", ""+t.number);
	    DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
	    v.startDrag(dragData, myShadow, v, 0);
	    return true;
	}
}
