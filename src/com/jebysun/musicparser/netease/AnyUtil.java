package com.jebysun.musicparser.netease;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AnyUtil {
	
	private AnyUtil() {}
	
	/**
	 * 编码结果长度固定为24位最后两位为等号的Base64编码
	 * 此代码来源于网络
	 * @param b
	 * @return
	 * @author Jeby Sun
	 */
	public static String encode(byte[] b) {
		char[] codec_table = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/' };
		int totalBits = b.length * 8;
		int nn = totalBits % 6;
		int curPos = 0;// process bits
		StringBuffer toReturn = new StringBuffer();
		while (curPos < totalBits) {
			int bytePos = curPos / 8;
			switch (curPos % 8) {
			case 0:
				toReturn.append(codec_table[(b[bytePos] & 0xfc) >> 2]);
				break;
			case 2:
				toReturn.append(codec_table[(b[bytePos] & 0x3f)]);
				break;
			case 4:
				if (bytePos == b.length - 1) {
					toReturn.append(codec_table[((b[bytePos] & 0x0f) << 2) & 0x3f]);
				} else {
					int pos = (((b[bytePos] & 0x0f) << 2) | ((b[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
					toReturn.append(codec_table[pos]);
				}
				break;
			case 6:
				if (bytePos == b.length - 1) {
					toReturn.append(codec_table[((b[bytePos] & 0x03) << 4) & 0x3f]);
				} else {
					int pos = (((b[bytePos] & 0x03) << 4) | ((b[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
					toReturn.append(codec_table[pos]);
				}
				break;
			default:
				// never hanppen
				break;
			}
			curPos += 6;
		}
		if (nn == 2) {
			toReturn.append("==");
		} else if (nn == 4) {
			toReturn.append("=");
		}
		return toReturn.toString();
	}
	
	/**
	 * 根据音乐dfsId获取音乐的关键路径
	 * @param dfsId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @author Jeby Sun
	 */
	public static String base64Encode(String dfsId) {
		String keyStr = "3go8&$8*3*3h0k(2)2";
		byte[] keyByte = keyStr.getBytes();
		byte[] dfsIdByte = dfsId.getBytes();
		int keyByteLength = keyByte.length;
		for (int i = 0; i < dfsIdByte.length; i++) {
			byte tmp = keyByte[i % keyByteLength];
			dfsIdByte[i] = (byte) (dfsIdByte[i] ^ tmp);
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] md5Bytes = md5.digest(dfsIdByte);
		String retval = encode(md5Bytes);
		retval = retval.replaceAll("/", "_");
		retval = retval.replaceAll("\\+", "-");
		return retval;
	}

}
