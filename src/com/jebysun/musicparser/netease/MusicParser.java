package com.jebysun.musicparser.netease;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jebysun.musicparser.HttpUtil;

/**
 * 网易云音乐音乐资源解析
 * @author Jeby Sun
 * @date 2016-01-05
 */
public class MusicParser {
	
	/**
	 * 音乐关键字搜索
	 * @param keyword - 搜索关键字
	 * @param pageSize - 搜索结果每页大小
	 * @param pageIndex - 当前第几页
	 * @return
	 * @throws IOException 
	 */
	public static List<Music> searchMusic(String keyword, int pageSize, int pageIndex) throws IOException {
		List<Music> playlist = new ArrayList<Music>();
		String url = Config.SEARCH_URL;
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Referer", "http://music.163.com/search");
		String paramStr = "s=" + keyword + "&offset=" + pageSize*(pageIndex-1)+ "&limit=" + pageSize + "&type=1";
		String resultStr = HttpUtil.sendPost(url, headerMap, paramStr);
		if (resultStr!=null) {
			JSONObject json = JSON.parseObject(resultStr);
			json = json.getJSONObject("result");
			JSONArray jsonArrTrack = json.getJSONArray("songs");
			for (int i=0; i<jsonArrTrack.size(); i++) {
				JSONObject jsonTrack = jsonArrTrack.getJSONObject(i);
				Music m = parseMusicFromJSONObj(jsonTrack);
				playlist.add(m);
			}
		}
		return playlist;
	}
	
	/**
	 * 获取播放列表/歌单
	 * @param playlistId - 歌单id，排行榜id
	 * @return
	 * @throws IOException
	 * @author Jeby Sun
	 */
	public static List<Music> getPlaylist(String playlistId) throws IOException {
		List<Music> playlist = new ArrayList<Music>();
		String url = Config.SONG_LIST;
		url = url.replaceAll("\\$_id", playlistId);
		String resultStr = httpRequest(url);
		if (resultStr!=null) {
			JSONObject json = JSON.parseObject(resultStr);
			json = json.getJSONObject("result");
			JSONArray jsonArrTrack = json.getJSONArray("tracks");
			for (int i=0; i<jsonArrTrack.size(); i++) {
				JSONObject jsonTrack = jsonArrTrack.getJSONObject(i);
				Music m = parseMusicFromJSONObj(jsonTrack);
				playlist.add(m);
			}
		}
		return playlist;
	}
	
	/**
	 * 根据歌曲id获取歌曲详细信息
	 * @param id
	 * @return
	 * @author Jeby Sun
	 */
	public static Music getMusicById(String id) {
		Music m = null;
		String url = Config.SONG_DETAIL;
		url = url.replaceAll("\\$_id", id);
		String resultStr = httpRequest(url);
		JSONObject json = JSON.parseObject(resultStr);
		JSONArray jsonArr = json.getJSONArray("songs");
		if (jsonArr.size()!=0) {
			json = jsonArr.getJSONObject(0);
			m = parseMusicFromJSONObj(json);
		}
		return m;
	}
	
	/**
	 * 根据歌曲id获取歌词
	 * @param id
	 * @return
	 * @throws IOException
	 * @author Jeby Sun
	 */
	public static String getLrcById(String id) throws IOException {
		String lrcStr = null;
		String url = Config.LRC_URL;
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Referer", "http://music.163.com/search");
		String paramStr = "id=" + id + "&lv=-1&kv=-1&tv=-1";
		String resultStr = HttpUtil.sendPost(url, headerMap, paramStr);
		if (resultStr==null) {
			return null;
		}
		JSONObject json = JSON.parseObject(resultStr);
		json = json.getJSONObject("lrc");
		if (json !=null) {
			lrcStr = json.getString("lyric");
		}
		return lrcStr;
	}
	
	
	
	
	//////////////////////////内部私有方法////////////////////////////////////
	private static String httpRequest(String url) {
		String result = null;
		try {
			result = HttpUtil.sendGet(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static Music parseMusicFromJSONObj(JSONObject jsonTrack) {
		Music m = new Music();
		String id = String.valueOf(jsonTrack.getLong("id"));
		//歌曲id
		m.setId(id);
		//歌曲名称
		String name = jsonTrack.getString("name");
		m.setName(name);
		JSONArray jsonArrArtlist = jsonTrack.getJSONArray("artists");
		String singer = "";
		for (int j=0; j<jsonArrArtlist.size(); j++) {
			JSONObject jsonArt = jsonArrArtlist.getJSONObject(j);
			String artName = jsonArt.getString("name");
			if (j>0) {
				singer += "/";
			}
			singer += artName;
		}
		//歌手
		m.setSinger(singer);
		JSONObject jsonAblum = jsonTrack.getJSONObject("album");
		String albumName = jsonAblum.getString("name");
		//专辑名称
		m.setAblum(albumName);
		String picId = jsonAblum.getString("picId");
		String ablumPicUrl = Config.ABLUM_PIC_URL.replaceAll("\\$_path", AnyUtil.base64Encode(picId));
		ablumPicUrl = ablumPicUrl.replaceAll("\\$_picId", picId);
		//专辑封面
		m.setAblumPicUrl(ablumPicUrl);
		
		String mp3Url = jsonTrack.getString("mp3Url");
		if (mp3Url!=null) {
			//歌曲在线播放地址/低音质（96比特率）
			m.setLowQualityUrl(mp3Url);
		}
		
		JSONObject jsonMMusic = jsonTrack.getJSONObject("mMusic");
		//中等音质歌曲地址
		m.setMidQualityUrl(parseMusicUrl(jsonMMusic));
		JSONObject jsonHMusic = jsonTrack.getJSONObject("hMusic");
		//高音质歌曲地址
		m.setHightQualityUrl(parseMusicUrl(jsonHMusic));
		return m;
	}
	
	private static String parseMusicUrl(JSONObject jsonMusic) {
		String url = null;
		if (jsonMusic!=null) {
			String mdfsId = String.valueOf(jsonMusic.getLong("dfsId"));
			String midQualityUrl = Config.MUSIC_URL.replaceAll("\\$_path", AnyUtil.base64Encode(mdfsId));
			url = midQualityUrl.replaceAll("\\$_dfsId", mdfsId);
		}
		return url;
	}

	
	

}








