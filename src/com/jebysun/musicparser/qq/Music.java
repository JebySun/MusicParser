package com.jebysun.musicparser.qq;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Music implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2037853461437025891L;
	
	private String musicName;
	private String singer;
	private String ablum;
	private String ablumPictureUrl;
	private String musicUrl;
	private String lrcUrl;
	
	public Music() {}
	
	public Music(String[] infoArr) {
		this.musicName = infoArr[1];
		this.singer = infoArr[3];
		this.ablum = infoArr[5];
		this.musicUrl = fillMusicUrl(Config.MUSIC_PLAY, infoArr[20]);
		this.ablumPictureUrl = fillAblumPictureUrl(Config.MUSIC_ABLUM_PICTURE, infoArr[22]);
		this.lrcUrl = fillLrcUrl(Config.MUSIC_LRC, infoArr[0]);
	}
	
	private String fillMusicUrl(String template, String p) {
		return template.replaceFirst("<0>", p);
	}
	
	private String fillAblumPictureUrl(String template, String p) {
		template = template.replaceFirst("<0>", p.substring(p.length()-2, p.length()-1));
		template = template.replaceFirst("<1>", p.substring(p.length()-1));
		template = template.replaceFirst("<2>", p);
		return template;
	}
	private String fillLrcUrl(String template, String p) {
		int id = Integer.parseInt(p);
		template = template.replaceFirst("<0>", String.valueOf(id%100));
		template = template.replaceFirst("<1>", p);
		return template;
	}
	
	/**
	 * unicode字符转换为中文
	 * @param src
	 * @return
	 */
	private String unicode2char(String src) {
		if (src == null) {
			return null;
		}
		src = src.replaceAll("&amp;", "");
		String gtr = "";
		Pattern p = Pattern.compile("#\\d+;");
		Matcher m = p.matcher(src);
		while (m.find()) {
			gtr = m.group();
			src = src.replaceFirst("#\\d+;", String.valueOf((char)Integer.parseInt(gtr.substring(1, gtr.length()-1))));
		}
		return src;
	}
 
	public String getMusicName() {
		return unicode2char(musicName);
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getSinger() {
		return unicode2char(singer);
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAblum() {
		return unicode2char(ablum);
	}

	public void setAblum(String ablum) {
		this.ablum = ablum;
	}

	public String getMusicUrl(String key) {
		return musicUrl.replaceFirst("<1>", key);
	}
	
	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getAblumPictureUrl() {
		return ablumPictureUrl;
	}

	public void setAblumPictureUrl(String ablumPictureUrl) {
		this.ablumPictureUrl = ablumPictureUrl;
	}

	public String getLrcUrl() {
		return lrcUrl;
	}

	public void setLrcUrl(String lrcUrl) {
		this.lrcUrl = lrcUrl;
	}
	
	
	
}
