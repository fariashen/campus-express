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
		
		 // ����һ���ӳ��߳�
        new Thread(this).start();
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            // �ӳ�����ʱ��
            Thread.sleep(3000);
            // ��ȡSharedPreferences����Ҫ������
            sp = getSharedPreferences("Y_Setting", Context.MODE_PRIVATE);
            startActivity(new Intent(WelcomeActivity.this,
            		Login.class));
            
            finish();
		
	}catch (Exception e) {
   }
  }
}
