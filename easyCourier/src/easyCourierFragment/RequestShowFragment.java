package easyCourierFragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.easycourier.ItemBean;
import com.example.easycourier.MyBaseAdapter;
import com.example.easycourier.R;
import com.example.easycourier.ReFlashListView;
import com.example.easycourier.ReFlashListView.IReflashListener;

import easyCourierHttpPost.RequsetShowFragHttpPost;

/**
 * 
 * @author vacation
 *
 *	ͨ��BaseAdapter ������������ʾ��ListView ��
 *
 *	���ݵ�ˢ�£���������һ���߳�
 *
 */
public class RequestShowFragment extends Fragment implements IReflashListener {

	//��������ĳ�Ա����
	private RequsetShowFragHttpPost mHttpPost;
	
	//�������̷߳��͵ĸ���UI������
	public static Handler RSF_Handler = new Handler();
	
	//�漰���²����ĳ�Ա����
	public static View view;
	public static ReFlashListView RSF_ListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_requestshow,container,false);
		
		/**
		 * 
		 * ����һ��ItemBean List
		 * 
		 * ÿһ��ItemBean�����൱��ListView�е�һ������
		 * 
		 */
		
		
		//��ȡ�����е�ListView
		RSF_ListView = (ReFlashListView) view.findViewById(R.id.lv_RequestShow);
		
		//����ListView�Ľӿ�
		//Fragment��ʵ���˸ýӿڣ�ֻ�轫Fragment�ش�
		RSF_ListView.setInterface(this);
	
		//ʵ����mHttpPost,��������������߳�
		
		startRequestShowHttpPost();
		
		return view;
	}

	private void startRequestShowHttpPost() {
		mHttpPost = new RequsetShowFragHttpPost();
		mHttpPost.start();
	}

	/**
	 * 
	 * ��ȡ��������
	 * 
	 * ֪ͨ������ʾ
	 * 
	 * ֪ͨlistview ˢ���������
	 * 
	 */
	@Override
	public void onReflash() {
		// TODO Auto-generated method stub
		
		//��ȡ�������ݣ������߳�
		//TODO
		startRequestShowHttpPost();
		
		//֪ͨ������ʾ
		//���߳��л�ȡˢ������ʱ���
		
		//֪ͨlistview ˢ���������
		RSF_ListView.reflashComplete();
	}
		
}