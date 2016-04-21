package easyCourierFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.easycourier.R;

import easyCourierFunction.PersonalInfoEdit;
import easyCourierFunction.PersonalInfoShow;
import easyCourierFunction.ShowAcceptedRequest;
import easyCourierFunction.ShowUpLoadedRequest;

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
	
	private Button bt_PersonalCenter_PI;
	private Button bt_PersonalCenter_UpLoadedRequest;
	private Button bt_PersonalCenter_AcceptedRequest;
	
	//启动相应的操作类
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	
		view = inflater.inflate(R.layout.fragment_personalcenter, container,false);
		
		/**
		 * 初始化控件
		 */
		bt_PersonalCenter_PI = (Button) view.findViewById(R.id.bt_PersonalCenter_PI);
		bt_PersonalCenter_UpLoadedRequest = (Button) view.findViewById(R.id.bt_PersonalCenter_UpLoadedRequest);
		bt_PersonalCenter_AcceptedRequest = (Button) view.findViewById(R.id.bt_PersonalCenter_AcceptedRequest);
		
		//为修改按钮设置监听
		bt_PersonalCenter_PI.setOnClickListener(this);
		bt_PersonalCenter_UpLoadedRequest.setOnClickListener(this);
		bt_PersonalCenter_AcceptedRequest.setOnClickListener(this);
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
		case R.id.bt_PersonalCenter_PI:
			
			intent = new Intent(view.getContext(), PersonalInfoShow.class);
			startActivity(intent);
			break;
			
		//当点击“已发送请求”按钮时，触发调用查询已发送请求方法
		case R.id.bt_PersonalCenter_UpLoadedRequest:
			
			//TODO
			intent = new Intent(view.getContext(), ShowUpLoadedRequest.class);
			startActivity(intent);
			
			break;
		//当点击“已接受请求”按钮时，触发调用查询已接受请求方法
		case R.id.bt_PersonalCenter_AcceptedRequest:
			
			intent = new Intent(view.getContext(), ShowAcceptedRequest.class);
			startActivity(intent);
			
			break;

		}
	}


}
