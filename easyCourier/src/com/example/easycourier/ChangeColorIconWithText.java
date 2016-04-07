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
 *         ����ʵ�ֵĹ���
 * 
 *         ���Ƶײ��Զ��������Tab
 * 
 *         ����fragment����ʱTab����ɫ�仯
 * 
 */

public class ChangeColorIconWithText extends View {

	/**
	 * 
	 * ����ײ�Tab���ĸ���������
	 * 
	 */

	private int mColor = 0xFF45C01A;
	private Bitmap mIconBitmap;
	private String mText = "����";
	private int mTextSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	/**
	 * ������еײ�Tab���Ƶ���������
	 */

	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;
	/**
	 * ͨ��Bitmap��ȡһ��Canvas
	 * 
	 * ͨ��Canvas��ȡһ��Paint
	 * 
	 * Ȼ�����ڴ��н��л���
	 * 
	 */

	private float mAlpha;
	// �޸�͸���ȱ���

	private Rect mIconRect; // Icon�Ļ��Ʒ�Χ
	private Rect mTextBound; // Text�Ļ��Ʒ�Χ

	private Paint mTextPaint; // Text��Paint

	public ChangeColorIconWithText(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		// ͨ��TypedArray���л�ȡ����ֵ

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ChangeColorIconWithText);

		// ͨ������TypedArray����������ԣ�
		// ��ȡ�Զ���ؼ����Ե�ֵ��������ǰ����

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

		a.recycle(); // ����a

		mTextBound = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0xFF555555);
		// �����ֵķ�Χ
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

	}

	/**
	 * 
	 * �����ײ�Icon�ķ�Χ
	 * 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		/**
		 * 
		 * icon��һ�������� icon�Ŀ�������
		 * �߶ȣ�view�ĸ߶�-topPadding-bottomPadding-mTextBound.height
		 * ��ȣ�view�Ŀ��-leftPadding-rightPadding
		 * 
		 * ����icon�ı߳��ǿ��������ڿ�Ⱥ͸߶ȵ���Сֵ
		 * 
		 */

		// ����icon�ı߳�
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextBound.height());

		// ����icon�Ļ��Ʒ�Χ����rect���и�ֵ

		int left = getMeasuredWidth() / 2 - iconWidth / 2;
		int top = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth
				/ 2;

		mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);

	}

	@Override
	protected void onDraw(Canvas canvas) {

		// ����Դͼ
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);

		int alpha = (int) Math.ceil(255 * mAlpha);

		/**
		 * ���ڴ���׼��mBitmap
		 * 
		 * setAlpha
		 * 
		 * ���ƴ�ɫ
		 * 
		 * xfermode
		 * 
		 * ����ͼ��
		 */

		setupTargetBitmap(alpha);

		// ����Դ�ı�
		drawSourceText(canvas, alpha);

		// ���Ʊ�ɫ�ı�
		drawTargetText(canvas, alpha);

		// ͨ��canvas��ͼ����Ƴ���
		canvas.drawBitmap(mBitmap, 0, 0, null);

	}

	/**
	 * ���Ʊ�ɫ�ı�
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
	 *            ����Դ�ı�
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
	 *            ����alphaֵ�����ڴ��л��ƿɱ�ɫ��Icon
	 */
	private void setupTargetBitmap(int alpha) {

		mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint();

		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);// ������ʽ
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);

		// ���ô�ɫ
		mCanvas.drawRect(mIconRect, mPaint);

		// ����xfermode
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

		// ����ͼ��
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
	}

	/**
	 * ������������ͼ����ı�
	 */

	public void setIconAlpha(float alpha) {

		this.mAlpha = alpha;
		invalidateView();
	}

	// �����ػ�
	private void invalidateView() {

		// �жϵ�ǰ�߳��ǲ���UI�߳�
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
