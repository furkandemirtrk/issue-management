package com.angularspringboot.issuemanagement.service.impl;

import com.angularspringboot.issuemanagement.dto.RegistrationRequest;
import com.angularspringboot.issuemanagement.dto.UserDto;
import com.angularspringboot.issuemanagement.entity.User;
import com.angularspringboot.issuemanagement.repository.UserRepository;
import com.angularspringboot.issuemanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder){
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserDto save(UserDto userDto){
    if (userDto.getEmail() == null || userDto.getEmail().isEmpty() || userDto.getUsername() == null || userDto.getUsername().isEmpty() || userDto.getNameSurname() == null || userDto.getNameSurname().isEmpty()){
      throw new IllegalArgumentException("Required area is cannot null");
    }
    if (userRepository.getUserByEmailOrUsername(userDto.getEmail(), userDto.getUsername()) != null){
      throw new IllegalArgumentException("Email or Username is already exist");
    }
    User user = modelMapper.map(userDto, User.class);
    User userSave = userRepository.save(user);
    return modelMapper.map(userSave, UserDto.class);
  }

  @Override
  public UserDto update(Long id,UserDto userDto){
    User user = userRepository.getOne(id);
    if (user == null){
      throw new IllegalArgumentException("User does not exit id : ".concat(id.toString()));
    }
    User userCheck = userRepository.getUserByEmailAndUsernameAndIdNot(userDto.getEmail(), userDto.getUsername(), id);
    if ( userCheck != null){
      throw new IllegalArgumentException("Email or Username is already exist");
    }
    user.setEmail(userDto.getEmail());
    user.setNameSurname(userDto.getNameSurname());
    user.setUsername(userDto.getUsername());
    userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public User geyById(Long id){
    return userRepository.getOne(id);
  }

  @Override
  public Page<User> getAllPageable(Pageable pageable){
    return userRepository.findAll(pageable);
  }

  @Override
  public List<UserDto> getAll(){
    List<User> userList = userRepository.findAll();
    return Arrays.asList(modelMapper.map(userList, UserDto[].class));
  }

  @Override
  public Boolean delete(Long id){
    try {
      User user = userRepository.getOne(id);
      userRepository.delete(user);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public User findByUsername(String username){
    return userRepository.findByUsername(username);
  }

  @Transactional
  public Boolean register(RegistrationRequest registrationRequest){
    User user = null;
    try {
      user = new User();
      user.setUsername(registrationRequest.getUsername());
      user.setNameSurname(registrationRequest.getNameSurname());
      user.setEmail(registrationRequest.getEmail());
      user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
      userRepository.save(user);
      return Boolean.TRUE;
    } catch (Exception e) {
      log.error("register ", e);
      return Boolean.FALSE;
    }
  }
}
