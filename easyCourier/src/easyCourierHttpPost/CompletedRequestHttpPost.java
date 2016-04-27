package easyCourierHttpPost;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import easyCourierFunction.ShowRequestAllInfo;

public class CompletedRequestHttpPost extends HttpPostRequest {

	private String orderId;
	
	public CompletedRequestHttpPost(String orderId) {

		this.orderId = orderId;
	}
	@Override
	protected void setUrlAddress() {

		urlAddress = UrlFactory.getUrl("Completed");
	}

	@Override
	protected void setParamsIntoPost() {

		params.add(new BasicNameValuePair("orderID", orderId));
		
		try {
			mHttpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
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

		ShowRequestAllInfo.mHandler.sendMessage(message);
	}

}
