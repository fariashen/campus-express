package com.example.easycourier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
/*	����
 * 
 * �û����ؼ� 
 * tv_PersonalInfo_Username
 * 
 * ����ؼ�
 * tv_PersonalInfo_Password
 * 
 * �绰�ؼ�
 * tv_PersonalInfo_Phone
 * 
 * ����ؼ�
 * tv_PersonalInfo_Email
 * 
 * �޸İ�ť
 * bt_PersonalInfo_Edit
 * 
 * */
	
	private TextView tv_PersonalInfoUserName;
	private TextView tv_PersonalInfoPassWord;
	private TextView tv_PersonalInfoPhone;
	private TextView tv_PersonalInfoEmail;
	
	private Button bt_PersonalInfoEdit;
	
	//���ݿ����
	
	PIShowHandle mPiShowHandle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_show);
		
		//��ȡ�����ؼ�
		
		tv_PersonalInfoUserName = (TextView) findViewById(R.id.tv_PersonalInfo_Username);
		tv_PersonalInfoPassWord = (TextView) findViewById(R.id.tv_PersonalInfo_Password);
		tv_PersonalInfoPhone = (TextView) findViewById(R.id.tv_PersonalInfo_Phone);
		tv_PersonalInfoEmail = (TextView) findViewById(R.id.tv_PersonalInfo_Email);
		
		bt_PersonalInfoEdit = (Button) findViewById(R.id.bt_PersonalInfo_Commit);
		
		//�������ݿ��������
		
		mPiShowHandle = new PIShowHandle();
		mPiShowHandle.start();
		
		//�õ����ݿⷵ��ֵ���޸���Ӧ�ؼ�
		
		tv_PersonalInfoUserName.setText(Login.LOGIN_USERNAME);
		tv_PersonalInfoPassWord.setText(PIShowHandle.result_Password);
		tv_PersonalInfoPhone.setText(PIShowHandle.result_Phone);
		tv_PersonalInfoEmail.setText(PIShowHandle.result_Email);
		
//		Ϊ�޸İ�ť���ü���
		
		bt_PersonalInfoEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PersonalInfoShow.this, PersonalInfoEdit.class);
				startActivity(intent);
			}
		});
		
		
	}
	
}
