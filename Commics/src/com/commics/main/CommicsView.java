package com.commics.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
public class CommicsView extends View {

	private static final float RATIO = 4f / 4f;
	private int mViewHeight;
	private int mViewWidth;
	private TextPaint mTextPaint;
	private String mText = "Mone pore ruby roy kobitay tomake";
	private int mTextBaseline;

	public CommicsView(Context context) 
	{
		super(context);
	}

	public CommicsView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		mTextPaint = new TextPaint();
		mTextPaint.setAntiAlias(true);
	}

	public CommicsView(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
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
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mViewHeight = h;
		mViewWidth = w;
		//adjustTextSize();
		//adjustTextScale();

	}

	public void putText(String text)
	{


	}
	void adjustTextSize()
	{
		mTextPaint.setTextSize(100);
		mTextPaint.setTextScaleX(1.0f);
		Rect bounds = new Rect();
		// ask the paint for the bounding rect if it were to draw this
		// text
		mTextPaint.getTextBounds(mText, 0, mText.length(), bounds);

		// get the height that would have been produced
		int h = bounds.bottom - bounds.top;

		// make the text text up 70% of the height
		float target = (float)mViewHeight*.7f;

		// figure out what textSize setting would create that height
		// of text
		float size  = ((target/h)*100f);

		// and set it into the paint
		mTextPaint.setTextSize(size);
	}

	void adjustTextScale() {
		// do calculation with scale of 1.0 (no scale)
		mTextPaint.setTextScaleX(1.0f);
		Rect bounds = new Rect();
		// ask the paint for the bounding rect if it were to draw this
		// text.
		mTextPaint.getTextBounds(mText, 0, mText.length(), bounds);

		// determine the width
		int w = bounds.right - bounds.left;

		// calculate the baseline to use so that the
		// entire text is visible including the descenders
		int text_h = bounds.bottom-bounds.top;
		mTextBaseline = bounds.bottom+((mViewHeight-text_h)/2);

		// determine how much to scale the width to fit the view
		float xscale = ((float) (mViewWidth-getPaddingLeft()-getPaddingRight())) / w;

		// set the scale for the text paint
		mTextPaint.setTextScaleX(xscale);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{		
		super.onDraw(canvas);
		//canvas.drawText(mText, 0, mViewHeight-mTextBaseline, mTextPaint);
		//drawMultiline(mText, 0, mViewHeight-mTextBaseline, mTextPaint, canvas);

		RectF rect = new RectF(0,mViewHeight/2,mViewWidth,mViewHeight/3);  
		StaticLayout sl = new StaticLayout(mText, mTextPaint, (int)rect.width(), Layout.Alignment.ALIGN_CENTER, 1, 1, false);  
		canvas.save(); 
		canvas.translate(rect.left, rect.top); 
		sl.draw(canvas); 
		canvas.restore(); 

	}

	public void drawMultiline(String str, int x, int y, Paint paint,Canvas cnavas)
	{
		for (String line: str.split("\n"))
		{
			cnavas.drawText(line, x, y, paint);
			y +=-paint.ascent() + paint.descent();
		}
	}



}