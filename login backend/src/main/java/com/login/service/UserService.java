package com.login.service;

import java.util.Set;

import com.login.model.User;
import com.login.model.UserRole;


public interface UserService {

	public User createUser(User user,Set<UserRole> userRoles) throws Exception;

	public User getUser(String username);

	public void deleteUser(Long id );
}
