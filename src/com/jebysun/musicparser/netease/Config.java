package com.jebysun.musicparser.netease;

public final class Config {
	
	private Config() {}
	
	//根据歌单id获取歌单/播放列表
	public static final String SONG_LIST = "http://music.163.com/api/playlist/detail?id=$_id";
	
	//根据歌曲id获取歌曲信息
	public static final String SONG_DETAIL = "http://music.163.com/api/song/detail/?id=$_id&ids=%5B$_id%5D";
	
	//根据专辑图片picId专辑图片地址
	public static final String ABLUM_PIC_URL = "http://p3.music.126.net/$_path/$_picId.jpg?param=$_wy$_h";
	
	//根据音乐dfsId获取音乐地址
	public static final String MUSIC_URL = "http://m2.music.126.net/$_path/$_dfsId.mp3";
	
	
	//音乐搜索URL(POST)
	public static final String SEARCH_URL = "http://music.163.com/api/search/get/web";
	
	//根据歌曲id获取歌词(POST)
	public static final String LRC_URL = "http://music.163.com/api/song/lyric/";
	
	
}








