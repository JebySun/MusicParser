import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jebysun.musicparser.qq.Music;
import com.jebysun.musicparser.qq.QQMusicParser;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Test t = new Test();
		t.testMusic();
		
	}
	
	private void testMusic() {
		String key = QQMusicParser.getPlayKey();
		List<Music> list = QQMusicParser.search("纯音乐", 100, 1);
		for (Music m : list) {
			System.out.println(m.getMusicName());
			System.out.println(m.getSinger());
			System.out.println(m.getAblum());
			System.out.println(m.getMusicUrl(key));
			System.out.println(m.getAblumPictureUrl());
			System.out.println(m.getLrcUrl());
		}
		
	}
	

}
