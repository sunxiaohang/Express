package com.example.m1320.express;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;

import android.util.AttributeSet;
import android.widget.TextView;

/**
 * ʵ�ִ��±߿��textView
 * 
 * @author Harely
 * 
 */
public class BorderText extends TextView {

	public BorderText(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ʵ����һ֧����
		Paint paint = new Paint();
		// ���������Ƶı߿���ɫΪ��ɫ
		paint.setColor(android.graphics.Color.BLACK);
		// �����ϱ߿�
		canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
		// ������߿�
		canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
		// �����ұ߿�
		canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
		       this.getHeight() - 1, paint);
		// �����±߿�
		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this
				.getHeight() - 1, paint);

	}

}
