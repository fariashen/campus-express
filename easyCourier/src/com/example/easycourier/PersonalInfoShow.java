package com.example.easycourier;

import android.app.Activity;
import android.os.Bundle;
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
	
/*	参数
 * 
 * 用户名控件 
 * tv_PersonalInfo_Username
 * 
 * 密码控件
 * tv_PersonalInfo_Password
 * 
 * 电话控件
 * tv_PersonalInfo_Phone
 * 
 * 邮箱控件
 * tv_PersonalInfo_Email
 * 
 * 修改按钮
 * bt_PersonalInfo_Edit
 * 
 * */
	
	private TextView tv_PersonalInfoUserName;
	private TextView tv_PersonalInfoPassWord;
	private TextView tv_PersonalInfoPhone;
	private TextView tv_PersonalInfoEmail;
	
	private Button bt_PersonalInfoEdit;
	
	//数据库操作
	
	PIShowHandle mPiShowHandle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_show);
		
		//获取各个控件
		
		tv_PersonalInfoUserName = (TextView) findViewById(R.id.tv_PersonalInfo_Username);
		tv_PersonalInfoPassWord = (TextView) findViewById(R.id.tv_PersonalInfo_Password);
		tv_PersonalInfoPhone = (TextView) findViewById(R.id.tv_PersonalInfo_Phone);
		tv_PersonalInfoEmail = (TextView) findViewById(R.id.tv_PersonalInfo_Email);
		
		bt_PersonalInfoEdit = (Button) findViewById(R.id.bt_PersonalInfo_Commit);
		
		//发送数据库操作请求
		
		mPiShowHandle = new PIShowHandle();
		mPiShowHandle.start();
		
		//得到数据库返回值，修改相应控件
		
		tv_PersonalInfoUserName.setText(Login.LOGIN_USERNAME);
		tv_PersonalInfoPassWord.setText(PIShowHandle.result_Password);
		tv_PersonalInfoPhone.setText(PIShowHandle.result_Phone);
		tv_PersonalInfoEmail.setText(PIShowHandle.result_Email);
		
		
	}
	
}
