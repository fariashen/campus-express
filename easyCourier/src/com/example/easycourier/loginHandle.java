package com.example.easycourier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author vacation
 * 
	 * �ڴ�����ʵ�ֵ�¼��������ݿ����
	 * 
	 * ����Ҫ��Ĳ������û����������ӵ����ݿ⡮��ַ��
	 * 
	 * ͨ����run()�����ڵ�����Ӧ�ķ���ʵ�����ݿ����
	 * 
	 * ��������ݿ��ڲ�ѯ����Ӧ�û����򷵻ظ��û������룬�������� result ��,
	 * �������ݿ��в�ѯ������Ӧ�û����򷵻ؿ��ַ�����
	 * 
	 * 
 */
public class LoginHandle implements Runnable {

	public static String result = "";
	
	public String loginUserName;
	public String loginConnectUrl;
	
	public LoginHandle(String loginUserName,String loginConnectUrl) {
		// TODO Auto-generated constructor stub
		this.loginUserName = loginUserName;
		this.loginConnectUrl = loginConnectUrl;
	}
	
	@Override
	public void run() {

		gotoLogin();
		
	}
	private void gotoLogin() {

		//����Post����
		HttpPost httpRequest = new HttpPost(loginConnectUrl);
		
		//����NameValuePair[]���д洢Post����
		
		List params = new ArrayList();
		params.add(new BasicNameValuePair("userName",loginUserName));
		
		//����http����
		//ȡ��httpresponse
		//����Ƿ�����ɹ�
		
		try {
			
			httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
			
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				
				//ȡ�������ַ���
				result = EntityUtils.toString(httpResponse.getEntity());
				
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
