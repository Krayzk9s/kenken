package com.krayzk9s.classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.krayzk9s.R;

public class Tile extends ImageView {
	public int number;
	
	public Tile(Context context, int _number) {
		super(context);
		setOnTouchListener(new TileTouchListener());
		setPadding(1, 2, 1, 2);
    	setImageResource(R.drawable.box);
    	number = _number;
    	setClickable(true);
    	setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v){
    			Log.d("HomeActivity", "" + v.getId());
    		}
    	}
    	);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG);
		paint.setARGB(255, 0, 0, 0);
        paint.setTextSize(60);
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setAntiAlias(true);
        canvas.drawText("" + number, 20, 60, paint);
	}
}
