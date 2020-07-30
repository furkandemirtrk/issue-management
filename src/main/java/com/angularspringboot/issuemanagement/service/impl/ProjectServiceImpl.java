package com.angularspringboot.issuemanagement.service.impl;

import com.angularspringboot.issuemanagement.entity.Project;
import com.angularspringboot.issuemanagement.repository.ProjectRepository;
import com.angularspringboot.issuemanagement.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

  private final ProjectRepository projectRepository;

  public ProjectServiceImpl(ProjectRepository projectRepository){
    this.projectRepository = projectRepository;
  }

  @Override
  public Project save(Project project){
    if (project.getProjectCode() == null){
      throw new IllegalArgumentException("Project code cannot be null");
    }
    return projectRepository.save(project);
  }

  @Override
  public Project geyById(Long id){
    return projectRepository.getOne(id);
  }

  @Override
  public Page<Project> getAllPageable(Pageable pageable) {
    return projectRepository.findAll(pageable);
  }

  @Override public List<Project> getByProjectCode(String projectCode){
    return null;
  }

  @Override public List<Project> getByProjectContains(String projectCode){
    return null;
  }

  @Override public Boolean delete(Project project){
    return null;
  }
}
