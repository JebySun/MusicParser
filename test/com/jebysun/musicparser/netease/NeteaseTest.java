package com.jebysun.musicparser.netease;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.jebysun.musicparser.HttpUtil;

public class NeteaseTest {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

//		testBase64Path();
//		testGet();
//		testPost();
		
//		testPlayList();
//		testSearchMusic();
//		testGetMusic();
//		testGetLrc();
	}
	
	public static void testBase64Path() {
		String path = AnyUtil.base64Encode("3313928047887429");
		System.out.println(path);
	}
	
	public static void testGet() throws IOException {
		String s = HttpUtil.sendGet("http://music.163.com/api/playlist/detail?id=3779629");
		System.out.println(s);
	}
	
	public static void testPost() throws IOException {
		String s = HttpUtil.sendPost("http://music.163.com/api/song/lyric", null, "id=186001&lv=-1&kv=-1&tv=-1");
		System.out.println(s);
	}
	
	public static void testPlayList() throws IOException {
		List<Music> list = MusicParser.getPlaylist("3778678");
		for (Music m : list) {
			System.out.println("========================");
			printMusic(m);
		}
	}
	
	public static void testGetMusic() {
		Music m = MusicParser.getMusicById("60274");
		printMusic(m);
	}
	
	public static void testSearchMusic() throws IOException {
		List<Music> list = MusicParser.searchMusic("哪里都是你", 10, 1);
		for (Music m : list) {
			System.out.println("========================");
			printMusic(m);
		}
	}

	public static void testGetLrc() throws IOException {
		String s = MusicParser.getLrcById("25641373");
		System.out.println(s);
	}
	
	
	private static void printMusic(Music m) {
		System.out.println("歌曲ID："+m.getId());
		System.out.println("歌曲名称："+m.getName());
		System.out.println("所属专辑："+m.getAblum());
		System.out.println("专辑封面(宽高240)："+m.getAblumPicUrl());
		System.out.println("专辑封面(自定义宽高)："+m.getAblumPicUrl(120, 120));
		System.out.println("歌手："+m.getSinger());
		System.out.println("低音质歌曲路径："+m.getLowQualityUrl());
		System.out.println("中音质歌曲路径："+m.getMidQualityUrl());
		System.out.println("高音质歌曲路径："+m.getHightQualityUrl());
	}
	
	
}

