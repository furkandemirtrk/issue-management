package com.angularspringboot.issuemanagement.service.impl;

import com.angularspringboot.issuemanagement.dto.IssueDto;
import com.angularspringboot.issuemanagement.entity.Issue;
import com.angularspringboot.issuemanagement.repository.IssueRepository;
import com.angularspringboot.issuemanagement.service.IssueService;
import com.angularspringboot.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
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

  @Override public IssueDto getById(Long id){
    Issue issue = issueRepository.getById(id);
    return modelMapper.map(issue, IssueDto.class);
  }

  @Override public TPage<IssueDto> getAllPageable(Pageable pageable){
    Page<Issue> data = issueRepository.findAll(pageable);
    TPage page = new TPage<IssueDto>();
    IssueDto[] dto = modelMapper.map(data.getContent(), IssueDto[].class);
    page.setStat(data, Arrays.asList(dto));
    return page;
  }

  @Override public Boolean delete(Long id){
    issueRepository.deleteById(id);
    return true;
  }

  @Override public IssueDto update(Long id, IssueDto issueDto){
    Issue issue = issueRepository.getOne(id);

    if (issue.getId() == null)
      throw new IllegalArgumentException("Issue cannot be null");

    if (issueDto.getDate() == null)
      throw new IllegalArgumentException("Issue cannot be null");

    issue.setDate(issueDto.getDate());
    issue.setDescription(issueDto.getDescription());
    issue.setDetails(issueDto.getDetails());
    issue.setIssueStatus(issueDto.getIssueStatus());
    issueRepository.save(issue);
    return modelMapper.map(issue, IssueDto.class);
  }
}
