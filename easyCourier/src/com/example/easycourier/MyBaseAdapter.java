package com.example.easycourier;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter{

	private ViewHolder viewHolder;
	
	//将item的布局文件转换为View对象
	private LayoutInflater mInflater;
	
	//创建一个成员变量，保存传入的数据
	private List<ItemBean> mList;
	
	//为了从数据源中获取要传入的数据
	//创建一个构造方法
	public MyBaseAdapter(Context context,List<ItemBean> mList) {
		super();
		
		this.mList = mList;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {

		return mList.size();
	}


	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 
	 * 返回每一项的显示内容
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//如果缓冲池中不存在用过的View对象
		if(convertView == null){
			
			//实例化ViewHolder
			viewHolder = new ViewHolder();
			
			//通过mInflate对象将xml文件转换为需要的View对象
			convertView = mInflater.inflate(R.layout.item_request, null);
			
			//将布局中的控件保存到ViewHolder中
			viewHolder.kinds = (TextView) convertView.findViewById(R.id.tv_itemKinds);
			viewHolder.reward = (TextView) convertView.findViewById(R.id.tv_itemReward);
			viewHolder.size = (TextView) convertView.findViewById(R.id.tv_itemSize);
			
			//通过setTag()方法将viewHolder与convertView绑定在一起
			convertView.setTag(viewHolder);
		}else{
			
			//通过getTag()方法，取出与convertView绑定的viewHolder对象
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		//创建一个bean对象，获取保存在List中的数据
		ItemBean bean = mList.get(position);
		
		
		//通过viewHolder找到item的布局文件中的控件
		//通过bean对象，设置相应的控件
		viewHolder.kinds.setText(bean.itemKind);
		viewHolder.reward.setText(bean.itemReward);
		viewHolder.size.setText(bean.itemSize);
		
		return convertView;
	}
	
	/**
	 * 
	 * 定义一个内部类ViewHolder
	 * 
	 * 保存各个控件，避免重复的findViewById()操作
	 * 
	 */
	class ViewHolder{
		
		//定义成员变量，对应item布局中的控件
		public TextView kinds;
		public TextView reward;
		public TextView size;
	}
}
