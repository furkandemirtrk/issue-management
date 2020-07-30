package com.angularspringboot.issuemanagement.service;

import com.angularspringboot.issuemanagement.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProjectService{
  Project save(Project project);
  Project geyById(Long id);
  Page<Project> getAllPageable(Pageable pageable);
  List<Project> getByProjectCode(String projectCode);
  List<Project> getByProjectContains(String projectCode);
  Boolean delete(Project project);
}
