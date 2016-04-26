package easyCourierFunction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycourier.R;

import easyCourierHttpPost.CancelRequestHttpPost;
import easyCourierHttpPost.CompletedRequestHttpPost;
import easyCourierHttpPost.HttpPostRequest;

/**
 * 
 * @author vacation
 *	�ɹ������������ת���˽���
 *
 *	��ʾ�����ȫ����Ϣ
 */
/**
 * 
 * @author vacation �ɹ������������ת���˽���
 * 
 *         ��ʾ�����ȫ����Ϣ
 */
public class ShowRequestAllInfo extends Activity {

	// ����ֵ�Ŀؼ�
	private TextView tv_SRAI_CourierName;
	private TextView tv_SRAI_NickName;
	private TextView tv_SRAI_Reward;
	private TextView tv_SRAI_AddForm;
	private TextView tv_SRAI_AddTo;
	private TextView tv_SRAI_TimeLimit;
	private TextView tv_SRAI_Size;
	private TextView tv_SRAI_Phone;
	private TextView tv_SRAI_Kinds;
	private TextView tv_SRAI_Remarks;
	private TextView tv_SRAI_State;

	private TextView tv_tip_SRAI_CourierName;
	private TextView tv_tip_SRAI_State;

	private Button bt_SRAI_Function;

	// �����������Ϣ
	private String itemOrederId;
	private String itemProviderName;
	private String courierName;
	private String itemNickName;
	private String itemReward;
	private String itemAddForm;
	private String itemAddTo;
	private String itemTimeLimit;
	private String itemSize;
	private String itemPhone;
	private String itemKind;
	private String itemRemarks;
	private String ordflag;

	private Boolean isCheckUpLoaded = false;
	private Boolean isCheckAccepted = false;

	private Intent mIntent;
	private Bundle mBundle;

	public static Context mContext;
	private HttpPostRequest mHttpPostRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showrequestallinfo);
		getActionBar().setDisplayShowHomeEnabled(false);
		mContext = this;

		// ��ȡ�ؼ�
		tv_SRAI_CourierName = (TextView) findViewById(R.id.tv_SRAI_CourierName);
		tv_SRAI_NickName = (TextView) findViewById(R.id.tv_SRAI_NickName);
		tv_SRAI_Reward = (TextView) findViewById(R.id.tv_SRAI_Reward);
		tv_SRAI_AddForm = (TextView) findViewById(R.id.tv_SRAI_AddForm);
		tv_SRAI_AddTo = (TextView) findViewById(R.id.tv_SRAI_AddTo);
		tv_SRAI_TimeLimit = (TextView) findViewById(R.id.tv_SRAI_TimeLimit);
		tv_SRAI_Size = (TextView) findViewById(R.id.tv_SRAI_Size);
		tv_SRAI_Phone = (TextView) findViewById(R.id.tv_SRAI_Phone);
		tv_SRAI_Kinds = (TextView) findViewById(R.id.tv_SRAI_Kinds);
		tv_SRAI_Remarks = (TextView) findViewById(R.id.tv_SRAI_Remarks);
		tv_SRAI_State = (TextView) findViewById(R.id.tv_SRAI_State);

		tv_tip_SRAI_CourierName = (TextView) findViewById(R.id.tv_tip_SRAI_CourierName);
		tv_tip_SRAI_State = (TextView) findViewById(R.id.tv_tip_SRAI_State);

		bt_SRAI_Function = (Button) findViewById(R.id.bt_SRAI_Function);

		// ��ȡ���͹�����Bundle
		mIntent = getIntent();
		mBundle = mIntent.getBundleExtra("ItemAllInfo");

		// ��ȡBundle��������Ϣ
		itemOrederId = mBundle.getString("orderId");
		itemProviderName = mBundle.getString("providerName");
		courierName = mBundle.getString("courierName");
		itemNickName = mBundle.getString("nickName");
		itemReward = mBundle.getString("reward");
		itemAddForm = mBundle.getString("addForm");
		itemAddTo = mBundle.getString("addTo");
		itemTimeLimit = mBundle.getString("timeLimit");
		itemSize = mBundle.getString("size");
		itemPhone = mBundle.getString("phone");
		itemKind = mBundle.getString("kinds");
		itemRemarks = mBundle.getString("remarks");
		ordflag = mBundle.getString("ordflag");

		isCheckUpLoaded = mBundle.getBoolean("isCheckUpLoaded");
		isCheckAccepted = mBundle.getBoolean("isCheckAccepted");

		// ���ÿؼ���ʾ����
		tv_SRAI_NickName.setText(itemNickName);
		tv_SRAI_NickName.setText(itemNickName);

		tv_SRAI_NickName.setText(itemNickName);
		tv_SRAI_Reward.setText(itemReward);
		tv_SRAI_AddForm.setText(itemAddForm);
		tv_SRAI_AddTo.setText(itemAddTo);
		tv_SRAI_TimeLimit.setText(itemTimeLimit);
		tv_SRAI_Size.setText(itemSize);
		tv_SRAI_Phone.setText(itemPhone);
		tv_SRAI_Kinds.setText(itemKind);
		tv_SRAI_Remarks.setText(itemRemarks);

		// ����Ƿ���Ϊ����鿴���ѷ������󡱶�������Activity��
		if (isCheckUpLoaded || isCheckAccepted) {

			// ����ordflag�Ĳ�ͬ���ÿؼ�
			switch (Integer.parseInt(ordflag)) {

			// �����Ķ���δ������
			case 0:
				if (isCheckUpLoaded) {

					tv_tip_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setText("����δ������");

					bt_SRAI_Function.setVisibility(View.VISIBLE);
					bt_SRAI_Function.setText("ȡ������");

					bt_SRAI_Function
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO ����ȡ���������߳�
									mHttpPostRequest = new CancelRequestHttpPost(
											itemOrederId);
									mHttpPostRequest.start();
								}
							});

				}

				break;

			// �����Ķ����ѱ�����
			case 1:

				tv_tip_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setText("�������ڽ���");

				if (isCheckUpLoaded) {

					tv_tip_SRAI_CourierName.setVisibility(View.VISIBLE);
					tv_SRAI_CourierName.setVisibility(View.VISIBLE);
					tv_SRAI_CourierName.setText(courierName);

					bt_SRAI_Function.setVisibility(View.VISIBLE);
					bt_SRAI_Function.setText("ȷ���ջ�");

					bt_SRAI_Function
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									mHttpPostRequest = new CompletedRequestHttpPost(
											itemOrederId);
									mHttpPostRequest.start();
								}
							});

				}

				break;

			// �����Ķ����Ѿ����
			case 2:

				tv_tip_SRAI_CourierName.setVisibility(View.VISIBLE);
				tv_SRAI_CourierName.setVisibility(View.VISIBLE);
				tv_SRAI_CourierName.setText(courierName);

				tv_tip_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setText("�����Ѿ����");

				break;

			// �����������Ѿ�ȡ��
			case 3:

				if (isCheckUpLoaded) {

					tv_tip_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setText("�����Ѿ�ȡ��");

				}

				break;

			}
		}

	}

	public static Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			// ȡ������ɹ� ���� 0
			// ȷ���ջ��ɹ� ���� 1
			// ִ�в���ʧ�� ���� 2

			switch (msg.what) {
			case 0:

				Toast.makeText(mContext, "ȡ������ɹ���", Toast.LENGTH_SHORT).show();
				((Activity) mContext).finish();
				((Activity) ShowUpLoadedRequest.mContext).finish();
				break;

			case 1:

				Toast.makeText(mContext, "ȷ���ջ��ɹ���", Toast.LENGTH_SHORT).show();
				((Activity) mContext).finish();
				((Activity) ShowUpLoadedRequest.mContext).finish();
				break;

			case 2:

				Toast.makeText(mContext, "ִ�в���ʧ�ܣ�", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};
}