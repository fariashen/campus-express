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

	LoginHandle lgHandle;

	public String LOGIN_USERNAME = "";
	public String LOGIN_PASSWORD = "";
	public String LOGIN_CONNECTURL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// ��ȡ�����ؼ�

		et_Login_userName = (EditText) findViewById(R.id.et_Login_userName);
		et_Login_passWord = (EditText) findViewById(R.id.et_Login_passWord);

		LOGIN_CONNECTURL = "http://172.25.224.24:8080/phpserver.php";

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

			// �����������ݿ����ѯ����
			// �������

			// �޸��û�
			// �������
			// ��¼�ɹ�

			lgHandle = new LoginHandle(LOGIN_USERNAME, LOGIN_CONNECTURL);
			lgHandle.start();

			// ��һ��������޸��û�
			if (lgHandle.result == "") {

				Toast.makeText(Login.this, "δ�ҵ����û�", Toast.LENGTH_SHORT).show();

			} else if (LOGIN_PASSWORD != lgHandle.result) {

				// �ڶ���������������

				Toast.makeText(Login.this, "�������", Toast.LENGTH_SHORT).show();

			} else {

				// �������������¼�ɹ�

				Toast.makeText(Login.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();

				// ��ת���� MainActivity

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
