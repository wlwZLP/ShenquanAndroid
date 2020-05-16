package com.dongdian.shenquan.view.circle;


import com.dongdian.shenquan.bean.GoodsHomeBean;

/**
 */
public class ADInfo {
	String id = "";
	String url = "";
	String content = "";
	String type = "";
	GoodsHomeBean.BannersBean bannerBean;

	public GoodsHomeBean.BannersBean getBannerBean() {
		return bannerBean;
	}

	public void setBannerBean(GoodsHomeBean.BannersBean bannerBean) {
		this.bannerBean = bannerBean;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
