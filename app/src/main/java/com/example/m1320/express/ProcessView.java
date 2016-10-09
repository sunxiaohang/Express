package com.example.m1320.express;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class ProcessView extends SurfaceView 
implements SurfaceHolder.Callback{
	Bitmap processbackground;
	private TutorialThread thread;
	Bitmap process;
	int proces=0;
	int startx,starty;
	private Paint paint;
	private boolean flag=true;
	private PanlewarActivity activity;
	int scren_width=400;
	int scren_heigt=800;
	private int type;
public ProcessView(PanlewarActivity activity, int type,int screenwidth,int screenheight) {
	   super(activity);
	   this.activity = activity;
       getHolder().addCallback(this);
       this.thread = new TutorialThread(getHolder(), this);
       this.type = type;
       paint = new Paint();
       this.scren_width=screenwidth;
       this.scren_heigt=screenheight;
		paint.setTextSize(12);
		process = BitmapFactory.decodeResource(getResources(), R.drawable.process);
		processbackground = BitmapFactory.decodeResource(getResources(), R.drawable.tencentlogo);
		this.startx=scren_width/2-process.getWidth()/2;
		this.starty=scren_heigt-process.getHeight()*2;
	}
public void onDraw(Canvas canvas){
	Rect rect1 = new Rect(0,0,processbackground.getWidth(),processbackground.getHeight());
	Rect rect2 = new Rect(0,0,scren_width,scren_heigt);
	canvas.drawBitmap(processbackground,rect1,rect2, null);
	canvas.drawBitmap(process,startx,starty, paint);
	canvas.drawRect(startx+proces*(process.getWidth()/100),starty,startx+process.getWidth(),
			starty+process.getHeight(),paint);
}
private WindowManager getWindowManager() {
	// TODO Auto-generated method stub
	return null;
}
@Override
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
         catch (InterruptedException e) {}
     }
}
class TutorialThread extends Thread{
	private int span = 400;
	private SurfaceHolder surfaceHolder;
	private ProcessView processView;
	private boolean flag = false;
    public TutorialThread(SurfaceHolder surfaceHolder, ProcessView processView) {
        this.surfaceHolder = surfaceHolder;
        this.processView = processView;
    }
    public void setFlag(boolean flag) {
    	this.flag = flag;
    }
	public void run() {
		Canvas c;
        while (this.flag) {
            c = null;
            try {
                c = this.surfaceHolder.lockCanvas(null);
                synchronized (this.surfaceHolder) {
                	processView.draw(c);
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
            proces+=20;
            if(proces==100){
            	if(processView.type == 1){
            		processView.activity.myhander.sendEmptyMessage(4);
            	}
            	
            }
        }
	}
}


		
}
