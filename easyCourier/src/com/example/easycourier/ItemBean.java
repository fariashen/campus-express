package com.example.easycourier;

/**
 * 
 * @author vacation 
 * 
 * ��װItem�е��������� 
 * 
 * ResourceId 
 * 
 * Ҫ��ʾ����Ŀ 
 * 
 * Ҫ��ʾ������
 * 
 * TODO
 * 
 * �����ļ��пؼ����������ã���Ա��������
 */

public class ItemBean {

	public String itemProviderName;
	public String itemReward;
	public String itemSize;
	public String itemKind;
	public String itemTimeLimit;
	
	//��item_request�Ĳ����ļ��γ���һһ��Ӧ�Ĺ�ϵ

	public ItemBean(String itemProviderName, String itemReward,
			String itemSize, String itemKind, String itemTimeLimit) {
		super();
		this.itemProviderName = itemProviderName;
		this.itemReward = itemReward;
		this.itemSize = itemSize;
		this.itemKind = itemKind;
		this.itemTimeLimit = itemTimeLimit;
	}
	
	
	
}
