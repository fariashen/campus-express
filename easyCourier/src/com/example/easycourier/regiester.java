/**
 * 
 */
package com.example.easycourier;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vacation
 * 
 *         实现注册的模块
 * 
 */
public class Regiester extends Activity {

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

	RegiesterHandle rgHandle;// 实现注册功能的数据库操作类

	String REGIESTER_USERNAME;
	String REGIESTER_PASSWORD_1;
	String REGIESTER_PASSWORD_2;
	String REGIESTER_PHONE;
	String REGIESTER_EMAIL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regiester);

		// 获取EditText控件

		et_Regiester_userName = (EditText) findViewById(R.id.et_Regiester_userName);
		et_Regiester_passWord = (EditText) findViewById(R.id.et_Regiester_passWord);
		et_Regiester_confirmpassWord = (EditText) findViewById(R.id.et_Regiester_confirmpassWord);
		et_Regiester_Phone = (EditText) findViewById(R.id.et_Regiester_Phone);
		et_Regiester_Email = (EditText) findViewById(R.id.et_Regiester_Email);

		btCommit = (Button) findViewById(R.id.bt_Regiester_commit);
		// 得到控件"btCommit"

		// 为 btCommit 设置监听器

		btCommit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// 获取用户名
				REGIESTER_USERNAME = et_Regiester_userName.getText().toString();
				// 获取密码
				REGIESTER_PASSWORD_1 = et_Regiester_passWord.getText()
						.toString();
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

				} else {

					new AlertDialog.Builder(Regiester.this).setTitle("密码设置正确")
							.setPositiveButton("确定", null).show();

				}

				// 实例化数据操作类
				// 将 用户名，密码，电话，邮箱 的值传入函数方法

				rgHandle = new RegiesterHandle(REGIESTER_USERNAME,
						REGIESTER_PASSWORD_1, REGIESTER_PHONE, REGIESTER_EMAIL,
						Login.LOGIN_CONNECTURL);

				// 点击后触发调用数据库类插入方法
				rgHandle.start();

				if (rgHandle.Regiester_result.equals("3")) {
					// 插入用户记录成功，返回注册成功提示信息
					Toast.makeText(Regiester.this, "注册成功", Toast.LENGTH_SHORT)
							.show();

					// 跳转到登录界面
					Intent intent = new Intent(Regiester.this, Login.class);
					startActivity(intent);

				} else if (rgHandle.Regiester_result.equals("2")) {
					// 插入用户记录失败，返回用户名重复信息
					Toast.makeText(Regiester.this, "用户名重复", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(Regiester.this, "服务器正忙，请稍后再试",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

}
