package easyCourierFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycourier.MainActivity;
import com.example.easycourier.R;

import easyCourierFunction.PersonalInfoEdit;
import easyCourierFunction.PersonalInfoShow;
import easyCourierHttpPost.PIShowHttpPost;

/**
 * 
 * @author vacation
 * 
 * 当点击“个人信息”按钮时，触发调用 PersonalInfoShow.java
 * 
 * 当点击“信息修改”按钮时，触发调用 PersonalInfoEdit.java
 * 
 * 当点击“已接受请求”按钮时，触发调用查询已接受请求方法
 * 
 */

public class PersonalCenterFragment extends Fragment implements OnClickListener {
	
	
	
	//保存PersonalCenterFragment的布局
	private View view;
	
	/**
	 * 参数
	 * 
	 * 用户名控件 tv_PersonalCenter_Username
	 * 
	 * 密码控件 tv_PersonalCenter_Password
	 * 
	 * 修改按钮 bt_PersonalCenter_Edit
	 * 
	 */
	
	private TextView tv_PersonalCenter_1;
	private TextView tv_PersonalCenter_2;
	private TextView tv_PersonalCenter_3;
	private TextView tv_PersonalCenter_4;
	private TextView tv_PersonalCenter_5;
	
	private Button bt_PersonalCenter_Exit;
	//启动相应的操作类
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	
		view = inflater.inflate(R.layout.fragment_personalcenter, container,false);
		
		/**
		 * 初始化控件
		 */
		tv_PersonalCenter_1 = (TextView) view.findViewById(R.id.tv_PersonalCenter_1);
		tv_PersonalCenter_2 = (TextView) view.findViewById(R.id.tv_PersonalCenter_2);
		tv_PersonalCenter_3 = (TextView) view.findViewById(R.id.tv_PersonalCenter_3);
		tv_PersonalCenter_4 = (TextView) view.findViewById(R.id.tv_PersonalCenter_4);
		tv_PersonalCenter_5 = (TextView) view.findViewById(R.id.tv_PersonalCenter_5);
		
		bt_PersonalCenter_Exit=(Button) view.findViewById(R.id.bt_PersonalCenter_Exit);
		//为修改按钮设置监听
		tv_PersonalCenter_1.setOnClickListener(this);
		tv_PersonalCenter_2.setOnClickListener(this);
		tv_PersonalCenter_3.setOnClickListener(this);
		tv_PersonalCenter_4.setOnClickListener(this);
		tv_PersonalCenter_5.setOnClickListener(this);
		
		bt_PersonalCenter_Exit.setOnClickListener(this);
		
		
		return view;
	}

	@Override
	public void onClick(View v) {

		/**
		 * 
		 * 判断选择控件的ID
		 * 
		 * 根据不同的ID，做出不同的响应
		 * 
		 */
		
		switch (v.getId()) {
		
		//当点击“个人信息”按钮时，触发调用 PersonalInfoShow.java
		case R.id.tv_PersonalCenter_1:
			
			intent = new Intent(view.getContext(), PersonalInfoShow.class);
			startActivity(intent);
			break;
			
		//当点击“信息修改”按钮时，触发调用 PersonalInfoEdit.java
		case R.id.tv_PersonalCenter_2:
			
			intent = new Intent(view.getContext(), PersonalInfoEdit.class);
			startActivity(intent);
			
			break;
			
		//当点击“已接受请求”按钮时，触发调用查询已接受请求方法
		case R.id.tv_PersonalCenter_3:
			
			/**
			 * 待定
			 */
			break;
			
        case R.id.tv_PersonalCenter_4:
			
			/**
			 * 待定
			 */
			break;
       
        case R.id.tv_PersonalCenter_5:
			
			/**
			 * 待定
			 */
			break;

        case R.id.bt_PersonalCenter_Exit:
			
        	showPopwindow();
			break;
		}
	}

	
	private void showPopwindow() {
		
		// 利用layoutInflater获得View  
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        View view = inflater.inflate(R.layout.actitvity_exit, null);  
		
        
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()  
        
        PopupWindow window = new PopupWindow(view,  
                WindowManager.LayoutParams.MATCH_PARENT,  
                WindowManager.LayoutParams.WRAP_CONTENT);  
  
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true  
        window.setFocusable(true);  
  
  
        // 实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        window.setBackgroundDrawable(dw);  
        
     // 设置popWindow的显示和消失动画  
        window.setAnimationStyle(R.style.mypopwindow_anim_style);  
        
     // 在底部显示  
        window.showAtLocation(PersonalCenterFragment.this.findViewById(R.id.bt_PersonalCenter_Exit),  
                Gravity.BOTTOM, 0, 0);  
  
        
        
        
        
	}

	private LayoutInflater getSystemService(String layoutInflaterService) {
		// TODO Auto-generated method stub
		return null;
	}

	private View findViewById(int btPersonalcenterExit) {
		// TODO Auto-generated method stub
		return null;
	}

	
}





