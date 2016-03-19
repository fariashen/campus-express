package com.example.easycourier;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author vacation
 *
 *实现登录模块
 *
 */
public class login extends Activity {

	/**
	 * @param args
	 * 
	 * 获取et_Login_userName控件
	 * 获取et_Login_passWord控件
	 * 获取bt_Login_Commit控件
	 * 
	 * 获取et_Login_userName控件的文本信息
	 * 获取et_Login_passWord控件的文本信息
	 * 
	 */
	
	 EditText et_Login_userName;
	 EditText et_Login_passWord;
	
	 
	Button bt_Login_Commit;
	
	loginHandle lgHandle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//获取各个控件
		
		et_Login_userName = (EditText) findViewById(R.id.et_Login_userName);
		et_Login_passWord = (EditText) findViewById(R.id.et_Login_passWord);
		
		final String LOGIN_USERNAME = et_Login_userName.getText().toString();
		final String LOGIN_PASSWORD = et_Login_passWord.getText().toString();
		final String LOGIN_CONNECTURL = "";
		
		
		bt_Login_Commit = (Button) findViewById(R.id.bt_Login_Commit);
		
		//为登录按钮设置监听器
		
		bt_Login_Commit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//触发调用数据库类查询方法
				//三种情况
				//登录成功
				//无该用户
				//密码错误
				
				lgHandle = new loginHandle(LOGIN_USERNAME, LOGIN_CONNECTURL);
				lgHandle.run();
				
				if(lgHandle.result == ""/*数据库查找记录为空*/){
						
					Toast.makeText(login.this, "未找到该用户", Toast.LENGTH_SHORT).show();
					
				}else if(LOGIN_PASSWORD != lgHandle.result/*数据库查找记录*/){
					
					Toast.makeText(login.this,"密码错误",Toast.LENGTH_SHORT).show();
						
			}else{
				Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
				//跳转返回主界面
			}
		}
		
	});


}
	}
