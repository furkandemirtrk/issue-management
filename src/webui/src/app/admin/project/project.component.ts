import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../services/shared/project.service";
import {Project} from "../../common/project.model";
import {Page} from "../../common/page";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html'
})
export class ProjectComponent implements OnInit {
  page = new Page();
  projects: Project[];
  selected = [];
  columns= [
    {prop:'id', name:'No'},
    {prop:'projectName', name:'Project Name'},
    {prop:'projectCode', name:'Project Code'}
  ];
  projectForm: FormGroup;

  constructor(private projectService: ProjectService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.setPage({offset: 0});
    this.projectForm = this.formBuilder.group({
      'projectName': [null,
        [Validators.required,
        Validators.minLength(2)]
      ],
      'projectCode': [null,[
        Validators.required,
        Validators.maxLength(10),
        Validators.minLength(4)]
      ],
    });
  }

  get f() {
    return this.projectForm.controls
  }

  resetForm(){
    this.projectForm.reset();
  }

  saveForm(){
    if (!this.projectForm.valid)
      return;

    this.projectService.createProject(this.projectForm.value).subscribe(
        response => {
          console.log(response);
        }
    );
    this.setPage(this.page);
    this.resetForm();
  }

  setPage(pageInfo) {
    console.log(pageInfo);
    this.page.page = pageInfo.offset;
    if (this.page.page != 0){
      this.page.size = pageInfo.limit;
    }
    this.projectService.getAll(this.page).subscribe(
        res => {
          this.page.size = res.size;
          this.page.page = res.page;
          this.page.totalPages = res.totalPages;
          this.page.totalElements = res.totalElements;
          this.projects = res.content;
          console.log(res);
        }
    );
  }


  next() {
    console.log(this.page);
    this.page.page = this.page.page + 1;
  }

  prev() {
    this.page.page = this.page.page - 2;
  }

  reset() {
    this.page.page = 0;
  }

  isLastPage(): boolean {
    return this.projects ? this.page.page === (this.projects.length - this.page.size): true;
  }

  isFirstPage(): boolean {
    return this.projects ? this.page.page === 0 : true;
  }

}
