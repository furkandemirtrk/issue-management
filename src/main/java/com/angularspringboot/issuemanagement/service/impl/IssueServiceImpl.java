package com.angularspringboot.issuemanagement.service.impl;

import com.angularspringboot.issuemanagement.dto.IssueDto;
import com.angularspringboot.issuemanagement.entity.Issue;
import com.angularspringboot.issuemanagement.repository.IssueRepository;
import com.angularspringboot.issuemanagement.service.IssueService;
import com.angularspringboot.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;


public class IssueServiceImpl implements IssueService{

  private final IssueRepository issueRepository;
  private final ModelMapper modelMapper;

  public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper){
    this.issueRepository = issueRepository;
    this.modelMapper = modelMapper;
  }

  @Override public IssueDto save(IssueDto issue){
    if (issue.getDate() == null)
      throw new IllegalArgumentException("Issue cannot be null");

    Issue issueDb = modelMapper.map(issue, Issue.class);
    issueDb = issueRepository.save(issueDb);
    return modelMapper.map(issueDb, IssueDto.class);
  }

  @Override public IssueDto geyById(Long id){
    return null;
  }

  @Override public TPage<IssueDto> getAllPageable(Pageable pageable){
    Page<Issue> data = issueRepository.findAll(pageable);
    TPage page = new TPage<IssueDto>();
    IssueDto[] dto = modelMapper.map(data.getContent(), IssueDto[].class);
    page.setStat(data, Arrays.asList(dto));
    return page;
  }

  @Override public Boolean delete(IssueDto issue){
    return null;
  }
}
