package com.example.easycourier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vacation
 * 
 *         ʵ�ֶԸ�����Ϣ���޸�
 * 
 *         �û�ͨ���޸�EditText��
 * 
 *         ��� "ȷ��" ��ť�󣬻�ȡEditText���ı���Ϣ���ݣ�
 * 
 *         ����ȡ��������Ϊ������ͨ��POST��ʽ���ݸ���̨���ݿ�
 * 
 */
public class PersonalInfoEdit extends Activity {

	/*
	 * ����
	 * 
	 * �û��������룬�绰���ʼ���EditText
	 */

	private EditText et_PersonalInfoEdit_UserName;
	private EditText et_PersonalInfoEdit_PassWord;
	private EditText et_PersonalInfoEdit_Phone;
	private EditText et_PersonalInfoEdit_Email;

	private Button bt_PersonalInfoEdit_Commit;

	String EDITED_USERNAME;
	String EDITED_PASSWORD;
	String EDITED_PHONE;
	String EDITED_EMAIL;

	PIEditHandle mEditHandle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_edit);

		// ��ȡ�����ؼ�

		et_PersonalInfoEdit_UserName = (EditText) findViewById(R.id.et_PersonalInfo_Username);
		et_PersonalInfoEdit_PassWord = (EditText) findViewById(R.id.et_PersonalInfo_Password);
		et_PersonalInfoEdit_Phone = (EditText) findViewById(R.id.et_PersonalInfo_Phone);
		et_PersonalInfoEdit_Email = (EditText) findViewById(R.id.et_PersonalInfo_Email);

		bt_PersonalInfoEdit_Commit = (Button) findViewById(R.id.bt_PersonalInfo_Commit);

		// ����EditText�ĳ�ʼֵ

		et_PersonalInfoEdit_UserName.setText(Login.LOGIN_USERNAME);
		et_PersonalInfoEdit_PassWord.setText(PIShowHandle.result_Password);
		et_PersonalInfoEdit_Phone.setText(PIShowHandle.result_Phone);
		et_PersonalInfoEdit_Email.setText(PIShowHandle.result_Email);

		// �����ȷ������ť���޸ĵ�ֵ�������ݿ�

		bt_PersonalInfoEdit_Commit
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						EDITED_USERNAME = et_PersonalInfoEdit_UserName
								.getText().toString();
						EDITED_PASSWORD = et_PersonalInfoEdit_PassWord
								.getText().toString();
						EDITED_PHONE = et_PersonalInfoEdit_Phone.getText()
								.toString();
						EDITED_EMAIL = et_PersonalInfoEdit_Email.getText()
								.toString();

						mEditHandle = new PIEditHandle(EDITED_USERNAME,
								EDITED_PASSWORD, EDITED_PHONE, EDITED_EMAIL);

						mEditHandle.start();

						if (PIEditHandle.PIEdit_result.equals("success")) {
							Toast.makeText(PersonalInfoEdit.this, "�޸���Ϣ�ɹ�",
									Toast.LENGTH_SHORT).show();
						}

					}
				});
	}

}