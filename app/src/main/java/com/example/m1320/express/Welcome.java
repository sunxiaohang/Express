package com.example.m1320.express;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;

public class Welcome extends SurfaceView implements SurfaceHolder.Callback {
	private Context context;
	private PanlewarActivity activity;
	private TutorialThread thread;
	SoundPool soundPool;
	int status = 1;
	Bitmap background2;
	Bitmap startGame;
	Bitmap result;
	Bitmap openSound;
	Bitmap closeSound;
	Bitmap exit;
	public static Bitmap special = null, especiall = null, godPlane = null;
	HashMap<Integer, Integer> soundPoolMap;
	int width, height;
	Paint paint2;

	public Welcome(PanlewarActivity activity, int width, int height) {
		super(activity);
		this.activity = activity;
		this.width = width;
		this.height = height;
		getHolder().addCallback(this);
		this.thread = new TutorialThread(getHolder(), this);
		// this.welcomeThread = new WelcomeViewThread(this);
		// initSounds();
		// playSound(1);
		initBitmap();
	}

	private void playSound(int i) {
		// TODO Auto-generated method stub
		AudioManager mgr = (AudioManager) getContext().getSystemService(
				Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;
		soundPool.play(soundPoolMap.get(i), volume, volume, 1, 0, 1f);
	}

	private void initBitmap() {
		// TODO Auto-generated method stub
		paint2 = new Paint();
		startGame = BitmapFactory.decodeResource(getResources(),
				R.drawable.kaishiyouxi);
		background2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.mainmenu);
		result = BitmapFactory.decodeResource(getResources(),
				R.drawable.yingxiongbangdan);
		openSound = BitmapFactory.decodeResource(getResources(),
				R.drawable.kaiqishengying);
		closeSound = BitmapFactory.decodeResource(getResources(),
				R.drawable.guanbishengying);
		exit = BitmapFactory.decodeResource(getResources(),
				R.drawable.tuichuyouxi);
		special = BitmapFactory.decodeResource(getResources(),
				R.drawable.xplane);
		especiall = BitmapFactory.decodeResource(getResources(),
				R.drawable.jitplane);
		godPlane = BitmapFactory.decodeResource(getResources(),
				R.drawable.godplane);
	}

	private void initSounds() {
		// TODO Auto-generated method stub
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap.put(1, soundPool.load(getContext(), R.raw.welcome1, 1));
	}

	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		Rect rect1 = new Rect(0, 0, width, height);
		Rect rect2 = new Rect(0, 0, background2.getWidth(),
				background2.getHeight());
		canvas.drawBitmap(background2, rect2, rect1, null);// 绘制背景图
		canvas.drawBitmap(startGame, width / 2 - startGame.getWidth() / 2,
				3 * (height - 4 * startGame.getHeight()) / 20, paint2);// 绘制开始游戏按钮
		canvas.drawBitmap(result, width / 2 - startGame.getWidth() / 2, (height
				- 2 * (height - 4 * startGame.getHeight()) / 5 - 2 * startGame
				.getHeight()) * 3 / 4, paint2);// 绘制英雄榜单按钮
		canvas.drawBitmap(exit, width / 2 - startGame.getWidth() / 2, (height
				- (height - 4 * startGame.getHeight()) / 5 - startGame
				.getHeight()) * 3 / 4, paint2);// 绘制退出按钮
		if (activity.isSound) {
			canvas.drawBitmap(closeSound, width / 2 - startGame.getWidth() / 2,
					(2 * (height - 4 * startGame.getHeight()) / 5 + startGame
							.getHeight()) * 3 / 4, paint2);// 绘制关闭声音菜单
		} else {
			canvas.drawBitmap(openSound, width / 2 - startGame.getWidth() / 2,
					(2 * (height - 4 * startGame.getHeight()) / 5 + startGame
							.getHeight()) * 3 / 4, paint2);// 绘制打开声音
		}

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
			} catch (InterruptedException e) {
			}
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			/*
			 * if(this.status != 4){/ return false; }
			 */
			double x = event.getX();
			double y = event.getY();
			if (x > width / 2 - startGame.getWidth() / 2
					&& x < width / 2 - startGame.getWidth() / 2
							+ openSound.getWidth()
					&& y > 3 * (height - 4 * startGame.getHeight()) / 20
					&& y < 3 * (height - 4 * startGame.getHeight()) / 20
							+ openSound.getHeight()) {
				activity.myhander.sendEmptyMessage(2);
			} else if (x > width / 2 - startGame.getWidth() / 2
					&& x < width / 2 - startGame.getWidth() / 2
							+ result.getWidth()
					&& y > (height - 2 * (height - 4 * startGame.getHeight())
							/ 5 - 2 * startGame.getHeight()) * 3 / 4
					&& y < (height - 2 * (height - 4 * startGame.getHeight())
							/ 5 - 2 * startGame.getHeight())
							* 3 / 4 + result.getHeight()) {
				activity.myhander.sendEmptyMessage(3);
			} else if (x > width / 2 - startGame.getWidth() / 2
					&& x < width / 2 - startGame.getWidth() / 2
							+ openSound.getWidth()
					&& y > (2 * (height - 4 * startGame.getHeight()) / 5 + startGame
							.getHeight()) * 3 / 4
					&& y < (2 * (height - 4 * startGame.getHeight()) / 5 + startGame
							.getHeight()) * 3 / 4 + openSound.getHeight()) {
				activity.isSound = !activity.isSound;
			} else if (x > width / 2 - startGame.getWidth() / 2
					&& x < width / 2 - startGame.getWidth() / 2
							+ exit.getWidth()
					&& y > (height - (height - 4 * startGame.getHeight()) / 5 - startGame
							.getHeight()) * 3 / 4
					&& y < (height - (height - 4 * startGame.getHeight()) / 5 - startGame
							.getHeight()) * 3 / 4 + exit.getHeight()) {
				System.exit(0);
			}
		}
		return super.onTouchEvent(event);
	}

	class TutorialThread extends Thread {
		private int span = 100;
		private SurfaceHolder surfaceHolder;
		private Welcome welcomeView;
		private boolean flag = false;

		public TutorialThread(SurfaceHolder surfaceHolder, Welcome welcomeView) {
			this.surfaceHolder = surfaceHolder;
			this.welcomeView = welcomeView;
		}

		public void setFlag(boolean flag) {// ���ñ�׼λ
			this.flag = flag;
		}

		public void run() {
			Canvas c;
			while (this.flag) {
				c = null;
				try {
					c = this.surfaceHolder.lockCanvas(null);
					synchronized (this.surfaceHolder) {
						welcomeView.draw(c);
					}
				} finally {
					if (c != null) {
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try {
					Thread.sleep(span);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
