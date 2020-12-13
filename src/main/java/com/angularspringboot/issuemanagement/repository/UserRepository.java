package com.angularspringboot.issuemanagement.repository;

import com.angularspringboot.issuemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{
  User getUserByEmailOrUsername(String email, String username);
  User getUserByEmailAndUsernameAndIdNot(String email, String username, Long id);
  User findByUsername(String username);
}
