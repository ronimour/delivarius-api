//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.delivarius.api.service;

import java.io.IOException;

import com.delivarius.api.dto.User;
import com.delivarius.api.service.exception.HttpConnectionException;
import com.delivarius.api.service.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService implements Service {
	
    private static final String USER_RESOURCE = "/user";
    
    private String urlBase ;
    
    public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	private ObjectMapper mapper = new ObjectMapper();

    UserService() {
    }

    private String buildUrl(String resource) {
        return getUrlBaseDefined() + USER_RESOURCE + resource;
    }

    private String getUrlBaseDefined() {
		return getUrlBase() == null || getUrlBase().isEmpty() ? URL_BASE_RESOURCE : getUrlBase();
	}

	public User createClientUser(User user, String password) throws ServiceException {
        UserRegister userRegister = new UserRegister();
        userRegister.setUser(user);
        userRegister.setPassword(password);
        userRegister.setType(UserType.CLIENT);
        User userCreated = null;

        try {
            String userRegisterJson = this.mapper.writeValueAsString(userRegister);
            StringBuffer responseJson = new StringBuffer();
            int code = this.executePost(userRegisterJson, "/create", responseJson);
            if (code == 200) {
                userCreated = this.getUserFromJsonResponse(responseJson);
            }

            return userCreated;
        } catch (IOException | HttpConnectionException e) {
            throw new ServiceException(e);
        }
    }

    public User updateUser(User user) throws ServiceException {
        User userUpdated = null;

        try {
            String userJson = this.mapper.writeValueAsString(user);
            StringBuffer responseJson = new StringBuffer();
            int code = this.executePost(userJson, "/update", responseJson);
            if (code == 200) {
                userUpdated = this.getUserFromJsonResponse(responseJson);
            }

            return userUpdated;
        } catch (IOException | HttpConnectionException var6) {
            throw new ServiceException(var6);
        }
    }

    public User getUser(Long userId) throws ServiceException {
        StringBuffer responseJson = new StringBuffer();
        User user = null;

        try {
            int code = this.executeGet(String.format("/%d", userId), responseJson);
            if (code == 200) {
                user = this.getUserFromJsonResponse(responseJson);
            }

            return user;
        } catch (IOException | HttpConnectionException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteUser(Long userId) throws ServiceException {
        int code = 0;
        try {
            code = this.executeDelete(String.format("/%d", userId), new StringBuffer());
        } catch (HttpConnectionException e) {
            throw new ServiceException(e);
        }

        return code == 200;
    }

    public User login(String login, String password) throws ServiceException {
        Logon logon = new Logon(login, password);
        User user = null;

        try {
            String logonJson = this.mapper.writeValueAsString(logon);
            StringBuffer jsonResponse = new StringBuffer();
            int code = this.executePost(logonJson, "/login", jsonResponse);
            if (code == 200) {
                user = this.getUserFromJsonResponse(jsonResponse);
            }

            return user;
        } catch (HttpConnectionException | IOException e) {
            throw new ServiceException(e);
        } 
    }

    private User getUserFromJsonResponse(StringBuffer data) throws IOException {
        User user = null;
        if (data != null && data.length() > 0) {
            user = (User)this.mapper.readValue(data.toString().getBytes(), User.class);
        }
        return user;
    }

    private int executeDelete(String resource, StringBuffer response) throws HttpConnectionException {
        return HttpConnectionResource.getInstance().executeDelete(this.buildUrl(resource), response);
    }

    private int executeGet(String resource, StringBuffer response) throws HttpConnectionException  {
        return HttpConnectionResource.getInstance().executeGet(this.buildUrl(resource), response);
    }

    private int executePost(String jsonBody, String resource, StringBuffer response) throws HttpConnectionException  {
        return HttpConnectionResource.getInstance().executePost(jsonBody, this.buildUrl(resource), response);
    }
}
