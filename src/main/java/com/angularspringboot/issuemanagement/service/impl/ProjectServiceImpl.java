package com.angularspringboot.issuemanagement.service.impl;

import com.angularspringboot.issuemanagement.dto.ProjectDto;
import com.angularspringboot.issuemanagement.entity.Project;
import com.angularspringboot.issuemanagement.repository.ProjectRepository;
import com.angularspringboot.issuemanagement.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

  private final ProjectRepository projectRepository;
  private final ModelMapper modelMapper;

  public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper){
    this.projectRepository = projectRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public ProjectDto save(ProjectDto projectDto){
    Project projectCheck = projectRepository.getProjectByProjectCode(projectDto.getProjectCode());
    if (projectCheck != null){
      throw  new IllegalArgumentException("Project Code Already Exist");
    }
    Project project = modelMapper.map(projectDto, Project.class);
    Project projectSave = projectRepository.save(project);
    return modelMapper.map(projectSave, ProjectDto.class);
  }

  @Override
  public ProjectDto getById(Long id){
    Project project = projectRepository.getOne(id);
    return modelMapper.map(project,ProjectDto.class);
  }

  @Override
  public Page<ProjectDto> getAllPageable(Pageable pageable) {
    return null;
  }

  @Override public ProjectDto getByProjectCode(String projectCode){
    return null;
  }

  @Override public List<ProjectDto> getByProjectContains(String projectCode){
    return null;
  }

  public Boolean delete(Long id){
    projectRepository.deleteById(id);
    return true;
  }

  @Override public ProjectDto update(Long id, ProjectDto projectDto){
    Project  projectDb = projectRepository.getOne(id);
    if (projectDb == null){
      throw  new IllegalArgumentException("Project Does Not Exist Id :  " + id);
    }
    Project projectCheck = projectRepository.getProjectByProjectCodeAndIdNot(projectDto.getProjectCode(), id);
    if (projectCheck != null){
      throw  new IllegalArgumentException("Project Code Already Exist");
    }
    projectDb.setProjectCode(projectDto.getProjectCode());
    projectDb.setProjectName(projectDto.getProjectName());
    projectRepository.save(projectDb);
    return modelMapper.map(projectDb, ProjectDto.class);
  }
}
