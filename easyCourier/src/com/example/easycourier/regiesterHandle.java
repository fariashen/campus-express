package com.example.easycourier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.io.HttpResponseParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.net.http.HttpResponseCache;

public class regiesterHandle implements Runnable {

	/**
	 * 
	 * 在此类里实现注册的相关数据库操作
	 * 
	 * 传入要插入的参数‘用户名’，‘密码’，以及连接的数据库‘地址’
	 * 
	 * 通过在run()方法内调用相应的方法实现数据库操作
	 * 
	 */
	
	public static boolean result=false;//返回数据库操作结果信息
	
	public String regiesterUserName;
	public String regiesterPassWord;
	
	public String regiesterConnectUrl;
	
	public regiesterHandle(String userName,String passWord,String connectUrl) {
		// TODO Auto-generated constructor stub
		this.regiesterUserName = userName;
		this.regiesterPassWord = passWord;
		this.regiesterConnectUrl = connectUrl;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gotoLogin();
	}

	private void gotoLogin() {
		// TODO Auto-generated method stub
		
		//发送post请求
		HttpPost httpRequest = new HttpPost(regiesterConnectUrl);
		
		//构建NameValuePair[]阵列存储Post请求变量
		
		List params = new ArrayList();
		params.add(new BasicNameValuePair("userName", regiesterUserName));
		params.add(new BasicNameValuePair("passWord", regiesterPassWord));
		
		HttpResponse httpResponse;
		
		//发送HTTP请求
		
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			
			//取得HTTP response
			
			httpResponse = new DefaultHttpClient().execute(httpRequest);
			
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				//此时插入用户数据操作成功
				
				result = true;
				
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
