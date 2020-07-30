package com.angularspringboot.issuemanagement.service;

import com.angularspringboot.issuemanagement.entity.IssueHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IssueHistoryService{
  IssueHistory save(IssueHistory issueHistory);
  IssueHistory geyById(Long id);
  Page<IssueHistory> getAllPageable(Pageable pageable);
  Boolean delete(IssueHistory issueHistory);
}
