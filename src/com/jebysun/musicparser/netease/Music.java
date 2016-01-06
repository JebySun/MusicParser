package com.jebysun.musicparser.netease;

import java.io.Serializable;

/**
 * 网易云音乐实体
 * @author Jeby Sun
 * 2016-1-5 上午10:15:49
 */
public class Music implements Serializable {
	
	private static final long serialVersionUID = 3282283674721084124L;
	
	private String id;
	private String name;
	private String singer;
	private String ablum;
	private String ablumPicUrl;
	private String lowQualityUrl;
	private String midQualityUrl;
	private String hightQualityUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAblum() {
		return ablum;
	}
	public void setAblum(String ablum) {
		this.ablum = ablum;
	}
	/**
	 * 获取默认尺寸240*240专辑封面
	 * @return
	 * @author Jeby Sun
	 */
	public String getAblumPicUrl() {
		String ablumPicUrl = this.ablumPicUrl;
		ablumPicUrl = ablumPicUrl.replaceAll("\\$_w", "240");
		ablumPicUrl = ablumPicUrl.replaceAll("\\$_h", "240");
		return ablumPicUrl;
	}
	
	/**
	 * 获取自定义尺寸专辑封面
	 * @param w
	 * @param h
	 * @return
	 * @author Jeby Sun
	 */
	public String getAblumPicUrl(int w, int h) {
		String ablumPicUrl = this.ablumPicUrl;
		ablumPicUrl = ablumPicUrl.replaceAll("\\$_w", w+"");
		ablumPicUrl = ablumPicUrl.replaceAll("\\$_h", h+"");
		return ablumPicUrl;
	}
	public void setAblumPicUrl(String ablumPicUrl) {
		this.ablumPicUrl = ablumPicUrl;
	}
	public String getLowQualityUrl() {
		return lowQualityUrl;
	}
	public void setLowQualityUrl(String lowQualityUrl) {
		this.lowQualityUrl = lowQualityUrl;
	}
	public String getMidQualityUrl() {
		return midQualityUrl;
	}
	public void setMidQualityUrl(String midQualityUrl) {
		this.midQualityUrl = midQualityUrl;
	}
	public String getHightQualityUrl() {
		return hightQualityUrl;
	}
	public void setHightQualityUrl(String hightQualityUrl) {
		this.hightQualityUrl = hightQualityUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
