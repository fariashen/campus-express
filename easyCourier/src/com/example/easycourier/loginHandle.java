package com.example.easycourier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

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

public class LoginHandle extends Thread {

	public static String result = "";

	public static String loginUserName;
	public static String loginConnectUrl;

	public LoginHandle(String loginUserName, String loginConnectUrl) {
		// TODO Auto-generated constructor stub
		this.loginUserName = loginUserName;
		this.loginConnectUrl = loginConnectUrl;
	}

	@Override
	public void run() {

		gotoLogin();
		Log.d("LoginHandle", result);
	}

	private void gotoLogin() {

		HttpClient mClient = new DefaultHttpClient();
		// 发送Post请求
		HttpPost httpPost = new HttpPost(loginConnectUrl);

		// 构建NameValuePair[]阵列存储Post参数

		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("un", loginUserName));

		// 发送http请求
		// 取得httpresponse
		// 检测是否请求成功

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse httpResponse = mClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// 取出密码字符串
				result = EntityUtils.toString(httpResponse.getEntity());

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
