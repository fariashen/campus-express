package easyCourierFunction;

import com.example.easycourier.R;
import com.example.easycourier.R.id;
import com.example.easycourier.R.layout;

import easyCourierHttpPost.PIShowHttpPost;
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
	 */

	public static TextView tv_PersonalInfoUserName;
	public static TextView tv_PersonalInfoPassWord;
	/**
	 * ���߳�ִ���������󣬻�ȡ���ݺ󣬻������̷߳��͸���UI����
	 * �漰�����µĿؼ���Ҫ���ó�ȫ�־�̬���ԣ��Թ�����
	 */
	
	/**
	 * 
	 * �����̲߳���
	 * ��ȡ�û��ĸ�����Ϣ
	 * ��������Ӧ�ؼ���ֵ
	 * 
	 */

	PIShowHttpPost mPiShowHttpPost;

	//�������̷߳��͵ĸ���UI������
	public static Handler pisHandler = new Handler();

	// ���������͵�ַ
	//TODO
	public static String PISHOW_CONNECTURL = "http://172.25.224.12:801/phpserver/PIshow.php";

	// ��ȡ������Ϣ����ΪPersonalInfoEdit�����ؼ���Ĭ��ֵ
	public static String PIS_PASSWORD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_show);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		// ��ȡ�����ؼ�

		tv_PersonalInfoUserName = (TextView) findViewById(R.id.tv_PersonalInfo_Username);
		tv_PersonalInfoPassWord = (TextView) findViewById(R.id.tv_PersonalInfo_Password);

		// ���������̲߳�������

		mPiShowHttpPost = new PIShowHttpPost();
		mPiShowHttpPost.start();

		// ��ȡ������Ϣ����ΪPersonalInfoEdit�����ؼ���Ĭ��ֵ
		PIS_PASSWORD = tv_PersonalInfoPassWord.getText().toString();


	}

}