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

import android.util.Log;

/**
 * 
 * @author vacation
 * 
 *         �ڴ�����ʵ�ֵ�¼��������ݿ����
 * 
 *         ����Ҫ��Ĳ������û����������ӵ����ݿ⡮��ַ��
 * 
 *         ͨ����run()�����ڵ�����Ӧ�ķ���ʵ�����ݿ����
 * 
 *         ��������ݿ��ڲ�ѯ����Ӧ�û����򷵻ظ��û������룬�������� result ��, �������ݿ��в�ѯ������Ӧ�û����򷵻ؿ��ַ�����
 * 
 * 
 */

public class LoginHandle extends Thread {

	public static String Login_result;

	public static String loginUserName;
	public static String loginConnectUrl;

	// ʵ��POST����Ĳ���

	HttpPost mHttpPost;
	HttpClient mHttpClient;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;

	public LoginHandle(String loginUserName, String loginConnectUrl) {
		// TODO Auto-generated constructor stub
		this.loginUserName = loginUserName;
		this.loginConnectUrl = loginConnectUrl;
	}

	@Override
	public void run() {

		gotoLogin();
	}

	private void gotoLogin() {

		// ����Post����
		mHttpPost = new HttpPost(loginConnectUrl);

		// ����NameValuePair[]���д洢Post����

		params.add(new BasicNameValuePair("UserName", loginUserName));

		// ����http����
		// ȡ��httpresponse
		// ����Ƿ�����ɹ�

		try {

			mHttpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse httpResponse = mHttpClient.execute(mHttpPost);

			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// ������� ��¼�ɹ� ���������أ�1
				// ������� ������� ���������أ�2
				// ������� �޸��û� ���������أ�3
				
				mHttpEntity = mHttpResponse.getEntity();

				Login_result = mHttpEntity.toString();
				System.out.println("------------>LoginHandle"+Login_result);
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