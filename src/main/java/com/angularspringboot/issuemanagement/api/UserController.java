package com.angularspringboot.issuemanagement.api;

import com.angularspringboot.issuemanagement.dto.UserDto;
import com.angularspringboot.issuemanagement.service.impl.UserServiceImpl;
import com.angularspringboot.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL ,description = "User APIs")
@Slf4j
@CrossOrigin
public class UserController{

  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService){
    this.userService = userService;
  }

  @GetMapping("/all")
  @ApiOperation(value = "Get All Users", response = UserDto.class)
  public ResponseEntity<List<UserDto>> getAll(){
    log.info("UserController getAll start");
    return ResponseEntity.ok(userService.getAll());
  }

  @PostMapping
  @ApiOperation(value = "Create User Operation", response = UserDto.class)
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
    log.info("UserController createUser start");
    return ResponseEntity.ok(userService.save(userDto));
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update User Operation", response = UserDto.class)
  public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody UserDto userDto){
    log.info("UserController updateUser start");
    return ResponseEntity.ok(userService.update(id,userDto));
  }

  @PostMapping("/{id}")
  @ApiOperation(value = "Update User Operation", response = UserDto.class)
  public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id){
    log.info("UserController delete start");
    return ResponseEntity.ok(userService.delete(id));
  }
}
