package com.example.easycourier;

/**
 * 
 * @author vacation
 * 
 *         封装Item中的三个属性
 * 
 *         ResourceId
 * 
 *         要显示的题目
 * 
 *         要显示的内容
 * 
 *         TODO
 * 
 *         布局文件中控件需重新设置，成员变量待定
 */

public class ItemBean {

	public String itemOrederId;
	public String itemProviderName;
	public String courierName;
	public String itemNickName;
	public String itemReward;
	public String itemAddForm;
	public String itemAddTo;
	public String itemTimeLimit;
	public String itemSize;
	public String itemPhone;
	public String itemKind;
	public String itemRemarks;
	public String ordflag;

	// 与item_request的布局文件形成了一一对应的关系

	public ItemBean(String itemOrderId, String itemProviderName,
			String itemNickName, String itemReward, String itemAddForm,
			String itemAddTo, String itemTimeLimit, String itemSize,
			String itemPhone, String itemKind, String itemRemarks) {
		super();
		this.itemOrederId = itemOrderId;
		this.itemProviderName = itemProviderName;
		this.itemNickName = itemNickName;
		this.itemReward = itemReward;
		this.itemAddForm = itemAddForm;
		this.itemAddTo = itemAddTo;
		this.itemTimeLimit = itemTimeLimit;
		this.itemSize = itemSize;
		this.itemPhone = itemPhone;
		this.itemKind = itemKind;
		this.itemRemarks = itemRemarks;
	}

	public ItemBean(String itemOrderId, String itemProviderName,
			String courierName, String itemNickName, String itemReward,
			String itemAddForm, String itemAddTo, String itemTimeLimit,
			String itemSize, String itemPhone, String itemKind,
			String itemRemarks,String ordflag) {
		super();
		this.itemOrederId = itemOrderId;
		this.itemProviderName = itemProviderName;
		this.courierName = courierName;
		this.itemNickName = itemNickName;
		this.itemReward = itemReward;
		this.itemAddForm = itemAddForm;
		this.itemAddTo = itemAddTo;
		this.itemTimeLimit = itemTimeLimit;
		this.itemSize = itemSize;
		this.itemPhone = itemPhone;
		this.itemKind = itemKind;
		this.itemRemarks = itemRemarks;
		this.ordflag = ordflag;
	}

}
