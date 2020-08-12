package com.angularspringboot.issuemanagement.api;

import com.angularspringboot.issuemanagement.dto.ProjectDto;
import com.angularspringboot.issuemanagement.entity.Project;
import com.angularspringboot.issuemanagement.service.impl.ProjectServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/project")
public class ProjectController{

  private final ProjectServiceImpl projectServiceImpl;

  public ProjectController(ProjectServiceImpl projectServiceImpl){
    this.projectServiceImpl = projectServiceImpl;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id", required = true) Long id){
    ProjectDto projectDto = projectServiceImpl.getById(id);
    return ResponseEntity.ok(projectDto);
  }

  @PostMapping
  public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto){
    return ResponseEntity.ok(projectServiceImpl.save(projectDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id", required = true) Long id ,@Valid @RequestBody ProjectDto projectDto){
    return ResponseEntity.ok(projectServiceImpl.update(id,projectDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
    return ResponseEntity.ok(projectServiceImpl.delete(id));
  }
}
