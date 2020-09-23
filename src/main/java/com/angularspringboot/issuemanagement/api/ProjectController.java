package com.angularspringboot.issuemanagement.api;

import com.angularspringboot.issuemanagement.dto.ProjectDto;
import com.angularspringboot.issuemanagement.service.impl.ProjectServiceImpl;
import com.angularspringboot.issuemanagement.util.ApiPaths;
import com.angularspringboot.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL ,description = "Project APIs")
@Slf4j
@CrossOrigin
public class ProjectController{

  private final ProjectServiceImpl projectServiceImpl;

  public ProjectController(ProjectServiceImpl projectServiceImpl){
    this.projectServiceImpl = projectServiceImpl;
  }

  @GetMapping("/pagination")
  @ApiOperation(value = "Get By Pagination Operation", response = ProjectDto.class)
  public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable){
    log.info("ProjectController getAllByPagination");
    TPage<ProjectDto> allPageable = projectServiceImpl.getAllPageable(pageable);
    return ResponseEntity.ok(allPageable);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get By Id Operation", response = ProjectDto.class)
  public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id", required = true) Long id){
    log.info("ProjectController getById");
    log.debug(String.format("ProjectController getById param : %s" , id ));
    ProjectDto projectDto = projectServiceImpl.getById(id);
    return ResponseEntity.ok(projectDto);
  }

  @PostMapping
  @ApiOperation(value = "Create Operation", response = ProjectDto.class)
  public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto){
    log.info("ProjectController createProject");
    return ResponseEntity.ok(projectServiceImpl.save(projectDto));
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update Operation", response = ProjectDto.class)
  public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id", required = true) Long id ,@Valid @RequestBody ProjectDto projectDto){
    log.info("ProjectController updateProject");
    return ResponseEntity.ok(projectServiceImpl.update(id,projectDto));
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete Operation", response = Boolean.class)
  public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
    log.info("ProjectController deleteProject");
    log.debug(String.format("ProjectController deleteProject param : %s",id));
    return ResponseEntity.ok(projectServiceImpl.delete(id));
  }
}
