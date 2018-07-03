package com.delivarius.api.service;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.delivarius.api.dto.DataTransferObject;
import com.delivarius.api.service.connection.HttpConnectionResource;
import com.delivarius.api.service.exception.HttpConnectionException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractService implements Service{
	
	static final String URL_BASE_RESOURCE = "http://localhost:8081";
	
	static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	protected static final String UPDATE = "/update";
	
	protected static final String CREATE = "/create";

	protected static final String ALL = "/all";
	
    private String urlBase ;
    
	protected final ObjectMapper mapper = new ObjectMapper();
    
    public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	protected abstract String getResource();
	

	protected int executeDelete(String resource, StringBuffer response) throws HttpConnectionException {
        return HttpConnectionResource.getInstance().executeDelete(this.buildUrl(resource), response);
    }

	protected int executeGet(String resource, StringBuffer response) throws HttpConnectionException  {
        return HttpConnectionResource.getInstance().executeGet(this.buildUrl(resource), response);
    }

	protected int executePost(String jsonBody, String resource, StringBuffer response) throws HttpConnectionException  {
        return HttpConnectionResource.getInstance().executePost(jsonBody, this.buildUrl(resource), response);
    }
	
    private String buildUrl(String resource) {
        return getUrlBaseDefined() + getResource() + resource;
    }
	
    private String getUrlBaseDefined() {
		return getUrlBase() == null || getUrlBase().isEmpty() ? URL_BASE_RESOURCE : getUrlBase();
	}
    
    protected DataTransferObject getDtoFromJsonResponse(Class<? extends DataTransferObject> type, StringBuffer data) throws IOException {
        DataTransferObject dto = null;
        if (data != null && data.length() > 0) {
            dto = this.mapper.readValue(data.toString().getBytes(), type);
        }
        return dto;
    }
    
    protected List<? extends DataTransferObject> getListDtoFromJsonResponse(Class<? extends DataTransferObject> type, StringBuffer data) throws IOException {
    	List<DataTransferObject> dto = null;
    	if (data != null && data.length() > 0) {
    		dto = this.mapper.readValue(data.toString().getBytes(), mapper.getTypeFactory().constructCollectionType(List.class, type));
    	}
    	return dto;
    }

	protected boolean isResultOK(int code) {
		return code == HttpsURLConnection.HTTP_OK;
	}
    
}
