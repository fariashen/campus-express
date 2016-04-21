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

import easyCourierHttpPost.AcceptRequestHttpPost;
import easyCourierHttpPost.HttpPostRequest;

public class RequestDetailShow extends Activity {

	private TextView detailProviderName;
	private TextView detailReward;
	private TextView detailSize;
	private TextView detailKinds;
	private TextView detailTimeLimit;
	private Button bt_acceptRequest;
	
	//获取RequestShowFragment.class中传过来的Bundle
	//从Bundle中获取相应的信息设置控件的值
	private Intent mIntent;
	public static Bundle mBundle;
	
	//请求信息
	private String itemProviderName;
	private String itemReward;
	private String itemTimeLimit;
	private String itemSize;
	private String itemKind;
	public static String itemOrderId;
	
	public static Context requestDetailShowContext;
	
	//网络请求类
	private HttpPostRequest RDSHttpPostRequest = new AcceptRequestHttpPost();
	
	//处理网络请求线程回传的消息
	public static Handler handler_RequestDetailShow = new Handler(){
		
		private Intent handleIntent;
		
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
			case 2:
				//接单失败 提示信息
				Toast.makeText(requestDetailShowContext, "接单失败,该单已被接！", Toast.LENGTH_LONG).show();
				
				break;
			
			case 1:
				//接单成功 跳转到快递请求的完整信息界面
				handleIntent = new Intent(requestDetailShowContext, ShowRequestAllInfo.class);
				handleIntent.putExtra("ItemAllInfo", mBundle);
				Toast.makeText(requestDetailShowContext, "接单成功！", Toast.LENGTH_LONG).show();
				
				requestDetailShowContext.startActivity(handleIntent);
				((Activity) requestDetailShowContext).finish();
				break;

			case 0:
				//接单失败 提示信息
				Toast.makeText(requestDetailShowContext, "接单失败,系统繁忙！", Toast.LENGTH_LONG).show();
				
				break;
				
			}
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.activity_requestdetail);
		requestDetailShowContext = this;
		
		detailProviderName = (TextView) findViewById(R.id.tv_DetailProviderName);
		detailReward =(TextView) findViewById(R.id.tv_DetailReward);
		detailSize = ((TextView) findViewById(R.id.tv_DetailSize));
		detailKinds = (TextView) findViewById(R.id.tv_DetailKinds);
		detailTimeLimit = (TextView) findViewById(R.id.tv_DetailTimeLimit);
		bt_acceptRequest = (Button) findViewById(R.id.bt_acceptRequest);
		
		mIntent = getIntent();
		mBundle = mIntent.getBundleExtra("RequestItemClick");
		
		//获取Bundle传来的信息
		itemProviderName = mBundle.getString("providerName");
		itemReward = mBundle.getString("reward");
		itemTimeLimit = mBundle.getString("timeLimit");
		itemSize = mBundle.getString("size");
		itemKind = mBundle.getString("kinds");
		itemOrderId = mBundle.getString("orderId");
		
		detailProviderName.setText(itemProviderName);
		detailReward.setText(itemReward);
		detailSize.setText(itemSize);
		detailKinds.setText(itemKind);
		detailTimeLimit.setText(itemTimeLimit);

		
		//当点击按钮时，发送网络请求完成接单操作
		bt_acceptRequest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//TODO
				RDSHttpPostRequest.start();
			}
		});
		
		
	}
	
}
