package easyCourierFunction;

import com.example.easycourier.R;

import easyCourierHttpPost.HttpPostRequest;
import easyCourierHttpPost.SARHttpPost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

/**
 * 
 * @author vacation
 * 
 * 展示已接受的请求
 */

public class ShowAcceptedRequest extends Activity {

	public static ListView lv_ShowAccepted;
	public static Context mContext;
	
	public static Handler SAR_Handler = new Handler();
	
	private HttpPostRequest mHttpPostRequest;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.activity_showacceptedrequest);
		mContext = this;
		
		lv_ShowAccepted = (ListView) findViewById(R.id.lv_ShowAccepted);
		
		mHttpPostRequest = new SARHttpPost();
		mHttpPostRequest.start();
		
	}
	
}
