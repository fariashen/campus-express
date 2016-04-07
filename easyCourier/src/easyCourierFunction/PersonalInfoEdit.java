package easyCourierFunction;

import com.example.easycourier.R;
import com.example.easycourier.R.id;
import com.example.easycourier.R.layout;

import easyCourierFragment.PersonalCenterFragment;
import easyCourierHttpPost.PIEditHttpPost;
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
	 * 
	 * �������ӵ�URL
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

	// ���ӵ�ַ
	//TODO
	public static String PIEDIT_CONNECTURL = "http://172.25.224.12:801/phpserver/PIedit.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_edit);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		// ��ȡ PersonalInfoEdit �� Context
		pieContext = PersonalInfoEdit.this;

		// ��ȡ�����ؼ�

		et_PersonalInfoEdit_UserName = (EditText) findViewById(R.id.et_PersonalInfo_Username);
		et_PersonalInfoEdit_PassWord = (EditText) findViewById(R.id.et_PersonalInfo_Password);

		bt_PersonalInfoEdit_Commit = (Button) findViewById(R.id.bt_PersonalInfo_Commit);

		// ����EditText�ĳ�ʼֵ

		et_PersonalInfoEdit_UserName.setText(Login.LOGIN_USERNAME);
		et_PersonalInfoEdit_PassWord.setText(Login.LOGIN_PASSWORD);

		// �����ȷ������ť���޸ĵ�ֵ���� PIEditHttpPost �߳�

		bt_PersonalInfoEdit_Commit
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						EDITED_PASSWORD = et_PersonalInfoEdit_PassWord
								.getText().toString();

						mEditHandle = new PIEditHttpPost(EDITED_PASSWORD);

						// ����PIEditHttpPost �߳�
						mEditHandle.start();

					}
				});
	}

	/*
	 * ���� PIEditHttpPost �̷߳��ص���Ϣ
	 * 
	 * �������
	 * 
	 * ���һ���޸ĳɹ� 
	 * 
	 * ��������޸�ʧ��
	 */

	public static Handler PIE_Handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(pieContext, "�޸���Ϣ�ɹ�", Toast.LENGTH_SHORT).show();
				
				// �޸ĳɹ�����ת�� �������Ľ���
				((Activity) pieContext).finish();
				
				break;

			default:
				Toast.makeText(pieContext, "�޸���Ϣʧ��", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

}