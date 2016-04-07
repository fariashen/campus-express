package easyCourierFunction;

import com.example.easycourier.R;
import com.example.easycourier.R.id;
import com.example.easycourier.R.layout;

import easyCourierHttpPost.RegiesterHttpPost;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vacation
 * 
 *         ʵ��ע���ģ��
 * 
 */
public class Regiester extends Activity implements OnClickListener {

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

	private CheckBox et_Regiester_checkbox = null;// ����һ��et_Regiester_checkbox����

	String REGIESTER_USERNAME;
	String REGIESTER_PASSWORD_1;
	String REGIESTER_PASSWORD_2;

	// ע������URL
//TODO
	public static String REGIESTER_CONNECTURL = "http://172.25.224.12:801/phpserver/register.php";

	// ʵ��ע�Ṧ�ܵ�����������
	RegiesterHttpPost rgHttpPost;

	private static Context regiestContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regiester);

		// ��ȡ Regiester ��Context
		regiestContext = Regiester.this;

		// ��ȡEditText�ؼ�

		et_Regiester_userName = (EditText) findViewById(R.id.et_Regiester_userName);
		et_Regiester_passWord = (EditText) findViewById(R.id.et_Regiester_passWord);
		et_Regiester_confirmpassWord = (EditText) findViewById(R.id.et_Regiester_confirmpassWord);

		// �õ��ؼ�"btCommit"
		btCommit = (Button) findViewById(R.id.bt_Regiester_commit);

		// ��findViewById()����ȡ��et_Regiester_checkbox����
		et_Regiester_checkbox = (CheckBox) findViewById(R.id.cb_Regiester_checkBox);

		// Ĭ������Ϊδѡ��״̬
		et_Regiester_checkbox.setChecked(false);

		// ��ע�ᰴťĬ������Ϊδ����״̬
		btCommit.setEnabled(false);

		// Ϊt_Regiester_checkbox���ü���
		et_Regiester_checkbox.setOnClickListener(this);

		// Ϊ btCommit ���ü�����

		btCommit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.cb_Regiester_checkBox:

			// checkBox ������󴥷�����Ϊ

			if (et_Regiester_checkbox.isChecked()) {

				new AlertDialog.Builder(Regiester.this)
						.setTitle("���������Ķ���Э�鲢��ͬ��")
						.setPositiveButton("ȷ��", null).show();
				btCommit.setEnabled(true);
			} else {
				new AlertDialog.Builder(Regiester.this).setTitle("��δͬ��Э��")
						.setPositiveButton("ȷ��", null).show();
				btCommit.setEnabled(false);
			}
			break;

		case R.id.bt_Regiester_commit:

			System.out.println("--------------->click Button");
			// ע�ᰴť������󴥷�����Ϊ

			// ��ȡ�û���
			REGIESTER_USERNAME = et_Regiester_userName.getText().toString();
			// ��ȡ����
			REGIESTER_PASSWORD_1 = et_Regiester_passWord.getText().toString();
			// ��ȡȷ������
			REGIESTER_PASSWORD_2 = et_Regiester_confirmpassWord.getText()
					.toString();

			// ����������������Ƿ���ȷ

			if (!REGIESTER_PASSWORD_1.equals(REGIESTER_PASSWORD_2)) {

				new AlertDialog.Builder(Regiester.this)
						.setTitle("�������벻һ�£�����������")
						.setPositiveButton("ȷ��", null).show();

			}

			// ʵ��������������
			// �� �û��������룬�绰������ ��ֵ���뺯������

			rgHttpPost = new RegiesterHttpPost(REGIESTER_USERNAME,
					REGIESTER_PASSWORD_1, REGIESTER_CONNECTURL);

			// ����󴥷����� RegiesterHttpPost �߳�
			rgHttpPost.start();

			break;
		}
	}

	/*
	 * ���� RegiesterHttpPost �̷߳��ص���Ϣ
	 * 
	 * �������
	 * 
	 * ���һ������������ 
	 * ��������û����ظ�
	 * ��������ɹ�ע��
	 */

	public static Handler regiesterHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {

			// ���һ������������
			case 1:
				Toast.makeText(regiestContext, "��������æ�����Ժ�����",
						Toast.LENGTH_SHORT).show();
				break;

			// ��������û����ظ�
			case 2:
				// �����û���¼ʧ�ܣ������û����ظ���Ϣ
				Toast.makeText(regiestContext, "�û����ظ�", Toast.LENGTH_SHORT)
						.show();
				break;

			// ��������ɹ�ע��
			case 3:
				// �����û���¼�ɹ�������ע��ɹ���ʾ��Ϣ
				Toast.makeText(regiestContext, "ע��ɹ�", Toast.LENGTH_SHORT)
						.show();

				// ��ת����¼����
				Intent intent = new Intent(regiestContext, Login.class);
				regiestContext.startActivity(intent);
				((Activity) regiestContext).finish();
				break;
			}

		};
	};

}