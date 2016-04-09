package com.example.easycourier;

/**
 * 
 * @author vacation 
 * 
 * 封装Item中的三个属性 
 * 
 * ResourceId 
 * 
 * 要显示的题目 
 * 
 * 要显示的内容
 * 
 * TODO
 * 
 * 布局文件中控件需重新设置，成员变量待定
 */

public class ItemBean {

	public String itemProviderName;
	public String itemReward;
	public String itemSize;
	public String itemKind;
	public String itemTimeLimit;
	
	//与item_request的布局文件形成了一一对应的关系

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
