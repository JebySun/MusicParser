package com.jebysun.musicparser.qq;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.jebysun.musicparser.qq.Music;
import com.jebysun.musicparser.qq.MusicParser;

public class QQTest {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

		QQTest t = new QQTest();
		t.testMusic();

	}

	private void testMusic() throws IOException {
		String key = MusicParser.getPlayKey();
		List<Music> list = MusicParser.search("周杰伦", 100, 1);
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
