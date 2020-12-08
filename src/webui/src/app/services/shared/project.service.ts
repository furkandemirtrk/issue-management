import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
    providedIn: "root"
})
export class ProjectService {
    private PROJECT_PATH = "/project";

    constructor(private apiService: ApiService) {

    }

    getAll(): Observable<any> {
        return this.apiService.get(this.PROJECT_PATH+'/all',).pipe(map(
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
        return this.apiService.get(this.PROJECT_PATH,id).pipe(map(
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

    createProject(project): Observable<any>{
        return this.apiService.post(this.PROJECT_PATH,project).pipe(map(
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

    updateProject(project): Observable<any>{
        return this.apiService.post(this.PROJECT_PATH+"/"+ project.id , project).pipe(map(
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

    deleteProject(id): Observable<any> {
        return this.apiService.post(this.PROJECT_PATH+'/delete/'+id).pipe(map(
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
