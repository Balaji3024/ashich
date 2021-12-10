package com.login.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.exception.ResourceNotFoundException;
import com.login.model.User;
import com.login.model.UserRole;
import com.login.repository.RoleRepository;
import com.login.repository.UserRepository;
import com.login.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception  {
		// TODO Auto-generated method stub
		
		User local= userRepository.findByUsername(user.getUsername());
		
		if(local!=null) {
//			System.out.println("User already prsent111");
			throw new ResourceNotFoundException("This Username is already existed");
			
		}else {
			for(UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
				
			}
			user.getUserRoles().addAll(userRoles);
		local=	this.userRepository.save(user);
			
			
		}
		
		
		return local;
	}



	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = this.userRepository.findByUsername(username);
		return user;
	}



	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
			}

}
