package net.shadervertex.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.shadervertex.sms.entity.User;
import net.shadervertex.sms.repository.UserRepository;

@Service
public class UserService 
{

	
    @Autowired
    private UserRepository repo;
  
  public User login(String username, String password) {
      User user = repo.findByUsernameAndPassword(username, password);
      return user;
  }
	
}