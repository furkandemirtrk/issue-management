package com.angularspringboot.issuemanagement.api;


import com.angularspringboot.issuemanagement.dto.IssueDto;
import com.angularspringboot.issuemanagement.service.impl.IssueServiceImpl;
import com.angularspringboot.issuemanagement.service.impl.ProjectServiceImpl;
import com.angularspringboot.issuemanagement.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
public class IssueController{

  private final IssueServiceImpl issueService;

  public IssueController(IssueServiceImpl issueServiceImpl){
    this.issueService = issueServiceImpl;
  }


  @GetMapping("/{id}")
  public ResponseEntity<IssueDto> getById(@PathVariable(value = "id", required = true) Long id){
    IssueDto IssueDto = issueService.getById(id);
    return ResponseEntity.ok(IssueDto);
  }

  @PostMapping
  public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto issueDto){
    return ResponseEntity.ok(issueService.save(issueDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<IssueDto> updateProject(@PathVariable(value = "id", required = true) Long id ,@Valid @RequestBody IssueDto issueDto){
    return ResponseEntity.ok(issueService.update(id,issueDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
    return ResponseEntity.ok(issueService.delete(id));
  }
}
