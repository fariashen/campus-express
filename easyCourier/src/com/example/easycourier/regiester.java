/**
 * 
 */
package com.example.easycourier;

import com.example.easycourier.*;
import com.example.easycourier.*;
import com.example.easycourier.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vacation
 * 
 *         ʵ��ע���ģ��
 * 
 */
public class Regiester extends Activity {

	/**
	 * @param args
	 * 
	 *            ��ȡet_Regiester_userName�ؼ� ��ȡet_Regiester_passWord�ؼ�
	 *            ��ȡet_Regiester_confirmpassWord�ؼ� ��ȡet_Regiester_Phone�ؼ�
	 *            ��ȡet_Regiester_Email�ؼ�
	 * 
	 *            ��ȡbt_Regiester_Commit�ؼ�
	 * 
	 *            ��ȡet_Regiester_userName�ؼ����ı���Ϣ ��ȡet_Regiester_passWord�ؼ����ı���Ϣ
	 *            ��ȡet_Regiester_confirmpassWord�ؼ����ı���Ϣ
	 *            ��ȡet_Regiester_Phone�ؼ����ı���Ϣ ��ȡet_Regiester_Email�ؼ����ı���Ϣ
	 * 
	 */
	EditText et_Regiester_userName;
	EditText et_Regiester_passWord;
	EditText et_Regiester_confirmpassWord;
	EditText et_Regiester_Phone;
	EditText et_Regiester_Email;

	Button btCommit;

	RegiesterHandle rgHandle;// ʵ��ע�Ṧ�ܵ����ݿ������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regiester);

		// ��ȡEditText�ؼ�

		et_Regiester_userName = (EditText) findViewById(R.id.et_Regiester_userName);
		et_Regiester_passWord = (EditText) findViewById(R.id.et_Regiester_passWord);
		et_Regiester_confirmpassWord = (EditText) findViewById(R.id.et_Regiester_confirmpassWord);
		et_Regiester_Phone = (EditText) findViewById(R.id.et_Regiester_Phone);
		et_Regiester_Email = (EditText) findViewById(R.id.et_Regiester_Email);

		final String REGIESTER_USERNAME = et_Regiester_userName.getText()
				.toString();

		// ��ȡ�״����������
		final String REGIESTER_PASSWORD_1 = et_Regiester_passWord.getText()
				.toString();

		// ��ȡ�ظ�ȷ�ϵ�����
		final String REGIESTER_PASSWORD_2 = et_Regiester_confirmpassWord
				.getText().toString();

		// ��ȡ�绰������Ϣ
		final String REGIESTER_PHONE = et_Regiester_Phone.getText().toString();

		// ��ȡ�����ַ��Ϣ
		final String REGIESTER_EMAIL = et_Regiester_Email.getText().toString();

		final String REGIESTER_CONNECTURL = "";// ���ݿ����ӵ�ַ

		btCommit = (Button) findViewById(R.id.bt_Regiester_commit);
		// �õ��ؼ�"btCommit"

		// Ϊ btCommit ���ü�����

		btCommit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// ����������������Ƿ���ȷ

				if (!REGIESTER_PASSWORD_1.equals(REGIESTER_PASSWORD_2)) {

					new AlertDialog.Builder(Regiester.this)
							.setTitle("�������벻һ�£�����������")
							.setPositiveButton("ȷ��", null).show();

				} else {

					new AlertDialog.Builder(Regiester.this).setTitle("����������ȷ")
							.setPositiveButton("ȷ��", null).show();

				}

				// ʵ�������ݲ�����
				// �� �û��������룬�绰������ ��ֵ���뺯������

				rgHandle = new RegiesterHandle(REGIESTER_USERNAME,
						REGIESTER_PASSWORD_1, 
						REGIESTER_PHONE, 
						REGIESTER_EMAIL,
						REGIESTER_CONNECTURL);

				// ����󴥷��������ݿ�����뷽��
				rgHandle.run();

				if (rgHandle.result) {
					// �����û���¼�ɹ�������ע��ɹ���ʾ��Ϣ
					Toast.makeText(Regiester.this, "ע��ɹ�", Toast.LENGTH_SHORT)
							.show();

					// ��ת����¼����
					Intent intent = new Intent(Regiester.this, Login.class);
					startActivity(intent);

				} else {
					// �����û���¼ʧ�ܣ������û����ظ���Ϣ
					Toast.makeText(Regiester.this, "�û����ظ�", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

	}

}