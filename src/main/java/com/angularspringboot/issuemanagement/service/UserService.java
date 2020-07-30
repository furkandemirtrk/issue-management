package com.angularspringboot.issuemanagement.service;

import com.angularspringboot.issuemanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService{
  User save(User user);
  User geyById(Long id);
  Page<User> getAllPageable(Pageable pageable);
}
