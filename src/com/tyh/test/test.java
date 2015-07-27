package com.tyh.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 测试类
 * @author tyhhs
 *
 */
public class test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String content = "hello, this is a xml message sent from client";
				
		String url = "http://localhost:8080/wechat-server/message";
		String textParam = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA["+content+"]]></Content><MsgId>1234567890123456</MsgId></xml>";
		
		String eventParam = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[event]]></MsgType>" +
				"<Content><![CDATA["+content+"]]></Content><MsgId>1234567890123456</MsgId></xml>";
		
		String test = "<xml><ToUserName>toUser</ToUserName><FromUserName>fromUser</FromUserName><CreateTime>1348831860</CreateTime><MsgType>text</MsgType>"+
		"<Content>this message is replied by serveer</Content><MsgId>1234567890123456</MsgId></xml>";
		String result = post(url,textParam);
		
		System.out.println(result);
	}
	
	/**
	 * post请求 
	 * @param url 请求的地址
	 * @param param 请求的内容
	 * @return 接口返回的内容
	 */
	private static String post(String url, String param){
		try {
			//创建post方法
			HttpPost request = new HttpPost(url);
			//设置请求参数
			request.setEntity(new StringEntity(param));
			//获取返回
			HttpResponse response = HttpClients.createDefault().execute(request);
			
			//根据返回码判断请求是否成功
			String result = "";
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
			}
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
}
