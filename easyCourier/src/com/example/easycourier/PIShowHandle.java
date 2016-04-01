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
 *         ʵ��PersonalInfoShow����Զ�����ݿ�Ĳ�ѯ����
 * 
 *         ����ѯ����洢�� String �����ڣ����ظ�����
 * 
 */
public class PIShowHandle extends Thread {

	/*
	 * ����
	 * 
	 * ����Զ�̷������ĵ�ַ
	 * 
	 * ��ѯ���û���
	 * 
	 * ����
	 * String ����
	 * String �绰
	 * String ����
	 * 
	 */
	public static String result_Password;
	public static String result_Phone;
	public static String result_Email;

	// ������������Ĳ���

	HttpClient mClient;
	HttpPost mPost;
	ArrayList<NameValuePair> params;
	HttpResponse mResponse;
	HttpEntity mEntity;

	// ����JSON�Ĳ���

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

		// ���Ӵ��ݵĲ���

		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));

		// �½�POST����

		mPost = new HttpPost(PersonalInfoShow.PISHOW_CONNECTURL);

		// ���ò���

		try {

			mPost.setEntity(new UrlEncodedFormEntity(params));

			// ��������
			mResponse = mClient.execute(mPost);

			if (mResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// �����������ɹ�

				// ��ȡ���������ص�ʵ��
				mEntity = mResponse.getEntity();

				// �����ݴ洢��InputStream��

				mInputStream = mEntity.getContent();
				mBufferedReader = new BufferedReader(new InputStreamReader(
						mInputStream, "UTF-8"));
				
				String line = null;
				while((line = mBufferedReader.readLine())!=null){
					
					mStringBuilder.append(line);
					
				}
				mInputStream.close();
				
				//����JSON
				
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