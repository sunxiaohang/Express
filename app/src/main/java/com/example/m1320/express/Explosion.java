package com.example.m1320.express;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Explosion {

	float x;
	float y;
//	int n;
	int m = 0;
	private ArrayList<Bitmap> frame = new ArrayList<Bitmap>();
	public Explosion(float x,float y,Bitmap[] explosion){
		this.x = x;
		this.y = y;
		for(int i =0;i<12;i++){
			frame.add (explosion[i]);
		}
		
	}
	public void draw(Canvas canvas){
		canvas.drawBitmap(frame.get(m), x, y, null);
	}
}
