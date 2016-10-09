package com.example.m1320.express;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback,
		Runnable {
	private boolean flagg = true;
	int shun = 0;
	boolean zhakai = false;
	private int jiguang = 10;
	private long second = -1200;
	private long third = -600;
	private long fourth = 0;
	SurfaceHolder sh;
	private boolean isPlay = true;
	private PanlewarActivity activity;
	private int width;
	private int height;
	private static boolean flag = true;
	Bitmap background4 = null, player = null, background5 = null,
			background6 = null, background7 = null;
	private Bitmap explosion;
	public float tm1, tm2;
	private Canvas canvas;
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	private ArrayList<Bullet> ebullets = new ArrayList<Bullet>();
	Thread thread = null;
	float x, y;
	int height1 = 0;
	Enamy enemy;
	int[] explodesID = new int[] { R.drawable.explode1, R.drawable.explode2,
			R.drawable.explode3, R.drawable.explode4, R.drawable.explode5,
			R.drawable.explode6, R.drawable.explode7, R.drawable.explode8,
			R.drawable.explode9, R.drawable.explode10, R.drawable.explode11,
			R.drawable.explode12, };
	Bitmap[] explodes = new Bitmap[explodesID.length];
	Bitmap jiguangpt;
	SoundPool soundPool;
	HashMap<Integer, Integer> soundPoolMap;
	MediaPlayer mMediaPlayer;
	ArrayList<Enamy> enemys = new ArrayList<Enamy>();
	int height2;
	int width1;
	int width2;
	int shoot, time;
	private Paint paint = new Paint();
	Paint paint2;
	private float xu;
	private float yu;
	private float xd;
	private float yd;
	private Bullet bullet;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int bomb = 10;
	private int ilife = 3;
	public static int score;
	Bitmap bulle;
	Bitmap bull;
	Bitmap ene;
	Bitmap xuetiao;
	boolean isSound;
	boolean jikai = false;
	private Bitmap pauseGame = null, playGame = null;

	public GameView(PanlewarActivity activity, int width, int height, int bomb,
			int ilife) {
		super(activity);
		sh = this.getHolder();
		sh.addCallback(this);
		initSounds();
		this.isPlay = true;
		this.isSound = activity.isSound;
		mMediaPlayer = MediaPlayer.create(activity, R.raw.gamestart);
		mMediaPlayer.setLooping(true);
		ene = BitmapFactory.decodeResource(GameView.this.getResources(),
				R.drawable.enemy);
		bulle = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bullet2);
		background7 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.background);
		background5 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.background5);
		background6 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.backgroud6);
		bull = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bullet);
		player = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.myplane);
		jiguangpt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.jiguang);
		xuetiao = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.xuetiao);
		playGame = BitmapFactory.decodeResource(getResources(),
				R.drawable.startgame);
		pauseGame = BitmapFactory.decodeResource(getResources(),
				R.drawable.pause);
		for (int i = 0; i < explodes.length; i++) {
			explodes[i] = BitmapFactory.decodeResource(getResources(),
					explodesID[i]);
		}
		this.activity = activity;
		this.width = width;
		this.height = height;
		this.height2 = -height;
		this.width1 = width / 2 - jiguangpt.getWidth() / 2;
		this.width2 = width / 2 - jiguangpt.getWidth() / 2;
		this.bomb = bomb;
		this.ilife = ilife;
		this.x = width / 2 - player.getWidth() / 2;
		this.y = height / 2;
		this.setFocusable(true);
		this.score = 0;

		if (isSound) {
			mMediaPlayer.start();
		}
		// TODO Auto-generated constructor stub
	}

	public void onDraw() {

		// if(isPlay != null){

		// }
		if (flag) {
			if (((score > 0 && score < 1500)) || (score > 5000 && score < 7500)) {
				background4 = background5;
			} else if ((score < 3000) || (score > 75000 && score < 8500)) {
				background4 = background6;
			} else if (score < 5000 || score > 8500) {
				background4 = background7;
			}
			canvas = sh.lockCanvas();
			Rect rect1 = new Rect(0, 0, background4.getWidth(),
					background4.getHeight());
			Rect rect2 = new Rect(0, height1, width, height + 10 + height1);
			Rect rect3 = new Rect(0, height2, width, height + 10 + height2);
			canvas.drawBitmap(background4, rect1, rect2, null);
			canvas.drawBitmap(background4, rect1, rect3, null);
			height1 += height / 100;
			height2 += height / 100;
			if (height1 >= height) {
				height1 = -height;
			}
			if (height2 >= height) {
				height2 = -height;
			}

			if (isPlay) {
				canvas.drawBitmap(pauseGame, width - 70, 20, paint);
				setFlag (true);
			
			} else {
				canvas.drawBitmap(playGame, width - 70, 20, paint);
				setFlag (false);
			}
			// 敌机
			for (int j = 0; j < enemys.size(); j++) {
				Enamy ene = enemys.get(j);

				if (shoot % 40 == 0) {

					Bullet bull = new Bullet(ene.x + 9, ene.y + 31, bulle,
							time, this);
					ebullets.add(bull);
				}
				if (ene.y < height) {

					ene.draw(canvas);

				} else {
					enemys.remove(ene);
				}
			}
			// 爆炸物
			for (int m = 0; m < explosions.size(); m++) {
				Explosion explo = explosions.get(m);
				for (; explo.m < 6; explo.m++) {
					explo.draw(canvas);
				}
				explosions.remove(explo);
			}
			// 敌机子弹
			for (int i = 0; i < ebullets.size(); i++) {
				Bullet bull = ebullets.get(i);
				float ebullx = bull.x;
				float ebully = bull.y;
				if (bull.y < height) {
					bull.draw2(canvas);
				} else {
					ebullets.remove(bull);
				}
				if (ebullx > x + 10 & ebullx < x + ene.getWidth() + 10
						& ebully > y & ebully < y + ene.getHeight()) {
					ebullets.remove(bull);
					ilife--;
					Explosion explo = new Explosion(x + 9, y + 9, explodes);
					explosions.add(explo);
					if (isSound) {
						playSound(2, 0);
					}
					if (ilife <= 0) {
						activity.score = score;
						activity.myhander.sendEmptyMessage(5);

						if (this.mMediaPlayer.isPlaying()) {
							this.mMediaPlayer.stop();
							playSound(3, 0);
						}
					}
				}
			}
			// 自己子弹
			for (int i = 0; i < bullets.size(); i++) {
				Bullet bul = bullets.get(i);
				float bullx = bul.x + bull.getWidth() / 2;
				float bully = bul.y;
				for (int k = 0; k < enemys.size(); k++) {
					Enamy eneb = enemys.get(k);
					float enemyx = eneb.x;
					float enemyy = eneb.y;
					// 碰撞
					// if (bullx < (enemyx + 100) & (bullx + 11) > enemyx
					// & bully < (enemyy + 100) & (bully + 11 > enemyy))
					if (Math.abs(bullx - enemyx) < 200
							& Math.abs(bully - enemyy) < 200) {
						score += 100;
						enemys.remove(eneb);
						bullets.remove(bul);
						Explosion explo = new Explosion(enemyx, enemyy,
								explodes);
						explosions.add(explo);

						if (isSound) {
							playSound(3, 0);
						}
					}
				}

				if (bul.y > 0) {
					bul.draw1(canvas);
				} else {
					bullets.remove(bul);
				}
			}

			// me

			for (int i = 0; i < bullets.size(); i++) {
				Bullet bul = bullets.get(i);
				float bullx = bul.x + bull.getWidth() / 2;
				float bully = bul.y;
				for (int k = 0; k < ebullets.size(); k++) {
					Bullet eneb = ebullets.get(k);
					float enemyx = eneb.x;
					float enemyy = eneb.y;
					// 碰撞
					if (bullx < (enemyx + 100) & (bullx + 11) > enemyx
							& bully < (enemyy + 100) & (bully + 11 > enemyy)) {
						score += 100;
						ebullets.remove(eneb);
						bullets.remove(bul);
						Explosion explo = new Explosion(enemyx, enemyy,
								explodes);
						explosions.add(explo);

						if (isSound) {
							playSound(3, 0);
						}
					}
				}

				if (bul.y > 0) {
					bul.draw1(canvas);
				} else {
					bullets.remove(bul);
				}
			}
			// me

			if (ilife <= 0) {
				for (int m = 0; m < explosions.size(); m++) {
					Explosion explo = explosions.get(m);
					for (; explo.m < 6; explo.m++) {
						explo.draw(canvas);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					explosions.remove(explo);
				}
			} else {
				canvas.drawBitmap(player, x, y, null);
			}
			// ??????
			if (zhakai) {
				for (int k = 0; k < enemys.size(); k++) {
					Enamy eneb = enemys.get(k);

					float enemyx = eneb.x;
					float enemyy = eneb.y;
					score += 100;

					enemys.remove(eneb);
					Explosion explo = new Explosion(enemyx, enemyy, explodes);
					explosions.add(explo);
				}
				for (int i = 0; i < ebullets.size(); i++) {

					Bullet bull = ebullets.get(i);
					ebullets.remove(bull);
				}
				zhakai = false;
			}
			if (jikai) {
				Rect rect4 = new Rect(0, 0, jiguangpt.getWidth(),
						jiguangpt.getHeight());
				Rect rect5 = new Rect(width1, 0, width1 + jiguangpt.getWidth(),
						height);
				Rect rect6 = new Rect(width2, 0, width2 + jiguangpt.getWidth(),
						height);
				canvas.drawBitmap(jiguangpt, rect4, rect5, null);
				canvas.drawBitmap(jiguangpt, rect4, rect6, null);
				for (int k = 0; k < enemys.size(); k++) {
					Enamy eneb = enemys.get(k);
					float enemyx = eneb.x;
					float enemyy = eneb.y;
					if (width1 < enemyx + 50 & width1 > enemyx
							| width2 > enemyx & width2 < enemyx + 50) {
						if (isSound) {
							playSound(3, 0);
						}
						score += 100;

						enemys.remove(eneb);
						Explosion explo = new Explosion(enemyx, enemyy,
								explodes);
						explosions.add(explo);
					}
				}
				for (int i = 0; i < ebullets.size(); i++) {
					Bullet bull = ebullets.get(i);
					if (width1 < bull.x + 50 & width1 > bull.x
							| width2 > bull.x & width2 < bull.x + 50) {
						ebullets.remove(bull);
					}
				}
				if (shun == 0) {
					width1 -= 10;
					width2 += 10;
				} else {
					width1 += 10;
					width2 -= 10;
				}
				if (width1 <= 0) {
					shun = 1;
				}
				if (width1 > width2 & shun == 1) {
					jikai = false;
					shun = 0;
					width1 = width / 2 - jiguangpt.getWidth() / 2;
					width2 = width1;
				}
			}
			paint.setColor(Color.WHITE);
			paint.setTextSize(30);
			canvas.drawText("炸弹" + bomb, 30, 50, paint);
			canvas.drawText("分数" + score, 30, 90, paint);
			canvas.drawText("血量：", 30, 130, paint);
			canvas.drawText("激光弹" + jiguang, 30, 170, paint);
			// canvas.drawBitmap(xuetiao, 90, 70, paint);
			paint.setColor(Color.GREEN);
			canvas.drawRect(120, 105, 120 + ilife * (xuetiao.getWidth() + 60)
					/ 3, 105 + xuetiao.getHeight(), paint);

			sh.unlockCanvasAndPost(canvas);
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	public void initSounds() {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap
				.put(1, soundPool.load(getContext(), R.raw.bulletsound1, 1));
		soundPoolMap.put(2, soundPool.load(getContext(), R.raw.explode, 1));
		soundPoolMap.put(3, soundPool.load(getContext(), R.raw.dead, 1));
	}

	public void playSound(int sound, int loop) {
		AudioManager mgr = (AudioManager) getContext().getSystemService(
				Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;

		soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		setFlag(true);
		thread = new Thread(this);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		try {
			soundPool = null;
			mMediaPlayer = null;
			setFlag(false);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		flag = false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xu = event.getX();
			yu = event.getY();
			xd = xu - x;
			yd = yu - y;
			second = third;
			third = fourth;
			fourth = System.currentTimeMillis();
			if (Math.abs(xu - width + 70) < 60 && Math.abs(yu) < 70) {
				isPlay = !isPlay;
			}

			if (fourth - third < 500) {// ???Ч??
				if (fourth - third < 500 & third - second < 500) {
					jiguang();
				} else {
					baozha();
				}
			}

			break;
		case MotionEvent.ACTION_MOVE:
			xu = event.getX();
			yu = event.getY();
			x = xu - xd;
			y = yu - yd;
			if (x <= 0) {
				x = 0;
			}
			if (y <= 0) {
				y = 0;
			}
			if (x >= width - player.getWidth()) {
				x = width - player.getWidth();
			}
			if (y >= height - player.getHeight()) {
				y = height - player.getHeight();
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.setFlag(false);
			if (mMediaPlayer != null) {
				mMediaPlayer = null;
			}
			activity.myhander.sendEmptyMessage(5);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
	
	public void setFlag(boolean flag) {// ??????λ
		this.flag = flag;
	}

	public void jiguang() {
		if (jiguang > 0) {
			bomb += 1;
			jiguang -= 1;
			jikai = true;
		}
	}

	public void baozha() {
		if (bomb > 0) {
			bomb -= 1;
			if (isSound) {
				playSound(3, 0);
			}

			zhakai = true;
		}
	}

	public void run() {
		//System.out.println("mmmmmmm");
		new Thread(new Runnable() {
			public void run() {
				Random r = new Random();
				float tm1 = 0, tm2 = 0;
				tm1 = System.currentTimeMillis();
				//System.out.println("kkkkkkk");
				while (flag) {

					int i = r.nextInt(9);
					int shui = 1000;
					if (enemys.size() < 20) {//
						if (i == 0) {
							enemy = new Enamy(player.getWidth() / 2
									- ene.getWidth() / 2, 0, ene, GameView.this);
							enemys.add(enemy);
						} else if (i == 1) {
							enemy = new Enamy(width / 8, 0, ene, GameView.this);
							enemys.add(enemy);
						} else if (i == 2) {
							enemy = new Enamy(2 * width / 8, 0, ene,
									GameView.this);
							enemys.add(enemy);
						} else if (i == 3) {
							enemy = new Enamy(3 * width / 8, 0, ene,
									GameView.this);
							enemys.add(enemy);
						} else if (i == 4) {
							enemy = new Enamy(4 * width / 8, 0, ene,
									GameView.this);
							enemys.add(enemy);
						} else if (i == 5) {
							enemy = new Enamy(5 * width / 8, 0, ene,
									GameView.this);
							enemys.add(enemy);
						} else if (i == 6) {
							enemy = new Enamy(6 * width / 8 - ene.getWidth()
									/ 2 - player.getWidth() / 2, 0, ene,
									GameView.this);
							enemys.add(enemy);
						} else if (i == 7) {
							enemy = new Enamy(7 * width / 8 - ene.getWidth()
									/ 2 - player.getWidth() / 2, 0, ene,
									GameView.this);
							enemys.add(enemy);
						} else {
							enemy = new Enamy(width - ene.getWidth() / 2
									- player.getWidth() / 2, 0, ene,
									GameView.this);
							enemys.add(enemy);
						}
					}//
					tm2 = System.currentTimeMillis();
					if (tm2 - tm1 > 5000) {
						shui = 500;
					}
					if (tm2 - tm1 > 30000) {
						shui = 400;
					}
					if (tm2 - tm1 > 60000) {
						shui = 250;
					}

					try {
						Thread.sleep(shui);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		tm1 = System.currentTimeMillis();
		System.out.println("99999");
		System.out.println("00000"+flagg);
		while (this.flag) {// ???
			if (shoot % 5 == 0) {
				bullet = new Bullet(x + player.getWidth() / 2 - bull.getWidth()
						/ 2, y, bull, 0, this);
				bullets.add(bullet);
			} else if (shoot == 100) {
				shoot = 0;
			}
			shoot += 1;
			tm2 = System.currentTimeMillis();
//			onDraw();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
