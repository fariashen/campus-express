package easyCourierHttpPost;

public class UrlFactory {

	/**
	 * ��������Ĳ�ͬ���ܷ��ز�ͬ��Url
	 * 
	 * @param type
	 * @return
	 */
	public static String getUrl(String type) {

		String url = "";

		switch (type) {
		
		case "Login":
			
			//��¼�����������ַ
			url = "http://119.29.4.159/phpserver/login.php";
			break;
			
		case "Regiester":

			//ע������������ַ
			url = "http://119.29.4.159/phpserver/register.php";
			break;
			
		case "PIShow":

			//������Ϣչʾ�����������ַ
			url = "http://119.29.4.159/phpserver/PIshow.php";
			break;
			
		case "PIEdit":

			//������Ϣ�޸ĵ����������ַ
			url = "http://119.29.4.159/phpserver/PIedit.php";
			break;
			
		case "RequestShow":

			//�������չʾ�����������ַ
			url = "http://119.29.4.159/phpserver/ordshow.php";
			break;
			
		case "RUL":

			//�ϴ������������������ַ
			url = "http://119.29.4.159/phpserver/ordrelease.php";
			break;
			
		case "SAR":

			//չʾ�ѽ��ܵ���������������ַ
			url = "http://119.29.4.159/phpserver/myorderacc.php";
			break;
			
		case "SULR":

			//չʾ���ϴ������������ַ
			url = "http://119.29.4.159/phpserver/myorderpro.php";
			break;
			
		case "AcceptRequest":

			//������������������ַ
			url = "http://119.29.4.159/phpserver/ordaccept.php";
			break;
			
		case "Cancel":

			//ȡ����������������ַ
			url = "http://119.29.4.159/phpserver/ordcancel.php";
			break;
			
		case "Completed":

			//������ɵ����������ַ
			url = "http://119.29.4.159/phpserver/ordcompleted.php";
			break;

		}
		return url;
	}
}
