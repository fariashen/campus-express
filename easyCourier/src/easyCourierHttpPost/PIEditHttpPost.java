package easyCourierHttpPost;

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

import easyCourierFunction.Login;
import easyCourierFunction.PersonalInfoEdit;

import android.os.Message;

/**
 * @author vacation
 *
 *���ݴ���Ĳ������û��������룬�绰������
 *�����ݿ����ͨ�ţ��޸ĸ�����Ϣ
 *
 */
public class PIEditHttpPost extends Thread {

//	����POST����Ĳ���
	HttpClient mHttpClient;
	HttpPost mHttpPost;
	HttpResponse mHttpResponse;
	HttpEntity mHttpEntity;
	ArrayList<NameValuePair> params;
	
//	��Ҫ���ݵĲ���
	String PassWord;
	
	private String PIEdit_result;
	
	public PIEditHttpPost(String PassWord) {

		this.PassWord = PassWord;
	}
	
	@Override
	public void run() {
		
		editPersonalInfo();
	}

	private void editPersonalInfo() {

//		���ò���(name,value)
		
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", Login.LOGIN_USERNAME));
		params.add(new BasicNameValuePair("passWord", PassWord));
		params.add(new BasicNameValuePair("phone", "111"));
		params.add(new BasicNameValuePair("email", "111"));
		params.add(new BasicNameValuePair("address", "111"));
		params.add(new BasicNameValuePair("stunum", "111"));
		params.add(new BasicNameValuePair("sex", "111"));
		
//		����HTTPPOST
		mHttpPost = new HttpPost(PersonalInfoEdit.PIEDIT_CONNECTURL);
		
		try {
			
//			���ò���
			
			mHttpPost.setEntity(new UrlEncodedFormEntity(params));
//			��������
			mHttpClient = new DefaultHttpClient();
			mHttpResponse = mHttpClient.execute(mHttpPost);
			
//			�ж������Ƿ��ͳɹ�
			if(mHttpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){

//				��ȡ��������������
//				����޸ĳɹ��򷵻� "success" �ַ���
				PIEdit_result = EntityUtils.toString(mHttpResponse.getEntity());
				
//				�����������صĽ��������PersonalInfoEdit����
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