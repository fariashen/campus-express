package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import easyCourierFunction.Regiester;

/**
 * 
 * 在此类里实现注册的相关数据库操作
 * 
 * 传入要插入的参数‘用户名’，‘密码’，‘电话’，‘邮箱’以及连接的数据库‘地址’
 * 
 * 通过在run()方法内调用相应的方法实现数据库操作
 * 
 * 返回值为 true || false ，返回值存储在 result 内，由 Regiester.java 进行调用
 * 
 */

public class RegiesterHttpPost extends HttpPostRequest {

	private String regiesterUserName;
	private String regiesterPassWord;

	public RegiesterHttpPost(String userName, String passWord) {

		this.regiesterUserName = userName;
		this.regiesterPassWord = passWord;

	}

	@Override
	protected void setUrlAddress() {

		// 设置地址
		urlAddress = UrlFactory.getUrl("Regiester");
	}

	@Override
	protected void setParamsIntoPost() {

		// 设置参数
		params.add(new BasicNameValuePair("userName", regiesterUserName));
		params.add(new BasicNameValuePair("passWord", regiesterPassWord));
		params.add(new BasicNameValuePair("phone", "111"));
		params.add(new BasicNameValuePair("email", "111"));
		params.add(new BasicNameValuePair("address", "111"));
		params.add(new BasicNameValuePair("stunum", "111"));
		params.add(new BasicNameValuePair("sex", "111"));
		
		try {

			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void backToMainThreadHandleMessage() {

		// 如果出现 服务器错误 服务器返回 ：1
		// 如果出现 用户名重复 服务器返回 ：2
		// 如果出现 成功注册 服务器返回 ：3

		Regiester.regiesterHandler.sendMessage(message);
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
