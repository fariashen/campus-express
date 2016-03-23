package com.example.easycourier;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author vacation
 * 
 *         实现登录模块
 * 
 */
public class Login extends Activity implements OnClickListener {

	/**
	 * @param args
	 * 
	 *            获取et_Login_userName控件 获取et_Login_passWord控件
	 * 
	 *            获取bt_Login_Commit控件
	 * 
	 *            获取tv_Regiester控件
	 * 
	 *            获取et_Login_userName控件的文本信息 获取et_Login_passWord控件的文本信息
	 * 
	 */

	EditText et_Login_userName;
	EditText et_Login_passWord;

	Button bt_Login_Commit;

	TextView tv_Regiester;

	LoginHandle lgHandle;

	public String LOGIN_USERNAME = "";
	public String LOGIN_PASSWORD = "";
	public String LOGIN_CONNECTURL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// 获取各个控件

		et_Login_userName = (EditText) findViewById(R.id.et_Login_userName);
		et_Login_passWord = (EditText) findViewById(R.id.et_Login_passWord);

		LOGIN_CONNECTURL = "http://172.25.224.24:8080/phpserver.php";

		bt_Login_Commit = (Button) findViewById(R.id.bt_Login_Commit);

		// 为登录按钮设置监听器

		bt_Login_Commit.setOnClickListener(this);

		// 为注册的文本设置监听器，通过点击注册文本跳转到 Regiester.java
		tv_Regiester = (TextView) findViewById(R.id.tv_Regiester);

		tv_Regiester.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bt_Login_Commit:

			LOGIN_USERNAME = et_Login_userName.getText().toString();
			LOGIN_PASSWORD = et_Login_passWord.getText().toString();

			// 触发调用数据库类查询方法
			// 三种情况

			// 无该用户
			// 密码错误
			// 登录成功

			lgHandle = new LoginHandle(LOGIN_USERNAME, LOGIN_CONNECTURL);
			lgHandle.start();

			// 第一种情况：无该用户
			if (lgHandle.result == "") {

				Toast.makeText(Login.this, "未找到该用户", Toast.LENGTH_SHORT).show();

			} else if (LOGIN_PASSWORD != lgHandle.result) {

				// 第二种情况：密码错误

				Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();

			} else {

				// 第三种情况：登录成功

				Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();

				// 跳转返回 MainActivity

				Intent intent = new Intent(Login.this, MainActivity.class);
				startActivity(intent);
			}
			break;

		case R.id.tv_Regiester:

			Intent intent = new Intent(Login.this, Regiester.class);
			startActivity(intent);
			break;

		}
	}
}
