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
 * �������������Ϣ����ťʱ���������� PersonalInfoShow.java
 * 
 * ���������Ϣ�޸ġ���ťʱ���������� PersonalInfoEdit.java
 * 
 * ��������ѽ������󡱰�ťʱ���������ò�ѯ�ѽ������󷽷�
 * 
 */

public class PersonalCenterFragment extends Fragment implements OnClickListener {
	
	
	
	//����PersonalCenterFragment�Ĳ���
	private View view;
	
	/**
	 * ����
	 * 
	 * �û����ؼ� tv_PersonalCenter_Username
	 * 
	 * ����ؼ� tv_PersonalCenter_Password
	 * 
	 * �޸İ�ť bt_PersonalCenter_Edit
	 * 
	 */
	
	private TextView tv_PersonalCenter_1;
	private TextView tv_PersonalCenter_2;
	private TextView tv_PersonalCenter_3;
	private TextView tv_PersonalCenter_4;
	private TextView tv_PersonalCenter_5;
	
	private Button bt_PersonalCenter_Exit;
	//������Ӧ�Ĳ�����
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	
		view = inflater.inflate(R.layout.fragment_personalcenter, container,false);
		
		/**
		 * ��ʼ���ؼ�
		 */
		tv_PersonalCenter_1 = (TextView) view.findViewById(R.id.tv_PersonalCenter_1);
		tv_PersonalCenter_2 = (TextView) view.findViewById(R.id.tv_PersonalCenter_2);
		tv_PersonalCenter_3 = (TextView) view.findViewById(R.id.tv_PersonalCenter_3);
		tv_PersonalCenter_4 = (TextView) view.findViewById(R.id.tv_PersonalCenter_4);
		tv_PersonalCenter_5 = (TextView) view.findViewById(R.id.tv_PersonalCenter_5);
		
		bt_PersonalCenter_Exit=(Button) view.findViewById(R.id.bt_PersonalCenter_Exit);
		//Ϊ�޸İ�ť���ü���
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
		 * �ж�ѡ��ؼ���ID
		 * 
		 * ���ݲ�ͬ��ID��������ͬ����Ӧ
		 * 
		 */
		
		switch (v.getId()) {
		
		//�������������Ϣ����ťʱ���������� PersonalInfoShow.java
		case R.id.tv_PersonalCenter_1:
			
			intent = new Intent(view.getContext(), PersonalInfoShow.class);
			startActivity(intent);
			break;
			
		//���������Ϣ�޸ġ���ťʱ���������� PersonalInfoEdit.java
		case R.id.tv_PersonalCenter_2:
			
			intent = new Intent(view.getContext(), PersonalInfoEdit.class);
			startActivity(intent);
			
			break;
			
		//��������ѽ������󡱰�ťʱ���������ò�ѯ�ѽ������󷽷�
		case R.id.tv_PersonalCenter_3:
			
			/**
			 * ����
			 */
			break;
			
        case R.id.tv_PersonalCenter_4:
			
			/**
			 * ����
			 */
			break;
       
        case R.id.tv_PersonalCenter_5:
			
			/**
			 * ����
			 */
			break;

        case R.id.bt_PersonalCenter_Exit:
			
        	showPopwindow();
			break;
		}
	}

	
	private void showPopwindow() {
		
		// ����layoutInflater���View  
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        View view = inflater.inflate(R.layout.actitvity_exit, null);  
		
        
        // ���������ַ����õ���Ⱥ͸߶� getWindow().getDecorView().getWidth()  
        
        PopupWindow window = new PopupWindow(view,  
                WindowManager.LayoutParams.MATCH_PARENT,  
                WindowManager.LayoutParams.WRAP_CONTENT);  
  
        // ����popWindow��������ɵ������仰������ӣ�������true  
        window.setFocusable(true);  
  
  
        // ʵ����һ��ColorDrawable��ɫΪ��͸��  
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        window.setBackgroundDrawable(dw);  
        
     // ����popWindow����ʾ����ʧ����  
        window.setAnimationStyle(R.style.mypopwindow_anim_style);  
        
     // �ڵײ���ʾ  
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





