package easyCourierFunction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.AnyRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easycourier.R;

import easyCourierHttpPost.RULHttpPost;

/**
 * 
 * @author vacation
 * 
 *         ����ʵ�ֻ�ȡ�û��������Ϣ��
 * 
 *         ����ȡ����Ϣ���ݸ��������� RULHttpPost.class
 * 
 *         ����������Զ�����ݿⷢ�Ͳ�������������ݿⷵ�ؽ����
 * 
 *         ���������ཫ����뷢�͸����̴߳���
 * 
 */
public class RequestUpLoad extends Activity {

	/**
	 * 
	 * ��ȡ���� EditText �ؼ�
	 * 
	 */
	private EditText et_providerName;
	private EditText et_nickName;
	private EditText et_reward;
	private EditText et_addFrom;
	private EditText et_addTo;
	private EditText et_timeLimit;
	private EditText et_size;
	private EditText et_phone;
	private EditText et_kinds;
	private EditText et_remarks;

	// ��ȡ��ȷ����ť��
	private Button bt_RequestCommit;

	// ��ʱ����û���Ϣ
	private String providerName;
	private String nickName;
	private String reward;
	private String addFrom;
	private String addTo;
	private String timeLimit;
	private String size;
	private String phone;
	private String kinds;
	private String remarks;

	public static Context RUL_context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestupload);
		getActionBar().setDisplayShowHomeEnabled(false);

		RUL_context = RequestUpLoad.this;

		/**
		 * ��ȡ���пؼ�
		 */

		et_providerName = (EditText) findViewById(R.id.et_RUL_providerName);
		et_nickName = (EditText) findViewById(R.id.et_RUL_nickName);
		et_reward = (EditText) findViewById(R.id.et_RUL_reward);
		et_addFrom = (EditText) findViewById(R.id.et_RUL_addFrom);
		et_addTo = (EditText) findViewById(R.id.et_RUL_addTo);
		et_timeLimit = (EditText) findViewById(R.id.et_RUL_timeLimit);
		et_size = (EditText) findViewById(R.id.et_RUL_size);
		et_phone = (EditText) findViewById(R.id.et_RUL_phone);
		et_kinds = (EditText) findViewById(R.id.et_RUL_kinds);
		et_remarks = (EditText) findViewById(R.id.et_RUL_remarks);

		bt_RequestCommit = (Button) findViewById(R.id.bt_RUL_Commit);

		/**
		 * Ϊ��ȷ������ť���ü���
		 * 
		 * ������ð�ť�������������������߳�
		 */
		bt_RequestCommit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// ��ȡ�û��������Ϣ
				providerName = et_providerName.getText().toString();
				nickName = et_nickName.getText().toString();
				reward = et_reward.getText().toString();
				addFrom = et_addFrom.getText().toString();
				addTo = et_addTo.getText().toString();
				timeLimit = et_timeLimit.getText().toString();
				size = et_size.getText().toString();
				phone = et_phone.getText().toString();
				kinds = et_kinds.getText().toString();
				remarks = et_remarks.getText().toString();

				// �ж���Ϣ�Ƿ���д���
				if (providerName.equals("") || nickName.equals("")
						|| reward.equals("") || addFrom.equals("")
						|| addTo.equals("") || timeLimit.equals("")
						|| size.equals("") || phone.equals("")
						|| kinds.equals("") || remarks.equals("")) {

					// �����û���Ϣδ��ȫ��д
					Toast.makeText(RequestUpLoad.this, "��Ϣδ��ȫ��д��",
							Toast.LENGTH_LONG).show();

				} else {

					// ����ȡ����Ϣ��Ϊ�������������������߳�
					RULHttpPost mHttpPost = new RULHttpPost(providerName,
							nickName, reward, addFrom, addTo, timeLimit, size,
							phone, kinds, remarks);

					// �������������߳�
					mHttpPost.start();

				}

			}
		});

	}

	// ������������̻߳ش�����Ϣ
	public static Handler RULHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			/**
			 * 
			 * ����1 ��ʾ�����ɹ�
			 * 
			 * ����2 ϵͳ��æ����ʧ��
			 * 
			 * ����3 ���㷢��ʧ��
			 * 
			 */

			case 1:

				Toast.makeText(RUL_context, "�����ɹ���", Toast.LENGTH_LONG).show();
				((Activity) RUL_context).finish();
				break;

			case 2:

				Toast.makeText(RUL_context, "ϵͳ��æ��", Toast.LENGTH_LONG).show();
				break;

			case 3:

				Toast.makeText(RUL_context, "���㣡", Toast.LENGTH_LONG).show();
				break;

			}

		};
	};

}