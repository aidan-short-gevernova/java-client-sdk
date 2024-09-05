package org.trolie.client;


import java.net.URISyntaxException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.trolie.client.impl.TrolieClientImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TrolieClientBuilder {

	HttpHost host;
	HttpClientBuilder httpClientBuilder;
	ThreadPoolExecutor threadPoolExecutor;
	RequestConfig requestConfig;
	int bufferSize = 4096;
	ObjectMapper objectMapper;

	public TrolieClientBuilder(
			String baseUrl, 
			HttpClientBuilder clientBuilder) {
		super();
		try {
			this.host = HttpHost.create(baseUrl);
		} catch (URISyntaxException e) {
			throw new TrolieException(e);
		}
		this.httpClientBuilder = clientBuilder;
	}

	public TrolieClientBuilder threadPoolExecutor(ThreadPoolExecutor executor) {
		this.threadPoolExecutor = executor;
		return this;
	}
	
	public TrolieClientBuilder requestConfig(RequestConfig config) {
		this.requestConfig = config;
		return this;
	}
	
	public TrolieClientBuilder bufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
		return this;
	}
	
	public TrolieClientBuilder objectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		return this;
	}
	
    public TrolieClient build() {

    	if (threadPoolExecutor == null) {
    		threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    	}
    	
    	if (requestConfig == null) {
    		requestConfig = RequestConfig.DEFAULT;
    	}
    	
    	if (objectMapper == null) {
    		objectMapper = new ObjectMapper();
    	}

    	return new TrolieClientImpl(httpClientBuilder.build(), host, requestConfig, bufferSize, threadPoolExecutor, objectMapper);
    }
}
