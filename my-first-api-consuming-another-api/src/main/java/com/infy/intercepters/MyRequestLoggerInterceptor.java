package com.infy.intercepters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyRequestLoggerInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		System.out.println("Http Method and URL : "+ request.getMethod()+ "   "+request.getURI());
		System.out.println("Headers : "+request.getHeaders());
		System.out.println(">>>>>>>>>>>>> "+request.getMethodValue());
		
		System.out.println(">>>> Request body "+ new String(body, StandardCharsets.UTF_8));
		
		ClientHttpResponse  response =  execution.execute(request, body);
		
		System.out.println("Response Status Code : "+response.getStatusCode().name());
		
		 InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
		        String responseBody = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
		        System.out.println("Response body: "+ responseBody);
		 
		return response;
	}

}
