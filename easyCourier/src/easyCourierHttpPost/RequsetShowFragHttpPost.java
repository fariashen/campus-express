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
 *         ��Զ�����ݿⷢ�Ͳ�ѯ�Ĳ�������
 * 
 *         ��ȡJson��ʽ������
 * 
 *         ����Json����
 * 
 *         �����ͺ�����ݷ�װ��List<ItemBean> ��
 * 
 *         �����̷߳��͸���UI������
 * 
 *         1������ȡ�����ݰ�������
 * 
 *         2������ListView��Item����¼�
 * 
 * 
 */
public class RequsetShowFragHttpPost extends HttpPostRequest {

	// �����ѽ��͵�json����
	public static List<ItemBean> itemBeanList = new ArrayList<ItemBean>();

	// Ҫ������ݵ�������
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
	
	// ����������
	private MyBaseAdapter myBaseAdapter;

	// ����Activity�ĳ�Ա����
	private Intent mIntent;

	// ���û������Item��������Ϣֵд��Bundle
	// ������RequestDetailShow.class��ʱ�򣬽�Bundle����ȥ
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

		// ����Ԫ��������ItemBean�г�Ա������ֵ

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

		// �����̷߳��͸���UI������
		RequestShowFragment.RSF_Handler.post(new Runnable() {

			@Override
			public void run() {

				// ʵ����Adapter
				myBaseAdapter = new MyBaseAdapter(RequestShowFragment.view
						.getContext(), itemBeanList);

				// System.out.println("--------------->requestShowFrag"+itemBeanList.toString());
				// ��ListView������������
				RequestShowFragment.RSF_ListView.setAdapter(myBaseAdapter);

				RequestShowFragment.RSF_ListView
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO
								/**
								 * 
								 * ��ȡ��������������������Ϣ
								 * 
								 * ���ݻ�ȡ����Ϣ����RequestDetailShow.class�Ŀؼ�ֵ
								 * 
								 */

								// ����RequestDetailShow.class �鿴��ϸ��Ϣ
								mIntent = new Intent(view.getContext(),
										RequestDetailShow.class);

								bundle = new Bundle();

								// ��������ϸ��Ϣ�ؼ��Ĳ�������
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
