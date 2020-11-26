import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ProjectService} from "../../services/shared/project.service";
import {Project} from "../../common/project.model";
import {Page} from "../../common/page";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalService} from "ngx-bootstrap/modal";
import {ConfirmationComponent} from "../../shared/confirmation/confirmation.component";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html'
})
export class ProjectComponent implements OnInit {
  projects: Project[];
  selected = [];
  projectForm: FormGroup;
  rows = 10;
  first = 0;

  constructor(private projectService: ProjectService, private formBuilder: FormBuilder, private modalService: BsModalService) {
  }

  ngOnInit(): void {
    this.setPage();
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
    this.resetForm();
  }

  setPage() {
    this.projectService.getAll().subscribe(
        res => {
          this.projects = res;
        }
    );
  }


  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.projects ? this.first === (this.projects.length - this.rows): true;
  }

  isFirstPage(): boolean {
    return this.projects ? this.first === 0 : true;
  }

  showDeleteConfirmation(value){
    const modal = this.modalService.show(ConfirmationComponent);
    (<ConfirmationComponent>modal.content).showConfirmation('Delete Confirmation','Are you sure for delete project?');
    (<ConfirmationComponent>modal.content).onClose.subscribe(result => {
      if (result  === true){
        this.projectService.deleteProject(value).subscribe(response => {
          console.log(response);
          if (response === true){
            this.setPage();
          }
        });
      } else if (result ===false){
        console.log('no');
      }
    });
  }

}
