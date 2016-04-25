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
	
	//��item�Ĳ����ļ�ת��ΪView����
	private LayoutInflater mInflater;
	
	//����һ����Ա���������洫�������
	private List<ItemBean> mList;
	
	//Ϊ�˴�����Դ�л�ȡҪ���������
	//����һ�����췽��
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
	 * ����ÿһ�����ʾ����
	 * 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//���������в������ù���View����
		if(convertView == null){
			
			//ʵ����ViewHolder
			viewHolder = new ViewHolder();
			
			//ͨ��mInflate����xml�ļ�ת��Ϊ��Ҫ��View����
			convertView = mInflater.inflate(R.layout.item_request, null);
			
			//�������еĿؼ����浽ViewHolder��
			viewHolder.kinds = (TextView) convertView.findViewById(R.id.tv_itemKinds);
			viewHolder.reward = (TextView) convertView.findViewById(R.id.tv_itemReward);
			viewHolder.size = (TextView) convertView.findViewById(R.id.tv_itemSize);
			
			//ͨ��setTag()������viewHolder��convertView����һ��
			convertView.setTag(viewHolder);
		}else{
			
			//ͨ��getTag()������ȡ����convertView�󶨵�viewHolder����
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		//����һ��bean���󣬻�ȡ������List�е�����
		ItemBean bean = mList.get(position);
		
		
		//ͨ��viewHolder�ҵ�item�Ĳ����ļ��еĿؼ�
		//ͨ��bean����������Ӧ�Ŀؼ�
		viewHolder.kinds.setText(bean.itemKind);
		viewHolder.reward.setText(bean.itemReward);
		viewHolder.size.setText(bean.itemSize);
		
		return convertView;
	}
	
	/**
	 * 
	 * ����һ���ڲ���ViewHolder
	 * 
	 * ��������ؼ��������ظ���findViewById()����
	 * 
	 */
	class ViewHolder{
		
		//�����Ա��������Ӧitem�����еĿؼ�
		public TextView kinds;
		public TextView reward;
		public TextView size;
	}
}
