package easyCourierFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
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
	
	private Button bt_PersonalCenter_PI;
	private Button bt_PersonalCenter_Edit;
	private Button bt_PersonalCenter_Request;
	
	//������Ӧ�Ĳ�����
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	
		view = inflater.inflate(R.layout.fragment_personalcenter, container,false);
		
		/**
		 * ��ʼ���ؼ�
		 */
		bt_PersonalCenter_PI = (Button) view.findViewById(R.id.bt_PersonalCenter_PI);
		bt_PersonalCenter_Edit = (Button) view.findViewById(R.id.bt_PersonalCenter_Edit);
		bt_PersonalCenter_Request = (Button) view.findViewById(R.id.bt_PersonalCenter_Request);
		
		//Ϊ�޸İ�ť���ü���
		bt_PersonalCenter_PI.setOnClickListener(this);
		bt_PersonalCenter_Edit.setOnClickListener(this);
		bt_PersonalCenter_Request.setOnClickListener(this);
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
		case R.id.bt_PersonalCenter_PI:
			
			intent = new Intent(view.getContext(), PersonalInfoShow.class);
			startActivity(intent);
			break;
			
		//���������Ϣ�޸ġ���ťʱ���������� PersonalInfoEdit.java
		case R.id.bt_PersonalCenter_Edit:
			
			intent = new Intent(view.getContext(), PersonalInfoEdit.class);
			startActivity(intent);
			
			break;
			
		//��������ѽ������󡱰�ťʱ���������ò�ѯ�ѽ������󷽷�
		case R.id.bt_PersonalCenter_Request:
			
			/**
			 * ����
			 */
			break;

		}
	}


}