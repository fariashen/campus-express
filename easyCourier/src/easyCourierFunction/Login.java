package easyCourierFunction;

import com.example.easycourier.MainActivity;
import com.example.easycourier.R;
import com.example.easycourier.R.drawable;
import com.example.easycourier.R.id;
import com.example.easycourier.R.layout;

import easyCourierHttpPost.LoginHttpPost;
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

/**
 * 
 * @author vacation
 * 
 *         ʵ�ֵ�¼ģ��
 * 
 */

public class Login extends Activity implements OnClickListener {

	/**
	 * @param args
	 * 
	 *            ��ȡet_Login_userName�ؼ� ��ȡet_Login_passWord�ؼ�
	 * 
	 *            ��ȡbt_Login_Commit�ؼ�
	 * 
	 *            ��ȡtv_Regiester�ؼ�
	 * 
	 *            ��ȡet_Login_userName�ؼ����ı���Ϣ ��ȡet_Login_passWord�ؼ����ı���Ϣ
	 * 
	 */

	EditText et_Login_userName;
	EditText et_Login_passWord;

	Button bt_Login_Commit;

	TextView tv_Regiester;

	LoginHttpPost lgHandle;

	/**
	 * ����������ģ�������û���������ĳ�ʼֵ
	 */
	public static String LOGIN_USERNAME;
	public static String LOGIN_PASSWORD;
	
	//������������ĵ�ַ
	//TODO
	private String LOGIN_CONNECTURL = "http://172.25.224.12:801/phpserver/login.php";;

	public static Context loginContext;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		loginContext = Login.this;

		// ��ȡ�����ؼ�

		/**
		 * 
		 * @author vacation
		 * 
		 *         ����Ĭ��ͷ��
		 * 
		 */

		ImageView imageView1 = (ImageView) findViewById(R.id.iv_Image);
		imageView1.setImageDrawable(getResources().getDrawable(
				R.drawable.kuaidi2));

		et_Login_userName = (EditText) findViewById(R.id.et_Login_userName);
		et_Login_passWord = (EditText) findViewById(R.id.et_Login_passWord);

		bt_Login_Commit = (Button) findViewById(R.id.bt_Login_Commit);

		// Ϊ��¼��ť���ü�����

		bt_Login_Commit.setOnClickListener(this);

		// Ϊע����ı����ü�������ͨ�����ע���ı���ת�� Regiester.java

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

			// ���������߳� ����HttpPost����

			lgHandle = new LoginHttpPost(LOGIN_USERNAME, LOGIN_PASSWORD,
					LOGIN_CONNECTURL);

			lgHandle.start();
			break;

		case R.id.tv_Regiester:

			Intent intent = new Intent(Login.this, Regiester.class);
			startActivity(intent);
			break;

		}
	}
	/*
	 * ���� LoginHttpPost �̷߳��ص���Ϣ
	 * 
	 * �������
	 * 
	 * ���һ����¼�ɹ�
	 * ��������������
	 * ��������޸��û�
	 * 
	*/
	public static Handler loginHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:

				// ��һ���������¼�ɹ�

				Toast.makeText(loginContext, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
				
				// ��ת���� MainActivity
				
				Intent intent = new Intent(loginContext, MainActivity.class);
				loginContext.startActivity(intent);
				((Activity) loginContext).finish();
				break;

			case 2:

				// �ڶ���������������
				Toast.makeText(loginContext, "�������", Toast.LENGTH_SHORT).show();

				break;
			case 3:

				// ������������޸��û�
				Toast.makeText(loginContext, "δ�ҵ����û�", Toast.LENGTH_SHORT)
						.show();

				break;
			}

		}

	};
}