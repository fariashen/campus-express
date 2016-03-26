package com.example.easycourier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
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

import android.os.Message;

public class RegiesterHttpPost extends Thread {

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

	String Regiester_result = "";// 返回数据库操作结果信息

	private String regiesterUserName;
	private String regiesterPassWord;
	private String regiesterPhone;
	private String regiesterEmail;

	private String regiesterConnectUrl;

	// 实现POST请求的参数

	HttpPost mHttpPost;
	HttpClient mHttpClient;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;

	public RegiesterHttpPost(String userName, String passWord, String phone,
			String email, String connectUrl) {
		// TODO Auto-generated constructor stub
		this.regiesterUserName = userName;
		this.regiesterPassWord = passWord;
		this.regiesterPhone = phone;
		this.regiesterEmail = email;

		this.regiesterConnectUrl = connectUrl;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		gotoLogin();
	}

	private void gotoLogin() {
		// TODO Auto-generated method stub

		mHttpClient = new DefaultHttpClient();
		// 发送post请求
		mHttpPost = new HttpPost(regiesterConnectUrl);

		// 构建NameValuePair[]阵列存储Post请求变量（name,value）
		
		params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("userName", regiesterUserName));
		params.add(new BasicNameValuePair("passWord", regiesterPassWord));
		params.add(new BasicNameValuePair("phone", regiesterPhone));
		params.add(new BasicNameValuePair("email", regiesterEmail));

		// 发送HTTP请求

		try {

			mHttpPost.setEntity(new UrlEncodedFormEntity(params));

			// 取得HTTP response

			mHttpResponse = mHttpClient.execute(mHttpPost);

			if (mHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 此时插入用户数据操作成功


				// 如果出现 服务器错误 服务器返回 ：1
				// 如果出现 用户名重复 服务器返回 ：2
				// 如果出现 成功注册 服务器返回 ：3
				// String类型

				Regiester_result = EntityUtils.toString(mHttpResponse.getEntity());
				
				//将结果返回给 Login线程进行处理
				System.out.println("---------------------->"+Regiester_result);
				
				Message rhpMessage = new Message();
				rhpMessage.what = Integer.parseInt(Regiester_result);
				Regiester.regiesterHandler.sendMessage(rhpMessage);

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
