import { Component, OnInit } from '@angular/core';
import {BsModalRef} from "ngx-bootstrap/modal";
import {Subject} from "rxjs";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  public body: string;
  public header: string;
  public onClose: Subject<boolean>;
  public active: boolean;

  constructor(private bsModalRef: BsModalRef) {}

  ngOnInit(): void {
    this.onClose = new Subject<boolean>();
  }

  public showConfirmation(header:string, body:string): void{
    this.body = body;
    this.header = header;
    this.active = true;
  }

  onConfirm(){
    this.active = false;
    this.onClose.next(true);
    this.bsModalRef.hide();
  }

  onCancel(){
    this.active = false;
    this.onClose.next(false);
    this.bsModalRef.hide();
  }
}
