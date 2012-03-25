package com.krayzk9s.classes;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;

public class SquareTouchListener implements OnTouchListener {

	public boolean onTouch(View v, MotionEvent touchEvent) {
		Square square = (Square)v;
		if(square.getNumber() != 0) {
			ClipData dragData = ClipData.newPlainText("tilenumber", ""+square.getNumber());
		    DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
		    v.startDrag(dragData, myShadow, v, 0);
		    square.setNumber(0);
		    return true;
		}
		else {
			return false;
		}
	}

}
