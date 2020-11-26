package com.angularspringboot.issuemanagement.dto;

import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User Data Transfer Object")
public class UserDto{
  @ApiModelProperty(value = "User ID")
  private Long id;

  @NotNull
  @ApiModelProperty(required = true, value = "Name of User")
  private String nameSurname;

  @NotNull
  @ApiModelProperty(required = true, value = "Mail of User")
  private String email;

  @NotNull
  @ApiModelProperty(required = true, value = "Username of User")
  private String username;
}
