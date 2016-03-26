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
 *         ʵ��PersonalInfoShow����Զ�����ݿ�Ĳ�ѯ����
 * 
 *         ����ѯ����洢�� String �����ڣ����ظ�����
 * 
 */
public class PIShowHttpPost extends Thread {

	/*
	 * ����
	 * 
	 * ����Զ�̷������ĵ�ַ
	 * 
	 * ��ѯ���û���
	 * 
	 * ���� String ���� String �绰 String ����
	 */
	String result_Password;
	String result_Phone;
	String result_Email;

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

		// �����̷߳��͸���UI����

		PersonalInfoShow.pisHandler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				PersonalInfoShow.tv_PersonalInfoUserName
						.setText(Login.LOGIN_USERNAME);
				PersonalInfoShow.tv_PersonalInfoPassWord
						.setText(result_Password);
				PersonalInfoShow.tv_PersonalInfoPhone.setText(result_Phone);
				PersonalInfoShow.tv_PersonalInfoEmail.setText(result_Email);
			}
		});
	}

	private void getPersonalInfo() {

		// ���Ӵ��ݵĲ���
		params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));

		// �½�POST����

		mPost = new HttpPost(PersonalInfoShow.PISHOW_CONNECTURL);

		// ���ò���

		try {

			mPost.setEntity(new UrlEncodedFormEntity(params));

			// ��������
			mClient = new DefaultHttpClient();
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
				while ((line = mBufferedReader.readLine()) != null) {

					mStringBuilder.append(line);

				}
				System.out.println("------------>"+mInputStream.toString());
				mInputStream.close();

				// ����JSON

				mJsonArray = new JSONArray(mStringBuilder.toString());

				for (int i = 0; i < mJsonArray.length(); i++) {
					mJsonObject = mJsonArray.getJSONObject(i);
					result_Password = mJsonObject.getString("Password");
					result_Phone = mJsonObject.getString("Phone");
					result_Email = mJsonObject.getString("Email");
					
					System.out.println("----------->"+result_Password);
					System.out.println("----------->"+result_Phone);
					System.out.println("----------->"+result_Email);
					
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