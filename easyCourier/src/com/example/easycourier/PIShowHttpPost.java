package com.example.easycourier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author vacation
 * 
 *         实现PersonalInfoShow访问远程数据库的查询操作
 * 
 *         将查询结果存储在 String 数组内，返回该数组
 * 
 */
public class PIShowHttpPost extends Thread {

	/*
	 * 参数
	 * 
	 * 访问远程服务器的地址
	 * 
	 * 查询的用户名
	 * 
	 * 返回 String 密码
	 */
	String result_Password = "";

	// 发送网络请求的参数

	HttpClient mClient;
	HttpPost mPost;
	ArrayList<NameValuePair> params;
	HttpResponse mResponse;
	HttpEntity mEntity;

	// 解析JSON的参数

	JSONArray mJsonArray;
	InputStream mInputStream;
	BufferedReader mBufferedReader;
	StringBuilder mStringBuilder;
	JSONObject mJsonObject;

	@Override
	public void run() {

		getPersonalInfo();

		// 向主线程发送更新UI请求

		PersonalInfoShow.pisHandler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				PersonalInfoShow.tv_PersonalInfoUserName
						.setText(Login.LOGIN_USERNAME);
				PersonalInfoShow.tv_PersonalInfoPassWord
						.setText(result_Password);
			}
		});
	}

	private void getPersonalInfo() {

		// 添加传递的参数
		params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));

		// 新建POST请求

		mPost = new HttpPost(PersonalInfoShow.PISHOW_CONNECTURL);

		// 设置参数

		try {

			mPost.setEntity(new UrlEncodedFormEntity(params));

			// 发送请求
			mClient = new DefaultHttpClient();
			mResponse = mClient.execute(mPost);

			if (mResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// 如果发送请求成功

				// 获取服务器返回的实体
				mEntity = mResponse.getEntity();

				// 将数据存储在InputStream内

				mInputStream = mEntity.getContent();
				mBufferedReader = new BufferedReader(new InputStreamReader(
						mInputStream, "UTF-8"));
				
				mStringBuilder = new StringBuilder();
				String line = null;
				while ((line = mBufferedReader.readLine()) != null) {

					mStringBuilder.append(line);

				}
				System.out.println("------------>"+mStringBuilder.toString());
				mInputStream.close();

				// 解析JSON

//				mJsonArray = new JSONArray(mStringBuilder.toString());

					mJsonObject = new JSONObject(mStringBuilder.toString());
					result_Password = mJsonObject.getString("passWord");
					
					System.out.println("----------->JSON"+result_Password);
					
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
