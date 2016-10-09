package com.example.m1320.express;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class reStartView extends SurfaceView implements SurfaceHolder.Callback{
	PanlewarActivity activity;
	int width,height;
	Paint paint=new Paint();
	Bitmap restart,backgroundre,turnH;
	private TutorialThread thread;
	public reStartView(PanlewarActivity activity,int width,int height) {
		super(activity);
		this.activity=activity;
		this.width=width;
		this.height=height;
		 getHolder().addCallback(this);
	        this.thread = new TutorialThread(getHolder(), this);
	        initBitmap();
		// TODO Auto-generated constructor stub
	}
	public void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		Rect rect1 = new Rect(0,0,backgroundre.getWidth(),backgroundre.getHeight());
		Rect rect2 = new Rect(0,0,width,height);
		canvas.drawBitmap(backgroundre,rect1,rect2, null);
		canvas.drawText("���а�", 0, 0, paint);
		canvas.drawBitmap(restart,width/2-restart.getWidth()/2, height/3+(height*2/3-2*restart.getHeight())/3, null);
		canvas.drawBitmap(turnH, width/2-restart.getWidth()/2, height-(height*2/3-2*restart.getHeight())/3-turnH.getHeight(), null);  
	}
	private void initBitmap() {
		// TODO Auto-generated method stub
		 restart=BitmapFactory.decodeResource(getResources(), R.drawable.restartgame);
		 backgroundre=BitmapFactory.decodeResource(getResources(), R.drawable.restart);
		 turnH=BitmapFactory.decodeResource(getResources(), R.drawable.returnh);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			/*if(this.status != 4){
				return false;
			}*/
			double x = event.getX();
			double y = event.getY();
			if(x>width/2-restart.getWidth()/2&& x<width/2-restart.getWidth()/2 + restart.getWidth()
					&& y>height/3+(height*2/3-2*restart.getHeight())/3 && y<height/3+(height*2/3-2*restart.getHeight())/3 + restart.getHeight()){
				activity.myhander.sendEmptyMessage(6);
			}
			else if(x>width/2-restart.getWidth()/2 && x<width/2-restart.getWidth()/2 + turnH.getWidth()
					&& y>height-(height*2/3-2*restart.getHeight())/3-turnH.getHeight() && y<height-(height*2/3-2*restart.getHeight())/3-turnH.getHeight() + turnH.getHeight()){
				activity.myhander.sendEmptyMessage(8);
			}
			
		}
		return super.onTouchEvent(event);
	}
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		 this.thread.setFlag(true);
	        this.thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		 boolean retry = true;
	        thread.setFlag(false);
	        while (retry) {
	            try {
	                thread.join();
	                retry = false;
	            } 
	            catch (InterruptedException e) {
	            }
	        }
	}
	class TutorialThread extends Thread{
		private int span = 100;
		private SurfaceHolder surfaceHolder;
		private reStartView restart;
		private boolean flag = false;
        public TutorialThread(SurfaceHolder surfaceHolder, reStartView restart) {
            this.surfaceHolder = surfaceHolder;
            this.restart = restart;
        }
        public void setFlag(boolean flag) {
        	this.flag = flag;
        }
		@Override
		public void run() {
			Canvas c;
            while (this.flag) {
                c = null;
                try {
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {
                    	restart.draw(c);
                    }
                } finally {
                    if (c != null) {
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);
                }
                catch(Exception e){
                	e.printStackTrace();
                }
            }
		}
	}
}
