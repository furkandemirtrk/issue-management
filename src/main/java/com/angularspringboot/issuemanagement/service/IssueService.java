package com.angularspringboot.issuemanagement.service;

import com.angularspringboot.issuemanagement.dto.IssueDto;
import com.angularspringboot.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;


public interface IssueService{
  IssueDto save(IssueDto issue);
  IssueDto geyById(Long id);
  TPage<IssueDto> getAllPageable(Pageable pageable);
  Boolean delete(IssueDto issue);
}
