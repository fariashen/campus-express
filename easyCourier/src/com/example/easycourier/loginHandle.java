package com.example.easycourier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author vacation
 * 
	 * 在此类里实现登录的相关数据库操作
	 * 
	 * 传入要查的参数‘用户名’，连接的数据库‘地址’
	 * 
	 * 通过在run()方法内调用相应的方法实现数据库操作
	 * 
	 * 如果在数据库内查询到相应用户，则返回该用户的密码，并储存在 result 中,
	 * 若在数据库中查询不到相应用户，则返回空字符串；
	 * 
	 * 
 */
public class LoginHandle implements Runnable {

	public static String result = "";
	
	public String loginUserName;
	public String loginConnectUrl;
	
	public LoginHandle(String loginUserName,String loginConnectUrl) {
		// TODO Auto-generated constructor stub
		this.loginUserName = loginUserName;
		this.loginConnectUrl = loginConnectUrl;
	}
	
	@Override
	public void run() {

		gotoLogin();
		
	}
	private void gotoLogin() {

		//发送Post请求
		HttpPost httpRequest = new HttpPost(loginConnectUrl);
		
		//构建NameValuePair[]阵列存储Post参数
		
		List params = new ArrayList();
		params.add(new BasicNameValuePair("userName",loginUserName));
		
		//发送http请求
		//取得httpresponse
		//检测是否请求成功
		
		try {
			
			httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				
				//取出密码字符串
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
