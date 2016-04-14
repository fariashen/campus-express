package easyCourierHttpPost;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
import easyCourierFunction.RequestUpLoad;

/**
 * 
 * @author vacation
 * 
 *         根据传入的参数：
 * 
 *         快递类型， 收件人名称， 收件人联系方式， 收件时限
 * 
 *         与数据库进行通信，插入记录
 * 
 */
public class RULHttpPost extends Thread {

	/**
	 * 
	 * 插入数据库的数据参数
	 * 
	 */
	private String RUL_providerName;
	private String RUL_nickName;
	private String RUL_reward;
	private String RUL_addFrom;
	private String RUL_addTo;
	private String RUL_timeLimit;
	private String RUL_size;
	private String RUL_phone;
	private String RUL_kinds;
	private String RUL_remarks;

	// 访问数据库链接地址

	private final String REQUEST_UPLOAD_URL = "http://www.caiweicheng.cn/phpserver/ordrelease.php";
	// TODO

	// 进行POST请求的变量
	HttpClient mHttpClient;
	HttpPost mHttpPost;
	HttpResponse mHttpResponse;
	ArrayList<NameValuePair> params;

	// 发送网络请求后返回的结果码
	String RUL_RESULT="";

	public RULHttpPost(String providerName, String nickName, String reward,
			String addFrom, String addTo, String timeLimit, String size,
			String phone, String kinds, String remarks) {

		this.RUL_providerName = providerName;
		this.RUL_nickName = nickName;
		this.RUL_reward = reward;
		this.RUL_addFrom = addFrom;
		this.RUL_addTo = addTo;
		this.RUL_timeLimit = timeLimit;
		this.RUL_size = size;
		this.RUL_phone = phone;
		this.RUL_kinds = kinds;
		this.RUL_remarks = remarks;

	}

	@Override
	public void run() {

		addRequest();

	}

	private void addRequest() {

		/**
		 * 初始化网络请求的变量
		 */
		mHttpClient = new DefaultHttpClient();
		// 设置HttpPost
		mHttpPost = new HttpPost(REQUEST_UPLOAD_URL);
		params = new ArrayList<NameValuePair>();

		/**
		 * 设置参数
		 */
		params.add(new BasicNameValuePair("userName", RUL_providerName));
		params.add(new BasicNameValuePair("nickName", RUL_nickName));
		params.add(new BasicNameValuePair("reward", RUL_reward));
		params.add(new BasicNameValuePair("addFrom", RUL_addFrom));
		params.add(new BasicNameValuePair("addTo", RUL_addTo));
		params.add(new BasicNameValuePair("timeLimit", RUL_timeLimit));
		params.add(new BasicNameValuePair("size", RUL_size));
		params.add(new BasicNameValuePair("phone", RUL_phone));
		params.add(new BasicNameValuePair("kinds", RUL_kinds));
		params.add(new BasicNameValuePair("remarks", RUL_remarks));

		try {

			// 将参数设置在HttpPost请求内
			mHttpPost.setEntity(new UrlEncodedFormEntity(params));

			// 发送请求
			mHttpResponse = mHttpClient.execute(mHttpPost);

			// 判断请求是否发送成功
			if (mHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// 获取服务器返回的结果码
				RUL_RESULT = EntityUtils.toString(mHttpResponse.getEntity());
				System.out.println(RUL_RESULT.trim()+"-RUL---------->");

				// 将结果码传给 RequestUpLoad.class 处理
				Message rulHttpPostMessage = new Message();
				
				rulHttpPostMessage.what = Integer.parseInt(RUL_RESULT.trim());
				
				RequestUpLoad.RULHandler.sendMessage(rulHttpPostMessage);

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
