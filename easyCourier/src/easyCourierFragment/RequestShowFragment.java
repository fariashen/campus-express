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
 *	通过BaseAdapter 将所有请求显示在ListView 中
 *
 *	数据的刷新：重新启动一次线程
 *
 */
public class RequestShowFragment extends Fragment implements IReflashListener {

	//网络请求的成员变量
	private RequsetShowFragHttpPost mHttpPost;
	
	//接受子线程发送的更新UI的请求
	public static Handler RSF_Handler = new Handler();
	
	//涉及更新操作的成员变量
	public static View view;
	public static ReFlashListView RSF_ListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_requestshow,container,false);
		
		/**
		 * 
		 * 创建一个ItemBean List
		 * 
		 * 每一个ItemBean对象相当于ListView中的一条数据
		 * 
		 */
		
		
		//获取布局中的ListView
		RSF_ListView = (ReFlashListView) view.findViewById(R.id.lv_RequestShow);
		
		//设置ListView的接口
		//Fragment已实现了该接口，只需将Fragment回传
		RSF_ListView.setInterface(this);
	
		//实例化mHttpPost,并启动网络访问线程
		
		startRequestShowHttpPost();
		
		return view;
	}

	private void startRequestShowHttpPost() {
		mHttpPost = new RequsetShowFragHttpPost();
		mHttpPost.start();
	}

	/**
	 * 
	 * 获取最新数据
	 * 
	 * 通知界面显示
	 * 
	 * 通知listview 刷新数据完毕
	 * 
	 */
	@Override
	public void onReflash() {
		// TODO Auto-generated method stub
		
		//获取最新数据，调用线程
		//TODO
		startRequestShowHttpPost();
		
		//通知界面显示
		//在线程中获取刷新数据时完成
		
		//通知listview 刷新数据完毕
		RSF_ListView.reflashComplete();
	}
		
}
