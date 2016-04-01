package com.sparkjava.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sparkjava.Main;

import spark.Spark;

public class UserControllerTest {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	@BeforeClass
	public static void beforeClass() {
	Main.main(null);
	}
	
	@AfterClass
	public static void afterClass() {
	Spark.stop();
	}
	
	@Test
	public void getAllUsersTest() throws ClientProtocolException, IOException {
		String url = "http://localhost:4567/getAllUsers";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void createUserTest() throws ClientProtocolException, IOException {
		String url = "http://localhost:4567/createUser";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-type","application/json");
		request.setEntity(new StringEntity("{ \"id\" : \"8930216c-2608-44b9-aad4-9d76d8aafdf\", \"firstName\" : \"Dorris\", \"lastName\" : \"Keeling\", \"email\" : \"Darby_Leffler68@gmail.com\", \"address\" : { \"street\" : \"193 Talon Valley\", \"city\" : \"South Tate furt\", \"zip\" : \"47069\", \"state\" : \"IA\", \"country\" : \"US\" }, \"dateCreated\" : \"2016-03-15T07:02:40.896Z\", \"company\" : { \"name\" : \"Denesik Group\", \"website\" : \"http://jodie.org\" }, \"profilePic\" : \"http://lorempixel.com/640/480/people\"}"));
		request.setHeader("Accept", "application/json");
		// add request header
		HttpResponse response = client.execute(request);
		String id = convertStreamToString(response.getEntity().getContent());
		assertTrue("8930216c-2608-44b9-aad4-9d76d8aafdf".equals(id.trim()));
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void updateUserTest() throws ClientProtocolException, IOException {
		String url = "http://localhost:4567/updateUser";
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-type","application/json");
		request.setEntity(new StringEntity("{ \"id\" : \"1630215c-2608-44b9-aad4-9d56d8aafd4c\", \"firstName\" : \"Dorris\", \"lastName\" : \"Keeling\", \"email\" : \"Darby_Leffler68@gmail.com\", \"address\" : { \"street\" : \"193 Talon Valley\", \"city\" : \"South Tate furt\", \"zip\" : \"47069\", \"state\" : \"IA\", \"country\" : \"US\" }, \"dateCreated\" : \"2016-03-15T07:02:40.896Z\", \"company\" : { \"name\" : \"Denesik Group\", \"website\" : \"http://jodie.org\" }, \"profilePic\" : \"http://lorempixel.com/640/480/people\"}"));
		request.setHeader("Accept", "application/json");
		// add request header
		HttpResponse response = client.execute(request);
		String id = convertStreamToString(response.getEntity().getContent());
		assertTrue("1630215c-2608-44b9-aad4-9d56d8aafd4c".equals(id.trim()));
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
