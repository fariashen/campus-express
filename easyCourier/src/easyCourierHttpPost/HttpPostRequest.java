package easyCourierHttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;

public abstract class HttpPostRequest extends Thread {

	// 发送请求的远程数据库地址
	// TODO
	protected String urlAddress = "";

	// 发送网络请求的成员变量
	protected HttpClient mHttpClient = new DefaultHttpClient();
	protected HttpPost mHttpPost;
	protected HttpResponse mHttpResponse;
	protected HttpEntity mEntity;
	protected ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

	// 判断是否Json数据
	protected Boolean isJsonData;

	// 解析Json数据的成员变量
	protected InputStream mInputStream;
	protected InputStreamReader mInputStreamReader;
	protected BufferedReader mBufferedReader;
	protected JSONObject mJsonObject;
	protected JSONArray mJsonArray;

	// 保存返回的json字符串信息
	protected String jsonString = "";

	protected String result;
	protected Message message = new Message();

	@Override
	public void run() {

		doHttpPostRequest();
	}

	protected void doHttpPostRequest() {

		// 设置数据库地址
		setUrlAddress();

		// 向数据库地址发送请求
		mHttpPost = new HttpPost(urlAddress);

		try {

			// 设置HttpPost的请求参数
			setParamsIntoPost();

			// 发送Http请求并获取HttpResponse
			mHttpResponse = mHttpClient.execute(mHttpPost);

			// 检测请求是否发送成功
			if (mHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				// 获取请求结果
				mEntity = mHttpResponse.getEntity();
				
				setIsJsonData();

				// 如果是Json的数据格式
				if (isJsonData) {
					// 对Json进行解释操作
					decodeJson();

				} else {

					// 处理非Json格式的请求结果并设置消息
					handleNotJsonResult();

				}

				// 将消息返回给相应主线程处理
				backToMainThreadHandleMessage();

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 对非Json数据进行处理
	 */
	private void handleNotJsonResult() {

		try {

			result = EntityUtils.toString(mEntity);
			message.what = Integer.parseInt(result.trim());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 对Json数据进行解析
	 * 
	 */
	private void decodeJson() {

		// 将字节流转换为json字符串
		readStream();

		// 将jsonString转换为jsonObject
		changeJsonStringIntoJsonObject();

		// 获取jsonArray里的值
		getJsonArrayValue();
	}

	/**
	 * //获取jsonArray里的值
	 */
	private void getJsonArrayValue() {

		for (int i = 0; i < mJsonArray.length(); i++) {
			try {

				mJsonObject = mJsonArray.getJSONObject(i);

				// 根据子类的不同需要来获取不同的值
				getDifferentJsonArrayValue();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * //将jsonString转换为jsonObject
	 */
	private void changeJsonStringIntoJsonObject() {

		try {

			System.out.println("----------->HttpPostRequest"+jsonString);
			mJsonObject = new JSONObject(jsonString);
			// 获取jsonArray
			mJsonArray = mJsonObject.getJSONArray("data");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * //将字节流转换为json字符串
	 */
	private void readStream() {

		try {

			mInputStream = mEntity.getContent();
			mInputStreamReader = new InputStreamReader(mInputStream, HTTP.UTF_8);

			// 讲字符流以BufferReader的形式读出
			mBufferedReader = new BufferedReader(mInputStreamReader);

			// 逐行读取
			String line = "";

			while ((line = mBufferedReader.readLine()) != null) {

				jsonString += line;

			}
			// jsonString += "]}";
			// 关闭字节流
			mInputStream.close();

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 设置URL
	protected abstract void setUrlAddress();

	// 设置params和HttpPost请求参数
	protected abstract void setParamsIntoPost();

	// 设置请求结果的数据类型
	protected abstract void setIsJsonData();

	// 根据子类的不同需要来获取不同的值
	protected abstract void getDifferentJsonArrayValue();

	// 设置相应的handler
	protected abstract void backToMainThreadHandleMessage();

}
