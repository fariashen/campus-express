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
 * ʵ�ָ�����Ϣ��չʾ
 * 
 * ͨ�� post ��ʽ�� php ����ͨ�� ��
 * ��ȡ��Ӧ�û�����Ϣ��
 * �ֱ�ֵ����Ӧ�Ŀؼ���
 * 
 * ������޸İ�ťʱ���������� PersonalInfoEdit.java
 * 
 */

public class PersonalInfoShow extends Activity {

	/*
	 * ����
	 * 
	 * �û����ؼ� tv_PersonalInfo_Username
	 * 
	 * ����ؼ� tv_PersonalInfo_Password
	 * 
	 * �绰�ؼ� tv_PersonalInfo_Phone
	 * 
	 * ����ؼ� tv_PersonalInfo_Email
	 * 
	 * �޸İ�ť bt_PersonalInfo_Edit
	 */

	public static TextView tv_PersonalInfoUserName;
	public static TextView tv_PersonalInfoPassWord;

	private Button bt_PersonalInfoEdit;

	public static Context pisContext;

	// �����̲߳���

	PIShowHttpPost mPiShowHttpPost;

	public static Handler pisHandler = new Handler();

	// ���ӵ�ַ
	public static String PISHOW_CONNECTURL = "http://119.29.4.159/phpserver/PIshow.php";

	// ��ȡ������Ϣ����ΪPersonalInfoEdit�����ؼ���Ĭ��ֵ
	public static String PIS_PASSWORD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_show);

		// ��ȡ�����ؼ�

		tv_PersonalInfoUserName = (TextView) findViewById(R.id.tv_PersonalInfo_Username);
		tv_PersonalInfoPassWord = (TextView) findViewById(R.id.tv_PersonalInfo_Password);

		bt_PersonalInfoEdit = (Button) findViewById(R.id.bt_PersonalInfo_Edit);

		// ��ȡPersonalInfoShow ��Context

		pisContext = getApplicationContext();

		// ���������̲߳�������

		mPiShowHttpPost = new PIShowHttpPost();
		mPiShowHttpPost.start();


		// Ϊ�޸İ�ť���ü���

		bt_PersonalInfoEdit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ������Ϣ����ΪPersonalInfoEdit�����ؼ���Ĭ��ֵ
				
				PIS_PASSWORD = tv_PersonalInfoPassWord.getText().toString();
				
				Intent intent = new Intent(PersonalInfoShow.this,
						PersonalInfoEdit.class);
				startActivity(intent);
				finish();
			}
		});

	}

}
