package com.example.easycourier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import easyCourierFragment.FriendShowFragment;
import easyCourierFragment.PersonalCenterFragment;
import easyCourierFragment.RequestShowFragment;
import easyCourierFunction.RequestUpLoad;

/**
 * 设置ActionBar
 * 
 * 显示fragment
 * 
 * 实现底部图标颜色渐变
 * 
 * @author vacation
 * 
 */

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener {

	/**
	 * 中部内容区域 ViewPager+fragment 的实现参数
	 */
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mDatas;

	// 存放自定义View底部的三个Tab
	private List<ChangeColorIconWithText> mTab = new ArrayList<ChangeColorIconWithText>();

	//跳转到不同的功能模块执行不同个响应操作
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * 隐藏ActionBar默认图标
		 */
		getActionBar().setDisplayShowHomeEnabled(false);

		/**
		 * 初始化fragment
		 * 
		 * 获取底部view的三个Tab
		 */

		initView();

		// 初始化所有事件
		initEvent();
	}

	private void initEvent() {

		mViewPager.setOnPageChangeListener(this);
	}

	private void initView() {

		// 初始化中部内容区域的方法

		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mDatas = new ArrayList<Fragment>();

		RequestShowFragment tab01 = new RequestShowFragment();
		FriendShowFragment tab02 = new FriendShowFragment();
		PersonalCenterFragment tab03 = new PersonalCenterFragment();

		mDatas.add(tab01);
		mDatas.add(tab02);
		mDatas.add(tab03);

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {

				return mDatas.size();
			}

			@Override
			public Fragment getItem(int location) {

				return mDatas.get(location);
			}
		};

		mViewPager.setAdapter(mAdapter);

		// 获取底部三个Tab

		ChangeColorIconWithText showRequest = (ChangeColorIconWithText) findViewById(R.id.id_showrequest);
		ChangeColorIconWithText showFriend = (ChangeColorIconWithText) findViewById(R.id.id_showfriend);
		ChangeColorIconWithText personalCenter = (ChangeColorIconWithText) findViewById(R.id.id_personalcenter);

		mTab.add(showRequest);
		mTab.add(showFriend);
		mTab.add(personalCenter);

		showRequest.setOnClickListener(this);
		showFriend.setOnClickListener(this);
		personalCenter.setOnClickListener(this);

		// 默认选中第一个View
		showRequest.setIconAlpha(1.0f);

	}

	/**
	 * 设置在隐藏菜单内显示菜单项图标
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {

			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// 根据在隐藏菜单中选中的菜单项的ID，做出不同的响应
		switch (id) {

		// 选中“添加请求”
		case R.id.action_addrequest:
			
			//跳转到RequestUpLoad.class 执行相应的操作
			intent = new Intent(MainActivity.this,RequestUpLoad.class);
			startActivity(intent);
			break;

		// 选中“添加朋友”
		case R.id.action_addfriend:

			break;

		// 选中“意见反馈”
		case R.id.action_feedback:

			break;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		// 重置其它的Tab的颜色
		resetOtherTabs();

		switch (v.getId()) {
		case R.id.id_showrequest:
			mTab.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0, false);
			break;
		case R.id.id_showfriend:
			mTab.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1, false);
			break;
		case R.id.id_personalcenter:
			mTab.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2, false);
			break;
		}
	}

	private void resetOtherTabs() {

		for (int i = 0; i < mTab.size(); i++) {
			mTab.get(i).setIconAlpha(0);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * 当fragment滑动时，做出底部Tab按钮颜色渐变的效果
	 * 
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

		if (arg1 > 0) {
			ChangeColorIconWithText left = mTab.get(arg0);
			ChangeColorIconWithText right = mTab.get(arg0 + 1);
			left.setIconAlpha(1 - arg1);
			right.setIconAlpha(arg1);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}
}
