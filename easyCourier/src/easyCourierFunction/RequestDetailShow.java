package easyCourierFunction;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easycourier.R;

public class RequestDetailShow extends Activity {

	private TextView detailProviewName;
	private TextView detailReward;
	private TextView detailSize;
	private TextView detailKinds;
	private TextView detailTimeLimit;
	
	//��ȡRequestShowFragment.class�д�������Bundle
	//��Bundle�л�ȡ��Ӧ����Ϣ���ÿؼ���ֵ
	private Intent mIntent;
	private Bundle mBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.activity_requestdetail);
		
		detailProviewName = (TextView) findViewById(R.id.tv_DetailProviderName);
		detailReward =(TextView) findViewById(R.id.tv_DetailReward);
		detailSize = ((TextView) findViewById(R.id.tv_DetailSize));
		detailKinds = (TextView) findViewById(R.id.tv_DetailKinds);
		detailTimeLimit = (TextView) findViewById(R.id.tv_DetailTimeLimit);
		
		mIntent = getIntent();
		mBundle = mIntent.getBundleExtra("RequestItemClick");
		
		detailProviewName.setText(mBundle.getString("providerName"));
		detailReward.setText(mBundle.getString("reward"));
		detailSize.setText(mBundle.getString("size"));
		detailKinds.setText(mBundle.getString("kinds"));
		detailTimeLimit.setText(mBundle.getString("timeLimit"));
		
		
	}
	
}
