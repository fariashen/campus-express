package easyCourierHttpPost;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.easycourier.ItemBean;
import com.example.easycourier.MyBaseAdapter;

import easyCourierFragment.RequestShowFragment;
import easyCourierFunction.RequestDetailShow;

/**
 * 
 * @author vacation
 * 
 *         向远程数据库发送查询的操作请求
 * 
 *         获取Json格式的数据
 * 
 *         解释Json数据
 * 
 *         将解释后的数据封装在List<ItemBean> 中
 * 
 *         向主线程发送更新UI的请求：
 * 
 *         1、将获取的数据绑定适配器
 * 
 *         2、监听ListView的Item点击事件
 * 
 * 
 */
public class RequsetShowFragHttpPost extends HttpPostRequest {

	// 保存已解释的json数据
	public static List<ItemBean> itemBeanList = new ArrayList<ItemBean>();

	// 要添加数据的数据项
	private ItemBean mItemBean;
	private String itemOrederId = "";
	private String itemProviderName = "";
	private String itemNickName = "";
	private String itemReward = "";
	private String itemAddForm = "";
	private String itemAddTo = "";
	private String itemTimeLimit = "";
	private String itemSize = "";
	private String itemPhone = "";
	private String itemKind = "";
	private String itemRemarks = "";
	
	// 数据适配器
	private MyBaseAdapter myBaseAdapter;

	// 启动Activity的成员变量
	private Intent mIntent;

	// 将用户点击的Item的所有信息值写入Bundle
	// 在启动RequestDetailShow.class的时候，将Bundle传过去
	private Bundle bundle;

	@Override
	protected void setUrlAddress() {

		urlAddress = UrlFactory.getUrl("RequestShow");
	}

	@Override
	protected void setParamsIntoPost() {

	}

	@Override
	protected void setIsJsonData() {

		isJsonData = true;
		itemBeanList.clear();
	}

	@Override
	protected void getDifferentJsonArrayValue() {

		// 根据元素名设置ItemBean中成员变量的值

		try {

			System.out.println("------------->RequestShow item");
			itemOrederId = mJsonObject.getString("orderID");
			itemProviderName = mJsonObject.getString("providerName");
			itemNickName = mJsonObject.getString("nickName");
			itemReward = mJsonObject.getString("reward");
			itemAddForm = mJsonObject.getString("addFrom");
			itemAddTo = mJsonObject.getString("addTo");
			itemTimeLimit = mJsonObject.getString("timeLimit");
			itemSize = mJsonObject.getString("size");
			itemPhone = mJsonObject.getString("phone");
			itemKind = mJsonObject.getString("kinds");
			itemRemarks = mJsonObject.getString("remarks");

			System.out.println("------------------->RequestShow orderId"
					+ mJsonObject.getString("orderID"));
			mItemBean = new ItemBean(itemOrederId, itemProviderName,
					itemNickName, itemReward, itemAddForm, itemAddTo,
					itemTimeLimit, itemSize, itemPhone, itemKind, itemRemarks);

			System.out.println("------------>RequestShow mItemBean"
					+ mItemBean.toString());
			itemBeanList.add(mItemBean);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void backToMainThreadHandleMessage() {

		// 向主线程发送更新UI的请求
		RequestShowFragment.RSF_Handler.post(new Runnable() {

			@Override
			public void run() {

				// 实例化Adapter
				myBaseAdapter = new MyBaseAdapter(RequestShowFragment.view
						.getContext(), itemBeanList);

				// System.out.println("--------------->requestShowFrag"+itemBeanList.toString());
				// 将ListView绑定数据适配器
				RequestShowFragment.RSF_ListView.setAdapter(myBaseAdapter);

				RequestShowFragment.RSF_ListView
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO
								/**
								 * 
								 * 获取被点击的数据项的所有信息
								 * 
								 * 根据获取的信息设置RequestDetailShow.class的控件值
								 * 
								 */

								// 启动RequestDetailShow.class 查看详细信息
								mIntent = new Intent(view.getContext(),
										RequestDetailShow.class);

								bundle = new Bundle();

								// 将设置详细信息控件的参数传入
								bundle.putString(
										"orderId",
										itemBeanList.get(position - 1).itemOrederId);

								bundle.putString(
										"providerName",
										itemBeanList.get(position - 1).itemProviderName);

								bundle.putString(
										"nickName",
										itemBeanList.get(position - 1).itemNickName);

								bundle.putString(
										"reward",
										itemBeanList.get(position - 1).itemReward);

								bundle.putString(
										"addForm",
										itemBeanList.get(position - 1).itemAddForm);

								bundle.putString(
										"addTo",
										itemBeanList.get(position - 1).itemAddTo);

								bundle.putString(
										"timeLimit",
										itemBeanList.get(position - 1).itemTimeLimit);

								bundle.putString("size",
										itemBeanList.get(position - 1).itemSize);

								bundle.putString(
										"phone",
										itemBeanList.get(position - 1).itemPhone);

								bundle.putString("kinds",
										itemBeanList.get(position - 1).itemKind);

								bundle.putString(
										"remarks",
										itemBeanList.get(position - 1).itemRemarks);

								mIntent.putExtra("RequestItemClick", bundle);

								RequestShowFragment.view.getContext()
										.startActivity(mIntent);

							}
						});
			}
		});
	}

}
