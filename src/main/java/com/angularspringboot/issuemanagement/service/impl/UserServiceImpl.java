package com.angularspringboot.issuemanagement.service.impl;

import com.angularspringboot.issuemanagement.entity.User;
import com.angularspringboot.issuemanagement.repository.UserRepository;
import com.angularspringboot.issuemanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public User save(User user){
    return userRepository.save(user);
  }

  @Override
  public User geyById(Long id){
    return userRepository.getOne(id);
  }

  @Override
  public Page<User> getAllPageable(Pageable pageable){
    return userRepository.findAll(pageable);
  }
}
