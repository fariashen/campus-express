package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

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
public class RULHttpPost extends HttpPostRequest {

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
	protected void setUrlAddress() {

		urlAddress = UrlFactory.getUrl("RUL");
	}

	@Override
	protected void setParamsIntoPost() {

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
			mHttpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void setIsJsonData() {

		isJsonData = false;
	}

	@Override
	protected void getDifferentJsonArrayValue() {

	}

	@Override
	protected void backToMainThreadHandleMessage() {

		RequestUpLoad.RULHandler.sendMessage(message);
	}

}
