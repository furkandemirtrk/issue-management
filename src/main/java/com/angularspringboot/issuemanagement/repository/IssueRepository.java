package com.angularspringboot.issuemanagement.repository;

import com.angularspringboot.issuemanagement.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IssueRepository extends JpaRepository<Issue,Long>{

}
