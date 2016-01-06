package com.jebysun.musicparser.qq;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jebysun.musicparser.HttpUtil;
/**
 * QQ音乐资源解析
 * @author Jeby Sun
 *
 */
public class MusicParser {
	
	/**
	 * 获取播放Key
	 * @return
	 * @throws IOException 
	 */
	public static String getPlayKey() throws IOException {
		String key = null;
		String responseStr = HttpUtil.sendGet(Config.MUSIC_PLAY_KEY);
		if (responseStr==null || "".equals(responseStr) || "error".equals(responseStr)) {
			return null;
		}
		String json = responseStr.substring(responseStr.indexOf("(")+1, responseStr.length()-2);
		JSONObject jsonObj = JSON.parseObject(json);
		key = jsonObj.getString("key");
		return key;
	}
	
	/**
	 * 音乐搜索
	 * @param keyword
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 * @throws IOException 
	 */
	public static List<Music> search(String keyword, int pageSize, int pageIndex) throws IOException {
		List<String> infoList = searchInfo(keyword, pageSize, pageIndex);
		List<Music> musicList = new ArrayList<Music>();
		for (int i=0; i<infoList.size(); i++) {
			String info = infoList.get(i);
			System.out.println(info);
			String[] infoArr = info.split("\\|");
			if (infoArr.length != 25) {
				continue;
			}
			musicList.add(new Music(infoArr));
		}
		return musicList;
	}
	
	private static List<String> searchInfo(String keyword, int pageSize, int pageIndex) throws IOException {
		List<String> musicInfoList = new ArrayList<String>();
		String json = searchJSON(keyword, String.valueOf(pageSize), String.valueOf(pageIndex));
		if (json == null || "".equals(json) || "error".equals(json)) {
			return musicInfoList;
		}
		JSONObject jsonObj = JSON.parseObject(json);
		jsonObj = jsonObj.getJSONObject("data");
		jsonObj = jsonObj.getJSONObject("song");
		JSONArray jsonArray = jsonObj.getJSONArray("list");
		for (int i=0; i<jsonArray.size(); i++) {
			jsonObj = (JSONObject)jsonArray.get(i);
			musicInfoList.add(jsonObj.getString("f"));
//			System.out.println(jsonObj.getString("f"));
		}
		return musicInfoList;
	}
	
	private static String searchJSON(String keyword, String pageSize, String pageIndex) throws IOException {
		String searchUrl = Config.MUSIC_SEARCH;
		//设置当前搜索结果页大小
		searchUrl = searchUrl.replaceFirst("<0>", pageSize);
		//查询第几页数据
		searchUrl = searchUrl.replaceFirst("<1>", pageIndex);
		//搜索关键字
		try {
			keyword = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		searchUrl = searchUrl.replaceFirst("<2>", keyword);
		return HttpUtil.sendGet(searchUrl);
	}
	
	

}
