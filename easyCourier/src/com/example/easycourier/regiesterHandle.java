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

public class RegiesterHandle implements Runnable {

	/**
	 * 
	 * �ڴ�����ʵ��ע���������ݿ����
	 * 
	 * ����Ҫ����Ĳ������û������������롯�����绰���������䡯�Լ����ӵ����ݿ⡮��ַ��
	 * 
	 * ͨ����run()�����ڵ�����Ӧ�ķ���ʵ�����ݿ����
	 * 
	 * ����ֵΪ true || false ������ֵ�洢�� result �ڣ��� Regiester.java ���е���
	 * 
	 */

	public static boolean result = false;// �������ݿ���������Ϣ

	private String regiesterUserName;
	private String regiesterPassWord;
	private String regiesterPhone;
	private String regiesterEmail;

	private String regiesterConnectUrl;

	public RegiesterHandle(String userName, 
			String passWord, 
			String phone,
			String email,
			String connectUrl) {
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

		// ����post����
		HttpPost httpRequest = new HttpPost(regiesterConnectUrl);

		// ����NameValuePair[]���д洢Post�������

		List params = new ArrayList();
		params.add(new BasicNameValuePair("userName", regiesterUserName));
		params.add(new BasicNameValuePair("passWord", regiesterPassWord));
		params.add(new BasicNameValuePair("phone", regiesterPhone));
		params.add(new BasicNameValuePair("email", regiesterEmail));

		HttpResponse httpResponse;

		// ����HTTP����

		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

			// ȡ��HTTP response

			httpResponse = new DefaultHttpClient().execute(httpRequest);

			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// ��ʱ�����û����ݲ����ɹ�

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