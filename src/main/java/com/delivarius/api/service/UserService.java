package com.delivarius.api.service;

import java.io.IOException;

import com.delivarius.api.dto.User;
import com.delivarius.api.service.exception.HttpConnectionException;
import com.delivarius.api.service.exception.ServiceException;

public class UserService extends AbstractService {
	
    public static final String RESOURCE = "/user";

    UserService() {
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
            int code = this.executePost(userRegisterJson, CREATE, responseJson);
            if (isResultOK(code)) {
                userCreated = (User) getDtoFromJsonResponse(User.class, responseJson);
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
            int code = this.executePost(userJson, UPDATE, responseJson);
            if (isResultOK(code)) {
                userUpdated = (User) getDtoFromJsonResponse(User.class, responseJson);
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
            if (isResultOK(code)) {
                user = (User) getDtoFromJsonResponse(User.class, responseJson);
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

        return isResultOK(code);
    }

    public User login(String login, String password) throws ServiceException {
        Logon logon = new Logon(login, password);
        User user = null;

        try {
            String logonJson = this.mapper.writeValueAsString(logon);
            StringBuffer responseJson = new StringBuffer();
            int code = this.executePost(logonJson, "/login", responseJson);
            if (isResultOK(code)) {
                user = (User) getDtoFromJsonResponse(User.class, responseJson);
            }

            return user;
        } catch (HttpConnectionException | IOException e) {
            throw new ServiceException(e);
        } 
    }

	@Override
	protected String getResource() {
		return RESOURCE;
	}
}
