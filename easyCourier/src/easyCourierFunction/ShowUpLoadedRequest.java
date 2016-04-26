package easyCourierFunction;

import com.example.easycourier.R;

import easyCourierHttpPost.HttpPostRequest;
import easyCourierHttpPost.SULRHttpPost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

/**
 * 
 * @author vacation
 * 
 * 展示已发送的请求
 */

public class ShowUpLoadedRequest extends Activity {

	public static ListView lv_ShowUpLoaded;
	public static Context mContext;
	
	public static Handler SULR_Handler = new Handler();
	
	private HttpPostRequest mHttpPostRequest;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.activity_showuploadedrequest);
		mContext = this;
		
		lv_ShowUpLoaded = (ListView) findViewById(R.id.lv_ShowUpLoaded);
		
		mHttpPostRequest = new SULRHttpPost();
		mHttpPostRequest.start();
		
		
		
	}
	
}
