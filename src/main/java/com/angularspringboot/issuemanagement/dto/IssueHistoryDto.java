package com.angularspringboot.issuemanagement.dto;

import com.angularspringboot.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel(value = "Issue History Data Transfer Object")
public class IssueHistoryDto{
  @ApiModelProperty(value = "ID")
  private Long id;
  @ApiModelProperty(required = true, value = "Issue")
  private IssueDto issue;
  @ApiModelProperty(required = true, value = "Description")
  private String description;
  @ApiModelProperty(required = true, value = "Date")
  private Date date;
  @ApiModelProperty(required = true, value = "Issue Status")
  private IssueStatus issueStatus;
  @ApiModelProperty(required = true, value = "Details")
  private String details;
  @ApiModelProperty(required = true, value = "Assignee")
  private UserDto assignee;

}