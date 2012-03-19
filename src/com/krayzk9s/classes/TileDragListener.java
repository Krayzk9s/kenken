package com.krayzk9s.classes;

import android.content.ClipData;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;


public class TileDragListener implements View.OnDragListener {

	private Boolean containsDragable;
	
	public boolean onDrag(View v, DragEvent event) {
		Square square = (Square) v;
		int dragAction = event.getAction();
		View dragView = (View) event.getLocalState();
		if(dragAction == DragEvent.ACTION_DRAG_EXITED) {
			containsDragable = false;
		}
		else if(dragAction == DragEvent.ACTION_DRAG_ENTERED){
			containsDragable = true;
		}
		else if(dragAction == DragEvent.ACTION_DROP && containsDragable) {
			dragView.setVisibility(View.VISIBLE);
			ClipData data = event.getClipData();
			ClipData.Item dataitem = data.getItemAt(0);
			String strdata = (String) dataitem.coerceToText(null);
			square.setNumber(Integer.parseInt(strdata));
			Log.d("drag", strdata + "dragging" + square.position);
		}
		// TODO Auto-generated method stub
		return true;
	}

}
