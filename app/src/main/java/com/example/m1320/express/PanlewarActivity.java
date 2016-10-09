package com.example.m1320.express;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class PanlewarActivity extends Activity {
    int screenwidth;
    int screenheight;
    int action = 0;
    int score2 = 0;
    int score3;
    String name2;
    String name3;
   private boolean isPlay = true;
    // HelpView helpView;
    GameView gameview;
    ProcessView processview;
    HelpView helpview;
    Welcome welcome;
    reStartView restart;
    Cursor cur = null;
    String str = null;
    final String sql = "CREATE TABLE chengji (_id INTEGER PRIMARY KEY,name TEXT,score INTEGER)";
    int score = 0;
    int score1;
    private SQLiteDatabase mSQLiteDatabase = null;
    EditText edittext1;
    boolean isSound = true;// �Ƿ񲥷�����
    Handler myhander = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 4) {
                if (processview != null) {
                    processview = null;
                }
                welcome = new Welcome(PanlewarActivity.this, screenwidth, screenheight);
                toWelcomeView();
            } else if (msg.what == 3) {
                cur = mSQLiteDatabase.rawQuery("select *from chengji order by score;", null);
                initHelpView();
            } else if (msg.what == 7) {
                if (helpview != null) {
                    helpview = null;
                }
                welcome = new Welcome(PanlewarActivity.this, screenwidth,
                        screenheight);
                toWelcomeView();
            } else if (msg.what == 2) {
                if (welcome != null) {
                    welcome = null;
                }
                toGameView();
            } else if (msg.what == 5) {
                if (gameview != null) {

                    gameview.setFlag(false);
                    gameview = null;
                }
                cur = mSQLiteDatabase.rawQuery("select * from chengji where score <'" + score + "' order by score;", null);
                if (cur.getCount() > 0) {
                    if (cur.moveToFirst()) {
                        do {
                            if (score1 < cur.getInt(cur.getColumnIndex("score")))
                                score1 = cur.getInt(cur.getColumnIndex("score"));

                        } while (cur.moveToNext());
                    }
                    shangbang().show();
                } else {

                    torestartView();
                }
            } else if (msg.what == 8) {
                if (restart != null) {
                    restart = null;
                }
                welcome = new Welcome(PanlewarActivity.this, screenwidth,
                        screenheight);
                toWelcomeView();
            } else if (msg.what == 6) {
                if (restart != null) {
                    restart = null;
                }
                toGameView();
            }
        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenwidth = dm.widthPixels;
        screenheight = dm.heightPixels;

        processview = new ProcessView(this, 1, screenwidth, screenheight);
        this.setContentView(processview);
        mSQLiteDatabase = this.openOrCreateDatabase("cengji.db", MODE_PRIVATE, null);
        try {
            mSQLiteDatabase.execSQL(sql);
        } catch (Exception e) {

        }
        cur = mSQLiteDatabase.rawQuery("select *from chengji order by score;", null);
        for (; cur.getCount() < 5; ) {
            Adate("分数：", 65534);
            Adate("分数：", 45534);
            Adate("分数：", 35534);
            Adate("分数：", 1034);
            Adate("分数：", 534);
            break;
        }
    }


    private void Adate(String strin, int i) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put("name", strin);
        cv.put("score", i);
        mSQLiteDatabase.insert("chengji", null, cv);
    }

    public void torestartView() {
        // TODO Auto-generated method stub
        restart = new reStartView(this, screenwidth, screenheight);
        this.setContentView(restart);

    }

    protected void toGameView() {
        // TODO Auto-generated method stub
        gameview = new GameView(this, screenwidth, screenheight, 10, 3);
        this.setContentView(gameview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.panlewar, menu);
        return true;
    }

    public void toWelcomeView() {
        //	Log.i("XXX", "-----" + welcome);

        this.setContentView(welcome);
    }

    public void initHelpView() {
        // TODO Auto-generated method stub
        helpview = new HelpView(this, screenwidth, screenheight, cur);
        this.setContentView(helpview);
    }
    public AlertDialog shangbang() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
        edittext1 = (EditText) textEntryView.findViewById(R.id.username_edit);
        return new AlertDialog.Builder(PanlewarActivity.this)
                .setIcon(R.drawable.enemy)
                .setTitle(R.string.yingxiong)
                .setView(textEntryView)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        str = edittext1.getText().toString();
                        ;
                        if (cur.moveToFirst()) {
                            do {
                                ContentValues cv = new ContentValues();
                                score3 = score2;
                                name3 = name2;
                                score2 = cur.getInt(cur.getColumnIndex("score"));
                                name2 = cur.getString(cur.getColumnIndex("name"));
                                cv.put("score", score2);
                                cv.put("name", name2);
                                mSQLiteDatabase.update("chengji", cv, "score" + "=" + score3, null);
                            } while (cur.moveToNext());
                        }
                        ContentValues cv = new ContentValues();
                        cv.put("score", score);
                        cv.put("name", str);
                        mSQLiteDatabase.update("chengji", cv, "score" + "=" + score1, null);
                        torestartView();
                    }
                })
                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        torestartView();
                    /* User clicked cancel so do some stuff */
                    }
                }).create();
    }
}
