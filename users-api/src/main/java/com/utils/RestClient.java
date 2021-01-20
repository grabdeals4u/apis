package com.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	private String url;
	private HttpMethod method;
	private Object data;
	private HttpHeaders headers;
	private Class requestDataClass;
	private Class responseClass;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	public Class getRequestDataClass() {
		return requestDataClass;
	}

	public void setRequestDataClass(Class requestDataClass) {
		this.requestDataClass = requestDataClass;
	}

	public Class getResponseClass() {
		return responseClass;
	}

	public void setResponseClass(Class responseClass) {
		this.responseClass = responseClass;
	}

	public Object invoke() {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> requestEntity = new HttpEntity(data, headers);
		ResponseEntity<Object> res = restTemplate.exchange(url,method, requestEntity, responseClass);
		return res;
	}

}
