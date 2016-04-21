package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import easyCourierFunction.Login;
import easyCourierFunction.PersonalInfoShow;

/**
 * @author vacation
 * 
 *         ʵ��PersonalInfoShow����Զ�����ݿ�Ĳ�ѯ����
 * 
 *         ����ѯ����洢�� String �ַ���result_Password�� ͨ�����ַ�������TextView�ؼ�
 * 
 */
public class PIShowHttpPost extends HttpPostRequest {

	private String PIS_Password = "";
	private String PIS_Phone = "";
	private String PIS_Address = "";
	private String PIS_Sex = "";
	private String PIS_Balances = "";

	@Override
	protected void setUrlAddress() {

		// ����URL
		urlAddress = PersonalInfoShow.PISHOW_CONNECTURL;
	}

	@Override
	protected void setParamsIntoPost() {

		// ����params��HttpPost�������
		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));
		try {
			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void backToMainThreadHandleMessage() {
		// �����̷߳��͸���UI����

		PersonalInfoShow.pisHandler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				PersonalInfoShow.tv_PIS_UserName.setText(Login.LOGIN_USERNAME);
				PersonalInfoShow.tv_PIS_PassWord.setText(PIS_Password);
				PersonalInfoShow.tv_PIS_Phone.setText(PIS_Phone);
				PersonalInfoShow.tv_PIS_Address.setText(PIS_Address);
				PersonalInfoShow.tv_PIS_Sex.setText(PIS_Sex);
				PersonalInfoShow.tv_PIS_Balances.setText(PIS_Balances);

			}
		});

	}

	@Override
	protected void setIsJsonData() {

		isJsonData = true;
		// jsonString = "{\"data\":[";
	}

	@Override
	protected void getDifferentJsonArrayValue() {

		try {

			PIS_Password = mJsonObject.getString("passWord");
			PIS_Phone = mJsonObject.getString("phone");
			PIS_Address = mJsonObject.getString("address");
			PIS_Sex = mJsonObject.getString("sex");
			PIS_Balances = mJsonObject.getString("balances");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
