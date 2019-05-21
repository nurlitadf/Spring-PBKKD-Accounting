package com.pbkk.accounting.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbkk.accounting.model.Customers;
import com.pbkk.accounting.model.Drivers;
import com.pbkk.accounting.model.Restaurants;
import com.pbkk.accounting.model.Token;
@Service
public class SendAPIImpl implements SendAPI{

	@Override
	public Customers getCustomerData(Long idCustomer, String token) {
		String url="http://128.199.210.218:7001/customer/"+ Long.toString(idCustomer);
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("token", token);
			//System.out.println(token);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			ObjectMapper mapper = new ObjectMapper();
			String responses=response.toString();
	        Customers customer = mapper.readValue(responses.substring(1, responses.length()-1), Customers.class);
	        return customer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getToken() {
		String urlToken="http://128.199.210.218:7001/token";
		URL url;
	    HttpURLConnection connection = null;  
	    String urlParameters="email=nurlitadf17@gmail.com&password=123";
	    try {
	      //Create connection
	      url = new URL(urlToken);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      ObjectMapper mapper = new ObjectMapper();
	      Token token=mapper.readValue(response.toString(),Token.class);
	      return token.getValue();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	}

	@Override
	public Restaurants getRestaurantData(Long idRestaurant, String token) {
		String url="http://128.199.210.218:7001/restaurant/"+ Long.toString(idRestaurant);
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("token", token);
			System.out.println(idRestaurant);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			ObjectMapper mapper = new ObjectMapper();
			String responses=response.toString();
	        Restaurants restaurant = mapper.readValue(responses.substring(1, responses.length()-1), Restaurants.class);
	        return restaurant;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Drivers getDriverData(Long idDriver, String token) {
		String url="http://128.199.210.218:7001/driver/"+ Long.toString(idDriver);
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("token", token);
			//System.out.println(token);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			ObjectMapper mapper = new ObjectMapper();
			String responses=response.toString();
	        Drivers driver = mapper.readValue(responses.substring(1, responses.length()-1), Drivers.class);
	        return driver;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
