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

public class RegiesterHandle extends Thread {

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

	public static String Regiester_result;// �������ݿ���������Ϣ

	private String regiesterUserName;
	private String regiesterPassWord;
	private String regiesterPhone;
	private String regiesterEmail;

	private String regiesterConnectUrl;

	// ʵ��POST����Ĳ���

	HttpPost mHttpPost;
	HttpClient mHttpClient;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;

	public RegiesterHandle(String userName, String passWord, String phone,
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
		// ����post����
		mHttpPost = new HttpPost(regiesterConnectUrl);

		// ����NameValuePair[]���д洢Post���������name,value��

		params.add(new BasicNameValuePair("userName", regiesterUserName));
		params.add(new BasicNameValuePair("passWord", regiesterPassWord));
		params.add(new BasicNameValuePair("phone", regiesterPhone));
		params.add(new BasicNameValuePair("email", regiesterEmail));

		// ����HTTP����

		try {

			mHttpPost.setEntity(new UrlEncodedFormEntity(params));

			// ȡ��HTTP response

			mHttpResponse = mHttpClient.execute(mHttpPost);

			if (mHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// ��ʱ�����û����ݲ����ɹ�

				mHttpEntity = mHttpResponse.getEntity();

				// ������� �û����ظ� ���������� ��1
				// ������� ���������� ���������� ��2
				// ������� �ɹ�ע�� ���������� ��3
				// String����

				Regiester_result = mHttpEntity.toString();

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