package com.ec.namespace;
import android.app.Activity;    
import android.app.AlertDialog;
import android.os.Bundle;    
import android.view.View;    
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;   
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class EcActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
        Button button=(Button)findViewById(R.id.jump);      
        button.setOnClickListener(new Button.OnClickListener()
        {   
        	final EditText epassword1 = (EditText) findViewById(R.id.password1);
        	final EditText epassword2 = (EditText) findViewById(R.id.password2);
			public void onClick(View v){
        		final String edittextpassword1 = epassword1.getText().toString();
        		final String edittextpassword2 = epassword2.getText().toString();
        		if (!edittextpassword1.equals(edittextpassword2)) {
        	    	new AlertDialog.Builder(EcActivity.this)
        	    	.setTitle("密码输入不一致，请重新输入")
        	    	.setPositiveButton("确定",null).show();
        	    	}
        	    	else
        	    	{
        	    	new AlertDialog.Builder(EcActivity.this)
        	    	.setTitle("密码设置正确")
        	    	.setPositiveButton("确定",null).show();
        	    	Intent intent = new Intent(EcActivity.this,jumpActivity.class);
            		startActivity(intent);
        	    	}	
        	}});
        }
} 
    /* public void myclick(View source)    
       {    
             Toast.makeText(getApplicationContext(), "按钮被点击了", Toast.LENGTH_SHORT).show(); 
             Intent intent=new Intent(EcActivity.this,jumpActivity.class);       
		     startActivity(intent);
       }*/
    
   
    
    

