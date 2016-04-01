package com.example.easycourier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
	 * 
	 * 网络连接的URL
	 * 
	 */

	private EditText et_PersonalInfoEdit_UserName;
	private EditText et_PersonalInfoEdit_PassWord;

	private Button bt_PersonalInfoEdit_Commit;

	String EDITED_USERNAME;
	String EDITED_PASSWORD;
	String EDITED_PHONE;
	String EDITED_EMAIL;

	PIEditHttpPost mEditHandle;

	public static Context pieContext;

	// 链接地址
	public static String PIEDIT_CONNECTURL = "http://119.29.4.159/phpserver/PIedit.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_edit);

		// 获取 PersonalInfoEdit 的 Context
		pieContext = PersonalInfoEdit.this;

		// 获取各个控件

		et_PersonalInfoEdit_UserName = (EditText) findViewById(R.id.et_PersonalInfo_Username);
		et_PersonalInfoEdit_PassWord = (EditText) findViewById(R.id.et_PersonalInfo_Password);

		bt_PersonalInfoEdit_Commit = (Button) findViewById(R.id.bt_PersonalInfo_Commit);

		// 设置EditText的初始值

		et_PersonalInfoEdit_UserName.setText(Login.LOGIN_USERNAME);
		et_PersonalInfoEdit_PassWord.setText(PersonalInfoShow.PIS_PASSWORD);

		// 点击“确定”按钮后将修改的值传给 PIEditHttpPost 线程

		bt_PersonalInfoEdit_Commit
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						EDITED_PASSWORD = et_PersonalInfoEdit_PassWord
								.getText().toString();

						mEditHandle = new PIEditHttpPost(EDITED_PASSWORD);

						// 启动PIEditHttpPost 线程
						mEditHandle.start();

					}
				});
	}

	/*
	 * 处理 PIEditHttpPost 线程返回的消息
	 * 
	 * 两种情况
	 * 
	 * 情况一：修改成功 情况二：修改失败
	 */

	public static Handler PIE_Handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(pieContext, "修改信息成功", Toast.LENGTH_SHORT).show();
				// 修改成功后跳转回 个人信息展示界面
				Intent intent = new Intent(pieContext, PersonalInfoShow.class);
				pieContext.startActivity(intent);
				((Activity) pieContext).finish();
				break;

			default:
				Toast.makeText(pieContext, "修改信息失败", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

}
