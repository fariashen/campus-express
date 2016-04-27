package easyCourierHttpPost;

public class UrlFactory {

	/**
	 * 根据请求的不同功能返回不同的Url
	 * 
	 * @param type
	 * @return
	 */
	public static String getUrl(String type) {

		String url = "";

		switch (type) {
		
		case "Login":
			
			//登录的网络请求地址
			url = "http://119.29.4.159/phpserver/login.php";
			break;
			
		case "Regiester":

			//注册的网络请求地址
			url = "http://119.29.4.159/phpserver/register.php";
			break;
			
		case "PIShow":

			//个人信息展示的网络请求地址
			url = "http://119.29.4.159/phpserver/PIshow.php";
			break;
			
		case "PIEdit":

			//个人信息修改的网络请求地址
			url = "http://119.29.4.159/phpserver/PIedit.php";
			break;
			
		case "RequestShow":

			//快递请求展示的网络请求地址
			url = "http://119.29.4.159/phpserver/ordshow.php";
			break;
			
		case "RUL":

			//上传快递请求的网络请求地址
			url = "http://119.29.4.159/phpserver/ordrelease.php";
			break;
			
		case "SAR":

			//展示已接受的请求的网络请求地址
			url = "http://119.29.4.159/phpserver/myorderacc.php";
			break;
			
		case "SULR":

			//展示已上传的网络请求地址
			url = "http://119.29.4.159/phpserver/myorderpro.php";
			break;
			
		case "AcceptRequest":

			//接受请求的网络请求地址
			url = "http://119.29.4.159/phpserver/ordaccept.php";
			break;
			
		case "Cancel":

			//取消请求的网络请求地址
			url = "http://119.29.4.159/phpserver/ordcancel.php";
			break;
			
		case "Completed":

			//请求完成的网络请求地址
			url = "http://119.29.4.159/phpserver/ordcompleted.php";
			break;

		}
		return url;
	}
}
