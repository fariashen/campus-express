package easyCourierFunction;

import com.example.easycourier.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

public class WelcomeActivity extends Activity implements Runnable {

public static SharedPreferences sp;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_welcome);
		
		 // 启动一个延迟线程
        new Thread(this).start();
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            // 延迟两秒时间
            Thread.sleep(3000);
            // 读取SharedPreferences中需要的数据
            sp = getSharedPreferences("Y_Setting", Context.MODE_PRIVATE);
            startActivity(new Intent(WelcomeActivity.this,
            		Login.class));
            
            finish();
		
	}catch (Exception e) {
   }
  }
}
