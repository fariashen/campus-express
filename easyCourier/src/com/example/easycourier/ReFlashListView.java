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

	private View header;	//顶部布局文件
	private int headerHeight;	//顶部布局文件的高度
	private int firstVisibleItem; //当前第一个可见控件
	private int scrollState; //当前滚动的状态
	
	private boolean isRemark;// 标记，当前是在页面的最顶端摁下
	private int startY;//摁下时的Y值
	
	private int state;// 摁下移动时的状态
	private final int NONE = 0;// 正常状态
	private final int PULL = 1;// 提示下拉可以刷新
	private final int RELESE = 2;// 提示松开可以释放
	private final int REFLASHING = 3;// 正在刷新
	private IReflashListener iReflashListener;//刷新数据的接口
	
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
	 * 初始化界面，添加顶部布局文件到listView内
	 * @param context
	 */
	private void initView(Context context){
		
		LayoutInflater inflater = LayoutInflater.from(context);
		header = inflater.inflate(R.layout.header_layout, null);
		
		measureView(header);
		headerHeight = header.getMeasuredHeight();
		topPadding(-headerHeight);
		//添加到listview
		this.addHeaderView(header);
		this.setOnScrollListener(this);
		
	}
	
	/**
	 * 通知父布局占用的宽高
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
	
	//通过设置顶部的padding隐藏下拉刷新的布局
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
		
		//根据firstVisibleItem判断当前界面是否在最顶端
		this.firstVisibleItem = firstVisibleItem;
	}

	//手势的判断
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
				//加载最新数据
				
				reflashViewByState();
				
				//在onReflash里加载数据，再把数据设置到界面上
				iReflashListener.onReflash();
				
			} else if (state == PULL) {
				//下拉状态
				
				state = NONE;
				isRemark = false;
				reflashViewByState();
				
			}
			break;
		}
		
		return super.onTouchEvent(ev);
	}

	//移动过程中的操作
	private void onMove(MotionEvent ev) {

		if (!isRemark) {
			return;
		}
		//当前移动的位置
		int tempY = (int) ev.getY();
		
		//已移动的距离
		int space = tempY - startY;
		
		//根据移动的距离不断设置topPadding
		int topPadding = space - headerHeight;
		
		switch (state) {
		case NONE:
			if (space > 0) {
				//往下移动了
				
				state = PULL;
				reflashViewByState();
				
			}
			break;
		case PULL:
			topPadding(topPadding);
			if (space > headerHeight + 30
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				//大于一定高度，并且正在当前滚动状态正在滚动
				//提示可以松开
				
				state = RELESE;
				reflashViewByState();
				
			}
			break;
		case RELESE:
			topPadding(topPadding);
			if (space < headerHeight + 30) {
				//往上移动，或者小于一定高度
				
				state = PULL;
				reflashViewByState();
				
			} else if (space <= 0) {
				//没有拖拽过
				
				state = NONE;
				isRemark = false;
				reflashViewByState();
			}
			break;
			
		}
	}
	
	//刷新完后的操作
	public void reflashComplete() {
		
		//设置当前状态
		state = NONE;
		isRemark = false;
		reflashViewByState();
		TextView lastupdatetime = (TextView) header
				.findViewById(R.id.tv_header_lastupdate_time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		lastupdatetime.setText(time);
	}
	
	
	//根据当前的状态来显示界面
	private void reflashViewByState() {
		TextView tip = (TextView) header.findViewById(R.id.tv_header_tip);
		ImageView arrow = (ImageView) header.findViewById(R.id.iv_header_arrow);
		ProgressBar progress = (ProgressBar) header.findViewById(R.id.header_progress);
		
		//箭头改变方向的动画效果
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);	//时间间隔
		anim.setFillAfter(true);
		RotateAnimation anim1 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		
		
		switch (state) {
		case NONE:
			//正常状态
			
			arrow.clearAnimation();	//移除动画
			topPadding(-headerHeight);
			break;

		case PULL:
			//下拉状态
			
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("下拉可以刷新！");
			arrow.clearAnimation();
			arrow.setAnimation(anim1);
			
			break;
		case RELESE:
			//松开状态
			
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("松开可以刷新！");
			arrow.clearAnimation();
			arrow.setAnimation(anim);
			
			break;
		case REFLASHING:
			//刷新状态
			
			topPadding(50);
			arrow.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			tip.setText("正在刷新");
			arrow.clearAnimation();
			
			break;
		}
	}
	
	
	//设置刷新的接口
	public void setInterface(IReflashListener iReflashListener){
		this.iReflashListener = iReflashListener;
	}
	/**
	 * 
	 * 刷新的接口
	 * 
	 */
	public interface IReflashListener{
		public void onReflash();
	}
	
}
