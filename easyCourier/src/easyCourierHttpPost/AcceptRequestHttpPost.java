package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import easyCourierFunction.Login;
import easyCourierFunction.RequestDetailShow;

public class AcceptRequestHttpPost extends HttpPostRequest {

	@Override
	protected void setUrlAddress() {
		// TODO ���÷��������ַ
		urlAddress = "http://119.29.4.159/phpserver/ordaccept.php";

	}

	@Override
	protected void setParamsIntoPost() {

		// TODO���ô��͸����ݿ�Ĳ���
		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));
		params.add(new BasicNameValuePair("orderID", RequestDetailShow.itemOrderId));

		try {
			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void setIsJsonData() {

		isJsonData = false;
	}

	@Override
	protected void getDifferentJsonArrayValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void backToMainThreadHandleMessage() {

		// ����Ϣ�ش������߳��д���
		// �ӵ�ʧ��,�����ѱ��� �ش� 2
		// �ӵ��ɹ� �ش� 1
		// �ӵ�ʧ��,���������� �ش� 0
		RequestDetailShow.handler_RequestDetailShow.sendMessage(message);
	}

}