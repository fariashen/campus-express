package com.example.easycourier;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ReFlashListView extends ListView implements OnScrollListener  {

	private View header;	//���������ļ�
	private int headerHeight;	//���������ļ��ĸ߶�
	private int firstVisibleItem; //��ǰ��һ���ɼ��ؼ�
	private int scrollState; //��ǰ������״̬
	
	private boolean isRemark;// ��ǣ���ǰ����ҳ����������
	private int startY;//����ʱ��Yֵ
	
	private int state;// �����ƶ�ʱ��״̬
	private final int NONE = 0;// ����״̬
	private final int PULL = 1;// ��ʾ��������ˢ��
	private final int RELESE = 2;// ��ʾ�ɿ������ͷ�
	private final int REFLASHING = 3;// ����ˢ��
	private IReflashListener iReflashListener;//ˢ�����ݵĽӿ�
	
	public ReFlashListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		
		initView(context);
		
	}

	public ReFlashListView(Context context, AttributeSet attrs) {
		this(context,attrs,0);
	}

	public ReFlashListView(Context context) {
		this(context, null);
	}
	
	
	/**
	 * ��ʼ�����棬���Ӷ��������ļ���listView��
	 * @param context
	 */
	private void initView(Context context){
		
		LayoutInflater inflater = LayoutInflater.from(context);
		header = inflater.inflate(R.layout.header_layout, null);
		
		measureView(header);
		headerHeight = header.getMeasuredHeight();
		topPadding(-headerHeight);
		//���ӵ�listview
		this.addHeaderView(header);
		this.setOnScrollListener(this);
		
	}
	
	/**
	 * ֪ͨ������ռ�õĿ���
	 * @param view
	 */
	private void measureView(View view){
		
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if(p == null){
			
			p = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if(tempHeight>0){
			height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
		}else{
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		
		view.measure(width, height);
	}
	
	//ͨ�����ö�����padding��������ˢ�µĲ���
	private void topPadding(int topPadding){
		header.setPadding(header.getPaddingLeft(), topPadding,
				header.getPaddingRight(), header.getPaddingBottom());
		
		header.invalidate();
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
		this.scrollState = scrollState;
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
		//����firstVisibleItem�жϵ�ǰ�����Ƿ������
		this.firstVisibleItem = firstVisibleItem;
	}

	//���Ƶ��ж�
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (firstVisibleItem == 0) {
				isRemark = true;
				startY = (int) ev.getY();
			}
			
			break;

		case MotionEvent.ACTION_MOVE:
			onMove(ev);
			
			break;
			
		case MotionEvent.ACTION_UP:
			if (state == RELESE) {
				state = REFLASHING;
				//������������
				
				reflashViewByState();
				
				//��onReflash��������ݣ��ٰ��������õ�������
				iReflashListener.onReflash();
				
			} else if (state == PULL) {
				//����״̬
				
				state = NONE;
				isRemark = false;
				reflashViewByState();
				
			}
			break;
		}
		
		return super.onTouchEvent(ev);
	}

	//�ƶ������еĲ���
	private void onMove(MotionEvent ev) {

		if (!isRemark) {
			return;
		}
		//��ǰ�ƶ���λ��
		int tempY = (int) ev.getY();
		
		//���ƶ��ľ���
		int space = tempY - startY;
		
		//�����ƶ��ľ��벻������topPadding
		int topPadding = space - headerHeight;
		
		switch (state) {
		case NONE:
			if (space > 0) {
				//�����ƶ���
				
				state = PULL;
				reflashViewByState();
				
			}
			break;
		case PULL:
			topPadding(topPadding);
			if (space > headerHeight + 30
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				//����һ���߶ȣ��������ڵ�ǰ����״̬���ڹ���
				//��ʾ�����ɿ�
				
				state = RELESE;
				reflashViewByState();
				
			}
			break;
		case RELESE:
			topPadding(topPadding);
			if (space < headerHeight + 30) {
				//�����ƶ�������С��һ���߶�
				
				state = PULL;
				reflashViewByState();
				
			} else if (space <= 0) {
				//û����ק��
				
				state = NONE;
				isRemark = false;
				reflashViewByState();
			}
			break;
			
		}
	}
	
	//ˢ�����Ĳ���
	public void reflashComplete() {
		
		//���õ�ǰ״̬
		state = NONE;
		isRemark = false;
		reflashViewByState();
		TextView lastupdatetime = (TextView) header
				.findViewById(R.id.tv_header_lastupdate_time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		lastupdatetime.setText(time);
	}
	
	
	//���ݵ�ǰ��״̬����ʾ����
	private void reflashViewByState() {
		TextView tip = (TextView) header.findViewById(R.id.tv_header_tip);
		ImageView arrow = (ImageView) header.findViewById(R.id.iv_header_arrow);
		ProgressBar progress = (ProgressBar) header.findViewById(R.id.header_progress);
		
		//��ͷ�ı䷽��Ķ���Ч��
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);	//ʱ����
		anim.setFillAfter(true);
		RotateAnimation anim1 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		
		
		switch (state) {
		case NONE:
			//����״̬
			
			arrow.clearAnimation();	//�Ƴ�����
			topPadding(-headerHeight);
			break;

		case PULL:
			//����״̬
			
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("��������ˢ�£�");
			arrow.clearAnimation();
			arrow.setAnimation(anim1);
			
			break;
		case RELESE:
			//�ɿ�״̬
			
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("�ɿ�����ˢ�£�");
			arrow.clearAnimation();
			arrow.setAnimation(anim);
			
			break;
		case REFLASHING:
			//ˢ��״̬
			
			topPadding(50);
			arrow.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			tip.setText("����ˢ��");
			arrow.clearAnimation();
			
			break;
		}
	}
	
	
	//����ˢ�µĽӿ�
	public void setInterface(IReflashListener iReflashListener){
		this.iReflashListener = iReflashListener;
	}
	/**
	 * 
	 * ˢ�µĽӿ�
	 * 
	 */
	public interface IReflashListener{
		public void onReflash();
	}
	
}