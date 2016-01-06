package com.jebysun.musicparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpUtil {
	
	private HttpUtil() {}

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url
	 *      发送请求的URL
	 * @return result 
	 * 		所代表远程资源的响应结果
	 * @throws IOException 
	 */
	public static String sendGet(String url) throws IOException {
		StringBuffer resultBuf = new StringBuffer();
		BufferedReader in = null;
		URL realUrl = new URL(url);
		// 打开和URL之间的连接
		HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
		conn.setRequestMethod("GET");
		// 建立实际的连接
		conn.connect();
		// 获取所有响应头字段
//		Map<String, List<String>> headerMap = conn.getHeaderFields();
		// 定义 BufferedReader输入流来读取URL的响应
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			resultBuf.append(line);
		}
		if (in != null) {
			in.close();
		}
		return resultBuf.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数
	 * @return 所代表远程资源的响应结果
	 * @throws IOException 
	 */
	public static String sendPost(String url, Map<String, String> headerParam, String param) throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		URL realUrl = new URL(url);
		// 打开和URL之间的连接
		HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
		conn.setRequestMethod("POST");
		if (headerParam != null) {
			Set<Entry<String, String>> entrySet = headerParam.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {  
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}  
		}
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// 建立实际的连接
		conn.connect();
		// 获取URLConnection对象对应的输出流
		out = new PrintWriter(conn.getOutputStream());
		// 发送请求参数
		out.print(param);
		// flush输出流的缓冲
		out.flush();
		// 获取所有响应头字段
//		Map<String, List<String>> headerMap = conn.getHeaderFields();
		// 定义BufferedReader输入流来读取URL的响应
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		if (out != null) {
			out.close();
		}
		if (in != null) {
			in.close();
		}
		return result;
	}
	
}



