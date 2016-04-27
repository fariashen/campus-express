package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import easyCourierFunction.Login;

/**
 * 
 * @author vacation
 * 
 *         �ڴ�����ʵ�ֵ�¼��������ݿ����
 * 
 *         ����Ҫ��Ĳ������û����������ӵ����ݿ⡮��ַ��
 * 
 *         ͨ����run()�����ڵ�����Ӧ�ķ���ʵ�����ݿ����
 * 
 *         ��������ݿ��ڲ�ѯ����Ӧ�û����򷵻ظ��û������룬�������� result ��, �������ݿ��в�ѯ������Ӧ�û����򷵻ؿ��ַ�����
 * 
 * 
 */

public class LoginHttpPost extends HttpPostRequest {

	@Override
	protected void setUrlAddress() {
		urlAddress =UrlFactory.getUrl("Login");
	}

	@Override
	protected void setParamsIntoPost() {

		params.add(new BasicNameValuePair("UserName", Login.LOGIN_USERNAME));
		params.add(new BasicNameValuePair("PassWord", Login.LOGIN_PASSWORD));

		try {

			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void backToMainThreadHandleMessage() {

		// ������� ��¼�ɹ� ���������أ�1
		// ������� ������� ���������أ�2
		// ������� �޸��û� ���������أ�3

		Login.loginHandler.sendMessage(message);

	}

	@Override
	protected void setIsJsonData() {

		isJsonData = false;
	}

	@Override
	protected void getDifferentJsonArrayValue() {
		// TODO Auto-generated method stub

	}

}
