import { Component, OnInit } from '@angular/core';
import {User} from "../../common/user.model";
import {UserService} from "../../services/shared/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username:string;
  constructor() { }

  ngOnInit(): void {
    this.username = JSON.parse(localStorage.getItem('currentUser')).username;
  }

}
