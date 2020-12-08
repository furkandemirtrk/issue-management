import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ProjectService} from "../../services/shared/project.service";
import {Project} from "../../common/project.model";
import {Page} from "../../common/page";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalService} from "ngx-bootstrap/modal";
import {ConfirmationComponent} from "../../shared/confirmation/confirmation.component";
import {UserService} from "../../services/shared/user.service";
import {User} from "../../common/user.model";
import {AutocompleteLibModule} from 'angular-ng-autocomplete';

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
  managerList: User[] = [];
  keyword = 'nameSurname';
  editMode: boolean = false;
  updateManager: User;
  @ViewChild('managerAutoComplete') managerAutoComplete: any;

  constructor(private projectService: ProjectService, private formBuilder: FormBuilder, private modalService: BsModalService, private userService: UserService) {
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
        Validators.maxLength(20),
        Validators.minLength(4)]
      ],
      'manager': [null,
        [Validators.required]
      ]
    });
    this.getUsers();
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
    if (this.editMode){
      this.projectService.updateProject(this.projectForm.value).subscribe(
          response => {
            console.log(response);
          }
      );
    }else{
      this.projectService.createProject(this.projectForm.value).subscribe(
          response => {
            console.log(response);
          }
      );
    }
    this.resetForm();
  }

  updateForm(item : Project){
    this.editMode = true;
    this.projectForm.setValue({
      projectName: item.projectName,
      projectCode: item.projectCode,
      manager: item.manager
    });
    console.log(this.projectForm.value);
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

  getUsers(){
    this.userService.getAll().subscribe( response => {
      this.managerList = response;
    });
  }

  selectEvent(item) {
    this.updateManager = item;
    this.projectForm.patchValue({
      'manager' : item
    });
    console.log(this.projectForm.value);
    // do something with selected item
  }

  onChangeSearch(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.
  }


}
