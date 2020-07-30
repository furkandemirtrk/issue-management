package com.angularspringboot.issuemanagement.repository;

import com.angularspringboot.issuemanagement.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long>{
}
