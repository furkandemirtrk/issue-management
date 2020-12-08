import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {User} from "../../common/user.model";

@Injectable({
    providedIn: "root"
})
export class UserService {
    private USER_PATH = "/user";

    constructor(private apiService: ApiService) {

    }

    getAll(): Observable<any> {
        return this.apiService.get(this.USER_PATH+'/all',).pipe(map(
            response => {
                if (response) {
                    return response;
                } else {
                    console.log(response);
                    return null;
                }
            }
        ));
    }

    getById(id): Observable<any> {
        return this.apiService.get(this.USER_PATH,id).pipe(map(
            response => {
                if (response) {
                    return response;
                } else {
                    console.log(response);
                    return null;
                }
            }
        ));
    }

    createUser(user): Observable<any>{
        return this.apiService.post(this.USER_PATH,user).pipe(map(
            response => {
                if (response) {
                    return response;
                } else {
                    console.log(response);
                    return null;
                }
            }
        ));
    }

    deleteUser(id): Observable<any> {
        return this.apiService.post(this.USER_PATH+'/delete/'+id).pipe(map(
            response => {
                if (response) {
                    return response;
                } else {
                    console.log(response);
                    return null;
                }
            }
        ));
    }

}
