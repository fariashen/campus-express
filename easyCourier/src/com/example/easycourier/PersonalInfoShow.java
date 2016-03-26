package com.example.easycourier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 * 实现个人信息的展示
 * 
 * 通过 post 方式与 php 进行通信 ，
 * 获取相应用户的信息，
 * 分别赋值给相应的控件。
 * 
 * 当点击修改按钮时，触发调用 PersonalInfoEdit.java
 * 
 */

public class PersonalInfoShow extends Activity {

	/*
	 * 参数
	 * 
	 * 用户名控件 tv_PersonalInfo_Username
	 * 
	 * 密码控件 tv_PersonalInfo_Password
	 * 
	 * 电话控件 tv_PersonalInfo_Phone
	 * 
	 * 邮箱控件 tv_PersonalInfo_Email
	 * 
	 * 修改按钮 bt_PersonalInfo_Edit
	 */

	public static TextView tv_PersonalInfoUserName;
	public static TextView tv_PersonalInfoPassWord;

	private Button bt_PersonalInfoEdit;

	public static Context pisContext;

	// 网络线程操作

	PIShowHttpPost mPiShowHttpPost;

	public static Handler pisHandler = new Handler();

	// 链接地址
	public static String PISHOW_CONNECTURL = "http://119.29.4.159/phpserver/PIshow.php";

	// 获取个人信息，作为PersonalInfoEdit各个控件的默认值
	public static String PIS_PASSWORD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_show);

		// 获取各个控件

		tv_PersonalInfoUserName = (TextView) findViewById(R.id.tv_PersonalInfo_Username);
		tv_PersonalInfoPassWord = (TextView) findViewById(R.id.tv_PersonalInfo_Password);

		bt_PersonalInfoEdit = (Button) findViewById(R.id.bt_PersonalInfo_Edit);

		// 获取PersonalInfoShow 的Context

		pisContext = getApplicationContext();

		// 发送网络线程操作请求

		mPiShowHttpPost = new PIShowHttpPost();
		mPiShowHttpPost.start();


		// 为修改按钮设置监听

		bt_PersonalInfoEdit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取个人信息，作为PersonalInfoEdit各个控件的默认值
				
				PIS_PASSWORD = tv_PersonalInfoPassWord.getText().toString();
				
				Intent intent = new Intent(PersonalInfoShow.this,
						PersonalInfoEdit.class);
				startActivity(intent);
				finish();
			}
		});

	}

}
