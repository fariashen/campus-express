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
 *         在此类里实现登录的相关数据库操作
 * 
 *         传入要查的参数‘用户名’，连接的数据库‘地址’
 * 
 *         通过在run()方法内调用相应的方法实现数据库操作
 * 
 *         如果在数据库内查询到相应用户，则返回该用户的密码，并储存在 result 中, 若在数据库中查询不到相应用户，则返回空字符串；
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

		// 如果出现 登录成功 服务器返回：1
		// 如果出现 密码错误 服务器返回：2
		// 如果出现 无该用户 服务器返回：3

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
