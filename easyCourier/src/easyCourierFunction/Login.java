package easyCourierFunction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycourier.MainActivity;
import com.example.easycourier.R;

import easyCourierHttpPost.HttpPostRequest;
import easyCourierHttpPost.LoginHttpPost;

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

	public static HttpPostRequest lgHandle;

	/**
	 * 供各个功能模块设置用户名和密码的初始值
	 */
	public static String LOGIN_USERNAME;
	public static String LOGIN_PASSWORD;


	// 发送网络请求的地址
	// TODO
	public static String LOGIN_CONNECTURL = "http://119.29.4.159/phpserver/login.php";;

	public static Context loginContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().setDisplayShowHomeEnabled(false);

		loginContext = Login.this;

		// 获取各个控件

		/**
		 * 
		 * @author vacation
		 * 
		 *         设置默认头像
		 * 
		 */

		
		

		et_Login_userName = (EditText) findViewById(R.id.et_Login_userName);
		et_Login_passWord = (EditText) findViewById(R.id.et_Login_passWord);

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

			// 触发调用线程 发送HttpPost请求

			lgHandle = new LoginHttpPost();
			
			lgHandle.start();
			break;

		case R.id.tv_Regiester:

			Intent intent = new Intent(Login.this, Regiester.class);
			startActivity(intent);
			break;

		}
	}

	/*
	 * 处理 LoginHttpPost 线程返回的消息
	 * 
	 * 三种情况
	 * 
	 * 情况一：登录成功 情况二：密码错误 情况三：无该用户
	 */
	public static Handler loginHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:

				// 第一种情况：登录成功

				Toast.makeText(loginContext, "登录成功", Toast.LENGTH_SHORT).show();

				// 跳转返回 MainActivity

				Intent intent = new Intent(loginContext, MainActivity.class);
				loginContext.startActivity(intent);
				
				((Activity) loginContext).finish();
				
				break;

			case 2:

				// 第二种情况：密码错误
				Toast.makeText(loginContext, "密码错误", Toast.LENGTH_SHORT).show();

				break;
			case 3:

				// 第三种情况：无该用户
				Toast.makeText(loginContext, "未找到该用户", Toast.LENGTH_SHORT)
						.show();

				break;
			}

		}

	};
}
