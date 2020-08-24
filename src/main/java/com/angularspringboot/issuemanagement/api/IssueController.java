package com.angularspringboot.issuemanagement.api;


import com.angularspringboot.issuemanagement.dto.IssueDto;
import com.angularspringboot.issuemanagement.service.impl.IssueServiceImpl;
import com.angularspringboot.issuemanagement.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL ,description = "Issue APIs")
public class IssueController{

  private final IssueServiceImpl issueService;

  public IssueController(IssueServiceImpl issueServiceImpl){
    this.issueService = issueServiceImpl;
  }


  @GetMapping("/{id}")
  @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
  public ResponseEntity<IssueDto> getById(@PathVariable(value = "id", required = true) Long id){
    IssueDto IssueDto = issueService.getById(id);
    return ResponseEntity.ok(IssueDto);
  }

  @PostMapping
  @ApiOperation(value = "Create Operation", response = IssueDto.class)
  public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto issueDto){
    return ResponseEntity.ok(issueService.save(issueDto));
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update Operation", response = IssueDto.class)
  public ResponseEntity<IssueDto> updateProject(@PathVariable(value = "id", required = true) Long id ,@Valid @RequestBody IssueDto issueDto){
    return ResponseEntity.ok(issueService.update(id,issueDto));
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete Operation", response = Boolean.class)
  public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
    return ResponseEntity.ok(issueService.delete(id));
  }
}
