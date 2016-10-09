package com.example.m1320.express;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;



public class Bullet {
	GameView gameview;
	public float x ;
	public float y ;
	private Bitmap bullet;
	private int time;
	public Bullet(float x,float y,Bitmap bullet,int time,GameView gameview){
		this.x = x;
		this.y = y;
		this.bullet = bullet;
		this.time = time;
		this.gameview=gameview;
		
	}
	
	public void draw1(Canvas canvas){
		Matrix matrix = new Matrix();
		y = y-50;
		matrix.setTranslate(x, y);
		canvas.drawBitmap(bullet, matrix, null);
	}
	public void draw2(Canvas canvas){
		Matrix matrix = new Matrix();
		if(gameview.tm2-gameview.tm1>5000){
			y=y+8;
		}else if(gameview.tm2-gameview.tm1>10000){
			y=y+10;
		}else if(gameview.tm2-gameview.tm1>15000){
			y=y+15;
		}else if(gameview.tm2-gameview.tm1>20000){
			y=y+20;
		}else if(gameview.tm2-gameview.tm1>25000){
			y=y+30;
		}else if(gameview.tm2-gameview.tm1>30000){
			y=y+40;
		}else if(gameview.tm2-gameview.tm1>35000){
			y=y+43;
		}else if(gameview.tm2-gameview.tm1>45000){
			y=y+46;
		}else if(gameview.tm2-gameview.tm1>60000){
			y=y+49;
		}else{
		y=y+4;}
		matrix.setTranslate(x, y);
		canvas.drawBitmap(bullet, matrix, null);
	
	}
}
