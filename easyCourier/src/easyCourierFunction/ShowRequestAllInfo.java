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
 *	成功接收请求后跳转到此界面
 *
 *	显示请求的全部信息
 */
/**
 * 
 * @author vacation 成功接收请求后跳转到此界面
 * 
 *         显示请求的全部信息
 */
public class ShowRequestAllInfo extends Activity {

	// 待赋值的控件
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

	// 请求的所有信息
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

		// 获取控件
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

		// 获取传送过来的Bundle
		mIntent = getIntent();
		mBundle = mIntent.getBundleExtra("ItemAllInfo");

		// 获取Bundle传来的信息
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

		// 设置控件显示内容
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

		// 检查是否因为点击查看“已发布请求”而触发此Activity的
		if (isCheckUpLoaded || isCheckAccepted) {

			// 根据ordflag的不同设置控件
			switch (Integer.parseInt(ordflag)) {

			// 发布的订单未被接受
			case 0:
				if (isCheckUpLoaded) {

					tv_tip_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setText("请求未被接受");

					bt_SRAI_Function.setVisibility(View.VISIBLE);
					bt_SRAI_Function.setText("取消请求");

					bt_SRAI_Function
							.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									// TODO 启动取消订单的线程
									mHttpPostRequest = new CancelRequestHttpPost(
											itemOrederId);
									mHttpPostRequest.start();
								}
							});

				}

				break;

			// 发布的订单已被接受
			case 1:

				tv_tip_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setText("请求正在进行");

				if (isCheckUpLoaded) {

					tv_tip_SRAI_CourierName.setVisibility(View.VISIBLE);
					tv_SRAI_CourierName.setVisibility(View.VISIBLE);
					tv_SRAI_CourierName.setText(courierName);

					bt_SRAI_Function.setVisibility(View.VISIBLE);
					bt_SRAI_Function.setText("确认收货");

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

			// 发布的订单已经完成
			case 2:

				tv_tip_SRAI_CourierName.setVisibility(View.VISIBLE);
				tv_SRAI_CourierName.setVisibility(View.VISIBLE);
				tv_SRAI_CourierName.setText(courierName);

				tv_tip_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setVisibility(View.VISIBLE);
				tv_SRAI_State.setText("请求已经完成");

				break;

			// 发布的请求已经取消
			case 3:

				if (isCheckUpLoaded) {

					tv_tip_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setVisibility(View.VISIBLE);
					tv_SRAI_State.setText("请求已经取消");

				}

				break;

			}
		}

	}

	public static Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			// 取消请求成功 返回 0
			// 确认收货成功 返回 1
			// 执行操作失败 返回 2

			switch (msg.what) {
			case 0:

				Toast.makeText(mContext, "取消请求成功！", Toast.LENGTH_SHORT).show();
				((Activity) mContext).finish();
				((Activity) ShowUpLoadedRequest.mContext).finish();
				break;

			case 1:

				Toast.makeText(mContext, "确认收货成功！", Toast.LENGTH_SHORT).show();
				((Activity) mContext).finish();
				((Activity) ShowUpLoadedRequest.mContext).finish();
				break;

			case 2:

				Toast.makeText(mContext, "执行操作失败！", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};
}
