package com.angularspringboot.issuemanagement.repository;

import com.angularspringboot.issuemanagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project,Long>{
  Project getProjectByProjectCode(String projectCode);
  Project getProjectByProjectCodeAndIdNot(String projectCode, Long id);
}
