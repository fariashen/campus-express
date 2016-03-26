package com.example.easycourier;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vacation
 * 
 *         实现注册的模块
 * 
 */
public class Regiester extends Activity implements OnClickListener {

	/**
	 * @param args
	 * 
	 *            获取et_Regiester_userName控件 获取et_Regiester_passWord控件
	 *            获取et_Regiester_confirmpassWord控件 获取et_Regiester_Phone控件
	 *            获取et_Regiester_Email控件
	 * 
	 *            获取bt_Regiester_Commit控件
	 * 
	 *            获取et_Regiester_userName控件的文本信息 获取et_Regiester_passWord控件的文本信息
	 *            获取et_Regiester_confirmpassWord控件的文本信息
	 *            获取et_Regiester_Phone控件的文本信息 获取et_Regiester_Email控件的文本信息
	 * 
	 */
	EditText et_Regiester_userName;
	EditText et_Regiester_passWord;
	EditText et_Regiester_confirmpassWord;
	EditText et_Regiester_Phone;
	EditText et_Regiester_Email;

	Button btCommit;

	private CheckBox et_Regiester_checkbox = null;// 声明一个et_Regiester_checkbox对象

	String REGIESTER_USERNAME;
	String REGIESTER_PASSWORD_1;
	String REGIESTER_PASSWORD_2;
	String REGIESTER_PHONE;
	String REGIESTER_EMAIL;

	// 实现注册功能的数据库操作类
	RegiesterHttpPost rgHandle;

	private static Context regiestContext;

	// 链接地址
	public static String REGIESTER_CONNECTURL = "http://119.29.4.159/phpserver/register.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regiester);

		// 获取 Regiester 的Context
		regiestContext = getApplicationContext();

		// 获取EditText控件

		et_Regiester_userName = (EditText) findViewById(R.id.et_Regiester_userName);
		et_Regiester_passWord = (EditText) findViewById(R.id.et_Regiester_passWord);
		et_Regiester_confirmpassWord = (EditText) findViewById(R.id.et_Regiester_confirmpassWord);
		et_Regiester_Phone = (EditText) findViewById(R.id.et_Regiester_Phone);
		et_Regiester_Email = (EditText) findViewById(R.id.et_Regiester_Email);

		// 得到控件"btCommit"
		btCommit = (Button) findViewById(R.id.bt_Regiester_commit);

		// 以findViewById()方法取得et_Regiester_checkbox对象
		et_Regiester_checkbox = (CheckBox) findViewById(R.id.cb_Regiester_checkBox);

		// 默认设置为未选择状态
		et_Regiester_checkbox.setChecked(false);

		// 将注册按钮默认设置为未启用状态
		btCommit.setEnabled(false);

		// 为t_Regiester_checkbox设置监听
		et_Regiester_checkbox.setOnClickListener(this);

		// 为 btCommit 设置监听器

		btCommit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.cb_Regiester_checkBox:

			// checkBox 被点击后触发的行为

			if (et_Regiester_checkbox.isChecked()) {

				new AlertDialog.Builder(Regiester.this)
						.setTitle("您已完整阅读该协议并且同意")
						.setPositiveButton("确定", null).show();
				btCommit.setEnabled(true);
			} else {
				new AlertDialog.Builder(Regiester.this).setTitle("您未同意协议")
						.setPositiveButton("确定", null).show();
				btCommit.setEnabled(false);
			}
			break;

		case R.id.bt_Regiester_commit:

			// 注册按钮被点击后触发的行为

			// 获取用户名
			REGIESTER_USERNAME = et_Regiester_userName.getText().toString();
			// 获取密码
			REGIESTER_PASSWORD_1 = et_Regiester_passWord.getText().toString();
			// 获取确认密码
			REGIESTER_PASSWORD_2 = et_Regiester_confirmpassWord.getText()
					.toString();
			// 获取电话
			REGIESTER_PHONE = et_Regiester_Phone.getText().toString();
			// 获取邮箱
			REGIESTER_EMAIL = et_Regiester_Email.getText().toString();

			// 检查两次输入密码是否正确

			if (!REGIESTER_PASSWORD_1.equals(REGIESTER_PASSWORD_2)) {

				new AlertDialog.Builder(Regiester.this)
						.setTitle("密码输入不一致，请重新输入")
						.setPositiveButton("确定", null).show();

			}

			// 实例化数据操作类
			// 将 用户名，密码，电话，邮箱 的值传入函数方法

			rgHandle = new RegiesterHttpPost(REGIESTER_USERNAME,
					REGIESTER_PASSWORD_1, REGIESTER_PHONE, REGIESTER_EMAIL,
					REGIESTER_CONNECTURL);

			// 点击后触发调用 RegiesterHttpPost 线程
			rgHandle.start();

			break;
		}
	}

	/*
	 * 处理 RegiesterHttpPost 线程返回的消息
	 * 
	 * 三种情况
	 * 
	 * 情况一：服务器错误 
	 * 情况二：用户名重复 
	 * 情况三：成功注册
	 * 
	 */

	public static Handler regiesterHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {

			// 情况一：服务器错误
			case 1:
				Toast.makeText(regiestContext, "服务器正忙，请稍后再试",
						Toast.LENGTH_SHORT).show();
				break;

			// 情况二：用户名重复
			case 2:
				// 插入用户记录失败，返回用户名重复信息
				Toast.makeText(regiestContext, "用户名重复", Toast.LENGTH_SHORT)
				.show();
				break;

			// 情况三：成功注册
			case 3:
				// 插入用户记录成功，返回注册成功提示信息
				Toast.makeText(regiestContext, "注册成功", Toast.LENGTH_SHORT)
						.show();

				// 跳转到登录界面
				Intent intent = new Intent(regiestContext, Login.class);
				regiestContext.startActivity(intent);
				break;
			}

		};
	};

}
