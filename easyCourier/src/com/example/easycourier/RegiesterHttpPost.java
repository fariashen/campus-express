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
	 * �ڴ�����ʵ��ע���������ݿ����
	 * 
	 * ����Ҫ����Ĳ������û������������롯�����绰���������䡯�Լ����ӵ����ݿ⡮��ַ��
	 * 
	 * ͨ����run()�����ڵ�����Ӧ�ķ���ʵ�����ݿ����
	 * 
	 * ����ֵΪ true || false ������ֵ�洢�� result �ڣ��� Regiester.java ���е���
	 * 
	 */

	String Regiester_result = "";// �������ݿ���������Ϣ

	private String regiesterUserName;
	private String regiesterPassWord;

	private String regiesterConnectUrl;

	// ʵ��POST����Ĳ���

	HttpPost mHttpPost;
	HttpClient mHttpClient;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;

	public RegiesterHttpPost(String userName, String passWord, String connectUrl) {
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

		mHttpClient = new DefaultHttpClient();
		// ����post����
		mHttpPost = new HttpPost(regiesterConnectUrl);

		// ����NameValuePair[]���д洢Post���������name,value��

		params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("userName", regiesterUserName));
		params.add(new BasicNameValuePair("passWord", regiesterPassWord));

		// ����HTTP����

		try {

			mHttpPost.setEntity(new UrlEncodedFormEntity(params));

			// ȡ��HTTP response

			System.out.println("------------------->RegiesterHttpPost"+params.toString());
			mHttpResponse = mHttpClient.execute(mHttpPost);

			if (mHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// ��ʱ�����û����ݲ����ɹ�

				// ������� ���������� ���������� ��1
				// ������� �û����ظ� ���������� ��2
				// ������� �ɹ�ע�� ���������� ��3
				// String����

				Regiester_result = EntityUtils.toString(mHttpResponse
						.getEntity());

				// ��������ظ� Login�߳̽��д���
				System.out
						.println("---------------------->" + Regiester_result);

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