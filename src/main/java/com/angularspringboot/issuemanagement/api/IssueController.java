package com.angularspringboot.issuemanagement.api;


import com.angularspringboot.issuemanagement.dto.IssueDto;
import com.angularspringboot.issuemanagement.service.impl.IssueServiceImpl;
import com.angularspringboot.issuemanagement.util.ApiPaths;
import com.angularspringboot.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL ,description = "Issue APIs")
@Slf4j
public class IssueController{

  private final IssueServiceImpl issueService;

  public IssueController(IssueServiceImpl issueServiceImpl){
    this.issueService = issueServiceImpl;
  }

  @GetMapping("/pagination")
  @ApiOperation(value = "Get By Pagination Operation", response = IssueDto.class)
  public ResponseEntity<TPage<IssueDto>> getAllPagination(Pageable pageable){
    log.info("IssueController getAllPagination");
    TPage<IssueDto> issueDtoTPage = issueService.getAllPageable(pageable);
    return ResponseEntity.ok(issueDtoTPage);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get By Id Operation", response = IssueDto.class)
  public ResponseEntity<IssueDto> getById(@PathVariable(value = "id", required = true) Long id){
    log.info("IssueController getById");
    log.debug(String.format("IssueController getById param : %s", id));
    IssueDto IssueDto = issueService.getById(id);
    return ResponseEntity.ok(IssueDto);
  }

  @PostMapping
  @ApiOperation(value = "Create Operation", response = IssueDto.class)
  public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto issueDto){
    log.info("IssueController createProject");
    return ResponseEntity.ok(issueService.save(issueDto));
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Update Operation", response = IssueDto.class)
  public ResponseEntity<IssueDto> updateProject(@PathVariable(value = "id", required = true) Long id ,@Valid @RequestBody IssueDto issueDto){
    log.info("IssueController updateProject");
    return ResponseEntity.ok(issueService.update(id,issueDto));
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete Operation", response = Boolean.class)
  public ResponseEntity<Boolean> deleteProject(@PathVariable("id") Long id){
    log.info("IssueController deleteProject");
    log.debug(String.format("IssueController deleteProject param : %s", id));
    return ResponseEntity.ok(issueService.delete(id));
  }
}
