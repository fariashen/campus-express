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
 *         实现对个人信息的修改
 * 
 *         用户通过修改EditText，
 * 
 *         点击 "确定" 按钮后，获取EditText的文本信息内容，
 * 
 *         将获取的内容作为参数，通过POST方式传递给后台数据库
 * 
 */
public class PersonalInfoEdit extends Activity {

	/*
	 * 参数
	 * 
	 * 用户名，密码，电话，邮件的EditText
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

		// 获取各个控件

		et_PersonalInfoEdit_UserName = (EditText) findViewById(R.id.et_PersonalInfo_Username);
		et_PersonalInfoEdit_PassWord = (EditText) findViewById(R.id.et_PersonalInfo_Password);
		et_PersonalInfoEdit_Phone = (EditText) findViewById(R.id.et_PersonalInfo_Phone);
		et_PersonalInfoEdit_Email = (EditText) findViewById(R.id.et_PersonalInfo_Email);

		bt_PersonalInfoEdit_Commit = (Button) findViewById(R.id.bt_PersonalInfo_Commit);

		// 设置EditText的初始值

		et_PersonalInfoEdit_UserName.setText(Login.LOGIN_USERNAME);
		et_PersonalInfoEdit_PassWord.setText(PIShowHandle.result_Password);
		et_PersonalInfoEdit_Phone.setText(PIShowHandle.result_Phone);
		et_PersonalInfoEdit_Email.setText(PIShowHandle.result_Email);

		// 点击“确定”按钮后将修改的值传给数据库

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
							Toast.makeText(PersonalInfoEdit.this, "修改信息成功",
									Toast.LENGTH_SHORT).show();
						}

					}
				});
	}

}
