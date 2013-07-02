package com.commics.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CommicsSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private static final float RATIO = 4f / 4f;
	private String text;
	private Paint fontPaint;
	private TextRect textRect;
	public CommicsSurfaceView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		SurfaceHolder h = this.getHolder();
		h.addCallback(this);
		fontPaint = new Paint();
		fontPaint.setAntiAlias( true );
		fontPaint.setTextSize( 24 );

	}
	
	public void drawText(String text)
	{
		this.text = text;
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
		int heigthWithoutPadding = height - getPaddingTop() - getPaddingBottom();

		int maxWidth = (int) (heigthWithoutPadding * RATIO);
		int maxHeight = (int) (widthWithoutPadding / RATIO);

		if (widthWithoutPadding > maxWidth) {
			width = maxWidth + getPaddingLeft() + getPaddingRight();
		} else {
			height = maxHeight + getPaddingTop() + getPaddingBottom();
		}

		if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
			width = getMeasuredWidth();
		}
		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
			height = getMeasuredHeight();
		}

		setMeasuredDimension(width, height);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		if( holder == null )
			return;

		Canvas c = null;

		try
		{
			if( (c = holder.lockCanvas()) != null )
				drawBubbles( c );
		}
		finally
		{
			if( c != null )
				holder.unlockCanvasAndPost( c );
		}


	}
	private void drawBubble(
			final Canvas c,
			final int x,
			final int y,
			final int width,
			final int height,
			final String text )
	{
		textRect = new TextRect( fontPaint );

		final int h = textRect.prepare(
				text,
				width - 8,
				height - 8 );
		
		
	}
	private void drawBubbles( final Canvas c )
	{

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{


	}

}
