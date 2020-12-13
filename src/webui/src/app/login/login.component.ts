import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../security/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  returnUrl: string = '';

  constructor(private authenticationService: AuthenticationService, private formBuilder: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.authenticationService.logout();
    this.returnUrl = this.activatedRoute.snapshot.queryParams['returnUrl'];
    this.loginForm = this.formBuilder.group({
      'username' :[null, [Validators.required]] ,
      'password' :[null, [Validators.required]]
    })
  }

  login(){
    console.log(this.loginForm.value);
    console.log(this.loginForm.value.username);
    this.authenticationService.login(this.loginForm.value.username, this.loginForm.value.password).pipe(first()).subscribe(resp => {
      this.router.navigate([this.returnUrl])
    });
  }

}
