package com.angularspringboot.issuemanagement.service;

import com.angularspringboot.issuemanagement.dto.UserDto;
import com.angularspringboot.issuemanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService{
  UserDto save(UserDto userDto);
  UserDto update(Long id, UserDto userDto);
  User geyById(Long id);
  Page<User> getAllPageable(Pageable pageable);
  List<UserDto> getAll();
  Boolean delete(Long id);
  User findByUsername(String username);
}
