import { Component, OnInit } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.css']
})
export class AppLayoutComponent implements OnInit {

  constructor(private translateService: TranslateService) {
    this.translateService.addLangs(['tr','en','de']);
    const browserLang =  this.translateService.getBrowserLang();
    this.translateService.use(browserLang.match(/en|tr|de/) ? browserLang : 'en' );
  }

  ngOnInit(): void {
  }

}
