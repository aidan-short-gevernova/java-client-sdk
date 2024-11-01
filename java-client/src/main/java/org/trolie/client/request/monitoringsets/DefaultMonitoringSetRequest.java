package org.trolie.client.request.monitoringsets;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.core5.http.HttpHost;
import org.trolie.client.request.streaming.AbstractStreamingGet;
import org.trolie.client.util.TrolieApiConstants;

import java.io.InputStream;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * On-demand GET request for forecast limits with no ETAG usage
 */
public class DefaultMonitoringSetRequest extends AbstractStreamingGet<MonitoringSetsReceiver> {

	JsonFactory jsonFactory;
	
	public DefaultMonitoringSetRequest(
			HttpClient httpClient, 
			HttpHost host, 
			RequestConfig requestConfig,
			int bufferSize, 
			ThreadPoolExecutor threadPoolExecutor, 
			ObjectMapper objectMapper,
			MonitoringSetsReceiver receiver) {
		
		super(httpClient, host, requestConfig, bufferSize, objectMapper, receiver);
		this.jsonFactory = new JsonFactory(objectMapper);
	}

	@Override
	protected String getPath() {
		return TrolieApiConstants.PATH_DEFAULT_MONITORING_SET;
	}
	
	@Override
	protected String getContentType() {
		return TrolieApiConstants.CONTENT_TYPE_MONITORING_SET;
	}

	@Override
	protected void handleResponseContent(InputStream inputStream) {
		new MonitoringSetsResponseParser(receiver).parseResponse(inputStream, jsonFactory);
	}

	
}
