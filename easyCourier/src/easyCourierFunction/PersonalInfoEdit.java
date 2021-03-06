package easyCourierFunction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easycourier.R;

import easyCourierHttpPost.PIEditHttpPost;

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
	 */

	private EditText et_PIE_UserName;
	private EditText et_PIE_PassWord;
	private EditText et_PIE_Phone;
	private EditText et_PIE_Address;
	private EditText et_PIE_Sex;

	private Button bt_PIE_Commit;

	private String PIE_Password;
	private String PIE_Phone;
	private String PIE_Address;
	private String PIE_Sex;

	PIEditHttpPost mEditHandle ;

	public static Context pieContext;

	private Intent mIntent;
	private Bundle mBundle;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_edit);
		getActionBar().setDisplayShowHomeEnabled(false);

		// 获取 PersonalInfoEdit 的 Context
		pieContext = PersonalInfoEdit.this;

		// 获取各个控件

		et_PIE_UserName = (EditText) findViewById(R.id.et_PIE_UserName);
		et_PIE_PassWord = (EditText) findViewById(R.id.et_PIE_PassWord);
		et_PIE_Phone = (EditText) findViewById(R.id.et_PIE_Phone);
		et_PIE_Address = (EditText) findViewById(R.id.et_PIE_Address);
		et_PIE_Sex = (EditText) findViewById(R.id.et_PIE_Sex);

		bt_PIE_Commit = (Button) findViewById(R.id.bt_PIE_Commit);

		// 获取从PersonalInfoShow传送过来的参数
		mIntent = getIntent();
		mBundle = mIntent.getBundleExtra("PIS_Init");

		// 设置EditText的初始值

		et_PIE_UserName.setText(Login.LOGIN_USERNAME);
		et_PIE_PassWord.setText(mBundle.getString("password"));
		et_PIE_Phone.setText(mBundle.getString("phone"));
		et_PIE_Address.setText(mBundle.getString("address"));
		et_PIE_Sex.setText(mBundle.getString("sex"));

		// 点击“确定”按钮后将修改的值传给 PIEditHttpPost 线程

		bt_PIE_Commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// 获取修改值
				PIE_Password = et_PIE_PassWord.getText().toString();
				PIE_Phone = et_PIE_Phone.getText().toString();
				PIE_Address = et_PIE_Address.getText().toString();
				PIE_Sex = et_PIE_Sex.getText().toString();

				mEditHandle = new PIEditHttpPost(PIE_Password, PIE_Phone,
						PIE_Address, PIE_Sex);

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
	 * 情况一：修改成功
	 * 
	 * 情况二：修改失败
	 */

	public static Handler PIE_Handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				
				Toast.makeText(pieContext, "修改信息成功", Toast.LENGTH_SHORT).show();

				// 修改成功后跳转回 个人中心界面
				((Activity) pieContext).finish();

				break;

			default:
				Toast.makeText(pieContext, "修改信息失败", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

}
