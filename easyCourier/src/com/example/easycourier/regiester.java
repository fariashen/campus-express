/**
 * 
 */
package com.example.easycourier;

import com.example.easycourier.*;
import com.example.easycourier.*;
import com.example.easycourier.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author vacation
 * 
 *实现注册的模块
 *
 */
public class regiester extends Activity {

	/**
	 * @param args
	 * 
	 * 获取et_Regiester_userName控件
	 * 获取et_Regiester_passWord控件
	 * 获取bt_Regiester_Commit控件
	 * 
	 * 获取et_Regiester_userName控件的文本信息
	 * 获取et_Regiester_passWord控件的文本信息
	 * 
	 */
	EditText et_Regiester_userName;
	EditText et_Regiester_passWord;
	
	Button btCommit;
	
	regiesterHandle rgHandle;//实现注册功能的数据库操作类
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_regiester);
	
	//获取EditText控件
	
	et_Regiester_userName = (EditText) findViewById(R.id.et_Regiester_userName);
	et_Regiester_passWord = (EditText) findViewById(R.id.et_Regiester_passWord);
	
	final String REGIESTER_USERNAME = et_Regiester_userName.getText().toString();
	final String REGIESTER_PASSWORD = et_Regiester_passWord.getText().toString();
	final String REGIESTER_CONNECTURL="";//数据库链接地址
	btCommit = (Button) findViewById(R.id.bt_Regiester_commit);
	//得到控件"btCommit"
	
	//为 btCommit 设置监听器
	
	btCommit.setOnClickListener(new View.OnClickListener() {
		
		
		final EditText et_Regiester_passWord = (EditText) findViewById(R.id.et_Regiester_passWord);
    	final EditText et_Regiester_confirmpassWord = (EditText) findViewById(R.id.et_Regiester_confirmpassWord);
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//实例化数据操作类
			rgHandle = new regiesterHandle(REGIESTER_USERNAME, REGIESTER_PASSWORD, REGIESTER_CONNECTURL);
			
			//点击后触发调用数据库类插入方法
			rgHandle.run();
			
			final String edittextpassword1 = et_Regiester_passWord.getText().toString();
    		final String edittextpassword2 = et_Regiester_confirmpassWord.getText().toString();
    		if (!edittextpassword1.equals(edittextpassword2)) {
    	    	new AlertDialog.Builder(regiester.this)
    	    	.setTitle("密码输入不一致，请重新输入")
    	    	.setPositiveButton("确定",null).show();
    	    	}
    	    	else
    	    	{
    	    	new AlertDialog.Builder(regiester.this)
    	    	.setTitle("密码设置正确")
    	    	.setPositiveButton("确定",null).show();
    	    	Intent intent = new Intent(regiester.this,login.class);
        		startActivity(intent);
    	    	}	
			if(rgHandle.result){
				//插入用户记录成功，返回注册成功提示信息
				Toast.makeText(regiester.this, "注册成功", Toast.LENGTH_SHORT).show();
				//跳转返回登录界面
			}else{
				//插入用户记录失败，返回用户名重复信息
				Toast.makeText(regiester.this, "用户名重复", Toast.LENGTH_SHORT).show();
			}
		}
	});
	
}

}
