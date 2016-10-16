package com.github.yinyee.tropo;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.util.URIUtil;

import net.sf.json.JSONObject;

public class MatrixClient {
	private static final String DEFAULT_BASE_URL = "https://matrix.org";
	private String baseUrl;
	private String mAccessToken;

	public MatrixClient() {
		this.baseUrl = DEFAULT_BASE_URL;
	}
	
	public MatrixClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public void login(String user, String password) throws IOException {
	    HttpClient client = new HttpClient();
	    PostMethod post = new PostMethod(baseUrl + "/_matrix/client/r0/login");

	    JSONObject obj = new JSONObject();
	    obj.put("type", "m.login.password");
	    obj.put("user", user);
	    obj.put("password", password);
	    post.setRequestEntity(new StringRequestEntity(obj.toString(), "application/json", "UTF-8"));
	    
	    int rc = client.executeMethod(post);
	    System.out.println(rc + " - " + post.getResponseBodyAsString());

	    obj = JSONObject.fromObject(post.getResponseBodyAsString());
	    mAccessToken = obj.getString("access_token");
	}
	
	public String getRoomId(String roomAlias) throws IOException {
	    HttpClient client = new HttpClient();
	    GetMethod get = new GetMethod(baseUrl + "/_matrix/client/r0/directory/room/" + URIUtil.encodeAll(roomAlias) + "?access_token=" + mAccessToken);

	    int rc = client.executeMethod(get);
	    System.out.println(rc + " - " + get.getResponseBodyAsString());

	    JSONObject obj = JSONObject.fromObject(get.getResponseBodyAsString());
	    return obj.getString("room_id");
	}

	public void sendMessage(String roomId, String message) throws IOException {
	    HttpClient client = new HttpClient();
	    PutMethod put = new PutMethod(baseUrl + "/_matrix/client/r0/rooms/" + roomId + "/send/m.room.message/" + System.currentTimeMillis() + "?access_token=" + mAccessToken);

	    JSONObject obj = new JSONObject();
	    obj.put("msgtype", "m.text");
	    obj.put("body", message);
	    put.setRequestEntity(new StringRequestEntity(obj.toString(), "application/json", "UTF-8"));
	    
	    int rc = client.executeMethod(put);
	    System.out.println(rc + " - " + put.getResponseBodyAsString());
	}	
	
	public String readLastMessage(String roomId) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(baseUrl + "/_matrix/client/r0/rooms/" + roomId + "/messages?access_token=" + mAccessToken + "&dir=b&limit=1");
        
        int rc = client.executeMethod(get);
        System.out.println(rc + " - " + get.getResponseBodyAsString());
        
        JSONObject obj = JSONObject.fromObject(get.getResponseBodyAsString());
        String body = obj.getJSONArray("chunk").getJSONObject(0).getJSONObject("content").optString("body");
        body = (body != null) ? body : "";
        return body;
    }
	
}
