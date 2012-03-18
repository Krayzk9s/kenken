package com.krayzk9s;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.ImageView;

public class Square extends ImageView {
	private int number;
	private ArrayList<Boolean> allowedNumbers;
	public int position;
	
	public Square(Context context, int _position) {
		super(context);
		setOnDragListener(new TileDragListener());
    	setPadding(1, 1, 1, 1);
    	setImageResource(R.drawable.box);
    	position = _position;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG);
		paint.setARGB(255, 255, 0, 0);
        paint.setTextSize(20);
        paint.setTypeface(Typeface.MONOSPACE);
        Log.d("painting", ""+ position);
		canvas.drawText("" + position, 2, 18, paint);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Boolean getAllowedNumbers(int i) {
		return allowedNumbers.get(i);
	}
	public void setAllowedNumbers(int i, Boolean bool) {
		allowedNumbers.set(i, bool);
	}
}
