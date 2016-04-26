package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.easycourier.ItemBean;
import com.example.easycourier.MyBaseAdapter;

import easyCourierFunction.Login;
import easyCourierFunction.ShowRequestAllInfo;
import easyCourierFunction.ShowUpLoadedRequest;

public class SULRHttpPost extends HttpPostRequest {

	// �����ѽ��͵�json����
	public static List<ItemBean> itemBeanList = new ArrayList<ItemBean>();

	// Ҫ�������ݵ�������
	private ItemBean mItemBean;
	private String itemOrederId;
	private String itemProviderName;
	private String courierName;
	private String itemNickName;
	private String itemReward;
	private String itemAddForm;
	private String itemAddTo;
	private String itemTimeLimit;
	private String itemSize;
	private String itemPhone;
	private String itemKind;
	private String itemRemarks;
	private String ordflag;

	// ����������
	private MyBaseAdapter myBaseAdapter;

	// ����Activity�ĳ�Ա����
	private Intent mIntent;

	// ���û������Item��������Ϣֵд��Bundle
	// ������RequestDetailShow.class��ʱ�򣬽�Bundle����ȥ
	private Bundle bundle;

	@Override
	protected void setUrlAddress() {

		// TODO
		urlAddress = "http://119.29.4.159/phpserver/myorderpro.php";
	}

	@Override
	protected void setParamsIntoPost() {

		// TODO �������û���
		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));

		try {
			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void setIsJsonData() {

		isJsonData = true;
		itemBeanList.clear();
	}

	@Override
	protected void getDifferentJsonArrayValue() {

		try {
			itemOrederId = mJsonObject.getString("orderID");
			itemProviderName = mJsonObject.getString("providerName");
			courierName = mJsonObject.getString("courierName");
			itemNickName = mJsonObject.getString("nickName");
			itemReward = mJsonObject.getString("reward");
			itemAddForm = mJsonObject.getString("addFrom");
			itemAddTo = mJsonObject.getString("addTo");
			itemTimeLimit = mJsonObject.getString("timeLimit");
			itemSize = mJsonObject.getString("size");
			itemPhone = mJsonObject.getString("phone");
			itemKind = mJsonObject.getString("kinds");
			itemRemarks = mJsonObject.getString("remarks");
			ordflag = mJsonObject.getString("ordflag");

			mItemBean = new ItemBean(itemOrederId, itemProviderName,
					courierName, itemNickName, itemReward, itemAddForm,
					itemAddTo, itemTimeLimit, itemSize, itemPhone, itemKind,
					itemRemarks, ordflag);

			itemBeanList.add(mItemBean);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void backToMainThreadHandleMessage() {

		// �����̷߳��͸���UI����
		ShowUpLoadedRequest.SULR_Handler.post(new Runnable() {

			@Override
			public void run() {
				// ʵ����Adapter
				myBaseAdapter = new MyBaseAdapter(ShowUpLoadedRequest.mContext,
						itemBeanList);

				// ��ListView������������
				ShowUpLoadedRequest.lv_ShowUpLoaded.setAdapter(myBaseAdapter);

				ShowUpLoadedRequest.lv_ShowUpLoaded
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
								mIntent = new Intent(
										ShowUpLoadedRequest.mContext,
										ShowRequestAllInfo.class);

								bundle = new Bundle();

								// ��������ϸ��Ϣ�ؼ��Ĳ�������
								bundle.putString("orderId",
										itemBeanList.get(position).itemOrederId);

								bundle.putString(
										"providerName",
										itemBeanList.get(position).itemProviderName);

								bundle.putString("courierName",
										itemBeanList.get(position).courierName);

								bundle.putString("nickName",
										itemBeanList.get(position).itemNickName);

								bundle.putString("reward",
										itemBeanList.get(position).itemReward);

								bundle.putString("addForm",
										itemBeanList.get(position).itemAddForm);

								bundle.putString("addTo",
										itemBeanList.get(position).itemAddTo);

								bundle.putString(
										"timeLimit",
										itemBeanList.get(position).itemTimeLimit);

								bundle.putString("size",
										itemBeanList.get(position).itemSize);

								bundle.putString("phone",
										itemBeanList.get(position).itemPhone);

								bundle.putString("kinds",
										itemBeanList.get(position).itemKind);

								bundle.putString("remarks",
										itemBeanList.get(position).itemRemarks);

								bundle.putString("ordflag",
										itemBeanList.get(position).ordflag);
								
							    bundle.putBoolean("isCheckUpLoaded", true);
							    bundle.putBoolean("isCheckAccepted", false);

								mIntent.putExtra("ItemAllInfo", bundle);

								ShowUpLoadedRequest.mContext
										.startActivity(mIntent);

							}
						});

			}
		});
	}

}