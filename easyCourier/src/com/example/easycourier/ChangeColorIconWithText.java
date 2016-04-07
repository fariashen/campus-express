package com.example.easycourier;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 
 * @author vacation
 * 
 *         该类实现的功能
 * 
 *         绘制底部自定义的三个Tab
 * 
 *         控制fragment滑动时Tab的颜色变化
 * 
 */

public class ChangeColorIconWithText extends View {

	/**
	 * 
	 * 定义底部Tab的四个基本属性
	 * 
	 */

	private int mColor = 0xFF45C01A;
	private Bitmap mIconBitmap;
	private String mText = "请求";
	private int mTextSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	/**
	 * 定义进行底部Tab绘制的三个变量
	 */

	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;
	/**
	 * 通过Bitmap获取一个Canvas
	 * 
	 * 通过Canvas获取一个Paint
	 * 
	 * 然后在内存中进行绘制
	 * 
	 */

	private float mAlpha;
	// 修改透明度变量

	private Rect mIconRect; // Icon的绘制范围
	private Rect mTextBound; // Text的绘制范围

	private Paint mTextPaint; // Text的Paint

	public ChangeColorIconWithText(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		// 通过TypedArray进行获取属性值

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ChangeColorIconWithText);

		// 通过遍历TypedArray里的所有属性，
		// 获取自定义控件属性的值，赋给当前变量

		int n = a.getIndexCount();

		for (int i = 0; i < n; i++) {

			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.ChangeColorIconWithText_icon:

				BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
				mIconBitmap = drawable.getBitmap();

				break;

			case R.styleable.ChangeColorIconWithText_color:

				mColor = a.getColor(attr, 0xFF45C01A);

				break;

			case R.styleable.ChangeColorIconWithText_text:

				mText = a.getString(attr);
				break;

			case R.styleable.ChangeColorIconWithText_text_size:

				mTextSize = (int) a.getDimension(attr, TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
								getResources().getDisplayMetrics()));
				break;
			}

		}

		a.recycle(); // 回收a

		mTextBound = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0xFF555555);
		// 测量字的范围
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

	}

	/**
	 * 
	 * 测量底部Icon的范围
	 * 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		/**
		 * 
		 * icon是一个正方形 icon的可用区域
		 * 高度：view的高度-topPadding-bottomPadding-mTextBound.height
		 * 宽度：view的宽度-leftPadding-rightPadding
		 * 
		 * 所以icon的边长是可用区域内宽度和高度的最小值
		 * 
		 */

		// 定义icon的边长
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextBound.height());

		// 设置icon的绘制范围，给rect进行赋值

		int left = getMeasuredWidth() / 2 - iconWidth / 2;
		int top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth
				/ 2;

		mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		// 绘制源图
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);

		int alpha = (int) Math.ceil(255 * mAlpha);

		/**
		 * 在内存里准备mBitmap
		 * 
		 * setAlpha
		 * 
		 * 绘制纯色
		 * 
		 * xfermode
		 * 
		 * 绘制图标
		 */

		setupTargetBitmap(alpha);

		// 绘制源文本
		drawSourceText(canvas, alpha);

		// 绘制变色文本
		drawTargetText(canvas, alpha);

		// 通过canvas把图标绘制出来
		canvas.drawBitmap(mBitmap, 0, 0, null);

	}

	/**
	 * 绘制变色文本
	 * 
	 * @param canvas
	 * @param alpha
	 */
	private void drawTargetText(Canvas canvas, int alpha) {

		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);
		int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
		int y = mIconRect.bottom + mTextBound.height();
		canvas.drawText(mText, x, y, mTextPaint);

	}

	/**
	 * 
	 * @param canvas
	 * @param alpha
	 * 
	 *            绘制源文本
	 * 
	 */
	private void drawSourceText(Canvas canvas, int alpha) {

		mTextPaint.setColor(0xff333333);
		mTextPaint.setAlpha(255 - alpha);
		int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
		int y = mIconRect.bottom + mTextBound.height();
		canvas.drawText(mText, x, y, mTextPaint);
	}

	/**
	 * 
	 * @param alpha
	 * 
	 *            根据alpha值，在内存中绘制可变色的Icon
	 */
	private void setupTargetBitmap(int alpha) {

		mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint();

		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);// 设置样式
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);

		// 设置纯色
		mCanvas.drawRect(mIconRect, mPaint);

		// 设置xfermode
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

		// 绘制图标
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
	}

	/**
	 * 让外界可以设置图标和文本
	 */

	public void setIconAlpha(float alpha) {

		this.mAlpha = alpha;
		invalidateView();
	}

	// 进行重绘
	private void invalidateView() {

		// 判断当前线程是不是UI线程
		if (Looper.getMainLooper() == Looper.myLooper()) {
			invalidate();
		} else {
			postInvalidate();
		}
	}

	public ChangeColorIconWithText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChangeColorIconWithText(Context context) {
		this(context, null);
	}

}
