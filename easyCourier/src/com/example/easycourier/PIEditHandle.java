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
import org.apache.http.message.BasicNameValuePair;

/**
 * @author vacation
 *
 *���ݴ���Ĳ������û��������룬�绰������
 *�����ݿ����ͨ�ţ��޸ĸ�����Ϣ
 *
 */
public class PIEditHandle extends Thread {

//	����POST����Ĳ���
	HttpClient mHttpClient;
	HttpPost mHttpPost;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;
	
//	��Ҫ���ݵĲ���
	String UserName;
	String PassWord;
	String Phone;
	String Email;
	
	public static String PIEdit_result;
	
	public PIEditHandle(String UserName,String PassWord,String Phone,String Email) {

		this.UserName = UserName;
		this.PassWord = PassWord;
		this.Phone = Phone;
		this.Email = Email;
	}
	
	@Override
	public void run() {
		
		editPersonalInfo();
		super.run();
	}

	private void editPersonalInfo() {

//		���ò���(name,value)
		params.add(new BasicNameValuePair("UserName", UserName));
		params.add(new BasicNameValuePair("PassWord", PassWord));
		params.add(new BasicNameValuePair("Phone", Phone));
		params.add(new BasicNameValuePair("Email", Email));
		
//		����HTTPPOST
		mHttpPost = new HttpPost(PersonalInfoEdit.PIEDIT_CONNECTURL);
		
		try {
			
//			���ò���
			
			mHttpPost.setEntity(new UrlEncodedFormEntity(params));
			
//			��������
			mHttpResponse = mHttpClient.execute(mHttpPost);
			
//			�ж������Ƿ��ͳɹ�
			if(mHttpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){

//				��ȡ��������������
				mHttpEntity = mHttpResponse.getEntity();
				
//				����޸ĳɹ��򷵻� "success" �ַ���
				PIEdit_result = mHttpEntity.toString();
				
				
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