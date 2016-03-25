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
public class PIShowHandle extends Thread {

	/*
	 * 参数
	 * 
	 * 访问远程服务器的地址
	 * 
	 * 查询的用户名
	 * 
	 * 返回
	 * String 密码
	 * String 电话
	 * String 邮箱
	 * 
	 */
	public static String result_Password;
	public static String result_Phone;
	public static String result_Email;

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
		super.run();
	}

	private void getPersonalInfo() {

		// 添加传递的参数

		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));

		// 新建POST请求

		mPost = new HttpPost(PersonalInfoShow.PISHOW_CONNECTURL);

		// 设置参数

		try {

			mPost.setEntity(new UrlEncodedFormEntity(params));

			// 发送请求
			mResponse = mClient.execute(mPost);

			if (mResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// 如果发送请求成功

				// 获取服务器返回的实体
				mEntity = mResponse.getEntity();

				// 将数据存储在InputStream内

				mInputStream = mEntity.getContent();
				mBufferedReader = new BufferedReader(new InputStreamReader(
						mInputStream, "UTF-8"));
				
				String line = null;
				while((line = mBufferedReader.readLine())!=null){
					
					mStringBuilder.append(line);
					
				}
				mInputStream.close();
				
				//解析JSON
				
				mJsonArray = new JSONArray(mStringBuilder.toString());
				
				for (int i = 0; i < mJsonArray.length(); i++) {
					mJsonObject = mJsonArray.getJSONObject(i);
					result_Password = mJsonObject.getString("Password");
					result_Phone = mJsonObject.getString("Phone");
					result_Email = mJsonObject.getString("Email");
				}
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
