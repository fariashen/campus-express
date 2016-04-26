package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import easyCourierFunction.Login;
import easyCourierFunction.PersonalInfoEdit;

/**
 * @author vacation
 * 
 *         根据传入的参数：用户名，密码，电话，邮箱 与数据库进行通信，修改个人信息
 * 
 */
public class PIEditHttpPost extends HttpPostRequest {

	private String Password;
	private String Phone;
	private String Address;
	private String Sex;

	public PIEditHttpPost(String PassWord, String Phone, String Address,
			String Sex) {

		this.Password = PassWord;
		this.Phone = Phone;
		this.Address = Address;
		this.Sex = Sex;

	}

	@Override
	protected void setUrlAddress() {
		// 设置地址
		urlAddress = PersonalInfoEdit.PIEDIT_CONNECTURL;

	}

	@Override
	protected void setParamsIntoPost() {

		// 设置参数
		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));
		params.add(new BasicNameValuePair("passWord", Password));
		params.add(new BasicNameValuePair("phone", Phone));
		params.add(new BasicNameValuePair("address", Address));
		params.add(new BasicNameValuePair("sex", Sex));

		try {
			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void backToMainThreadHandleMessage() {

		// 设置相应的handler
		PersonalInfoEdit.PIE_Handler.sendMessage(message);

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
