import { Component, OnInit } from '@angular/core';
import {ProjectService} from "../../services/shared/project.service";
import {Project} from "../../common/project.model";
import {Page} from "../../common/page";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
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

  constructor(private projectService: ProjectService) {
  }

  ngOnInit(): void {
    this.setPage({offset: 0});
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
