import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {AuthenticationService} from "./authentication.service";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
    constructor(private authenticationService:AuthenticationService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (err.status === 401){
                this.authenticationService.logout();
                location.reload();
            }
            const error = err.error.message || err.statusText;
            return throwError(error);
        }))
    }
}
