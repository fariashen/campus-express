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

/**
 * @author vacation
 *
 *根据传入的参数：用户名，密码，电话，邮箱
 *与数据库进行通信，修改个人信息
 *
 */
public class PIEditHttpPost extends Thread {

//	进行POST请求的参数
	HttpClient mHttpClient;
	HttpPost mHttpPost;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;
	
//	需要传递的参数
	String PassWord;
	
	public static String PIEdit_result;
	
	public PIEditHttpPost(String PassWord) {

		this.PassWord = PassWord;
	}
	
	@Override
	public void run() {
		
		editPersonalInfo();
	}

	private void editPersonalInfo() {

//		设置参数(name,value)
		
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));
		params.add(new BasicNameValuePair("passWord", PassWord));
		params.add(new BasicNameValuePair("phone", "111"));
		params.add(new BasicNameValuePair("email", "111"));
		params.add(new BasicNameValuePair("address", "111"));
		params.add(new BasicNameValuePair("stunum", "111"));
		params.add(new BasicNameValuePair("sex", "111"));
		
//		设置HTTPPOST
		mHttpPost = new HttpPost(PersonalInfoEdit.PIEDIT_CONNECTURL);
		
		try {
			
//			设置参数
			
			mHttpPost.setEntity(new UrlEncodedFormEntity(params));
			System.out.println("-------------->"+params.toString());
//			发送请求
			mHttpClient = new DefaultHttpClient();
			mHttpResponse = mHttpClient.execute(mHttpPost);
			System.out.println("-------------->");
			
//			判断请求是否发送成功
			if(mHttpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){

//				获取服务器返回数据
//				如果修改成功则返回 "success" 字符串
				PIEdit_result = EntityUtils.toString(mHttpResponse.getEntity());
				System.out.println("-------------->"+PIEdit_result);
//				将服务器返回的结果，传给PersonalInfoEdit处理
				Message pieHttpPostMessage = new Message();
				
				if(PIEdit_result.equals("success")){
					pieHttpPostMessage.what = 1;
				}else{
					pieHttpPostMessage.what = 0;
				}
				PersonalInfoEdit.PIE_Handler.sendMessage(pieHttpPostMessage);
				
				
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
