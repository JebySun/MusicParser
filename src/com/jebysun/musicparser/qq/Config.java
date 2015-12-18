package com.jebysun.musicparser.qq;

public final class Config {
	private Config() {}
	
	//音乐搜索，三个参数
	public static final String MUSIC_SEARCH = "http://s.music.qq.com/fcgi-bin/music_search_new_platform?t=0&n=<0>&aggr=1&cr=1&loginUin=0&format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json&needNewCode=0&p=<1>&catZhida=0&remoteplace=sizer.newclient.next_song&w=<2>";

	//获取音乐播放KEY,无需参数
	public static final String MUSIC_PLAY_KEY = "http://base.music.qq.com/fcgi-bin/fcg_musicexpress.fcg?json=3&loginUin=0&format=jsonp&inCharset=GB2312&outCharset=GB2312&notice=0&platform=yqq&needNewCode=0";

	//音乐播放，两个参数
	public static final String MUSIC_PLAY = "http://ws.stream.qqmusic.qq.com/C200<0>.m4a?vkey=<1>&fromtag=0";
	
	//获取音乐专辑图片，三个参数
	public static final String MUSIC_ABLUM_PICTURE = "http://i.gtimg.cn/music/photo/mid_album_300/<0>/<1>/<2>.jpg";
	
	//获取音乐歌词，两个参数
	public static final String MUSIC_LRC = "http://music.qq.com/miniportal/static/lyric/<0>/<1>.xml";
	
}
