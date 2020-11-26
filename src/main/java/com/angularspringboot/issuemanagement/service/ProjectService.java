package com.angularspringboot.issuemanagement.service;

import com.angularspringboot.issuemanagement.dto.ProjectDto;
import com.angularspringboot.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProjectService{
  ProjectDto save(ProjectDto project);
  ResponseEntity<List<ProjectDto>> getAll();
  ProjectDto getById(Long id);
  TPage<ProjectDto> getAllPageable(Pageable pageable);
  ProjectDto getByProjectCode(String projectCode);
  List<ProjectDto> getByProjectContains(String projectCode);
  Boolean delete(Long id);
  ProjectDto update(Long id, ProjectDto projectDto);
}
