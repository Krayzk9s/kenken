package com.krayzk9s.classes;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.ImageView;

import com.krayzk9s.R;

public class Square extends ImageView {
	private int number;
	private ArrayList<Boolean> allowedNumbers;
	public int position;
	public int truenumber;
	public String tag;
	
	public Square(Context context, int _position) {
		super(context);
		setOnDragListener(new TileDragListener());
    	setPadding(1, 1, 1, 1);
    	setImageResource(R.drawable.box);
    	this.invalidate();
    	position = _position;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG);
		paint.setARGB(255, 255, 0, 0);
        paint.setTextSize(20);
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setAntiAlias(true);
        Log.d("tag",""+tag);
		canvas.drawText(tag, 2, 18, paint);
		if(number != 0) {
			paint.setARGB(255, 0, 0, 0);
	        paint.setTextSize(60);
	        paint.setTypeface(Typeface.MONOSPACE);
	        paint.setAntiAlias(true);
	        canvas.drawText("" + number, 20, 60, paint);
		}
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
		this.invalidate();
	}
	public Boolean getAllowedNumbers(int i) {
		return allowedNumbers.get(i);
	}
	public void setAllowedNumbers(int i, Boolean bool) {
		allowedNumbers.set(i, bool);
	}
}
