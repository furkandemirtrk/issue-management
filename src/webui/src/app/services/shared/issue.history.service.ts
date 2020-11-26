import {Injectable} from "@angular/core";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
    providedIn: "root"
})
export class IssueHistoryService {
    private ISSUE_HISTORY_PATH = "/issue/history";

    constructor(private apiService: ApiService) {

    }

    getAll(): Observable<any> {
        return this.apiService.get(this.ISSUE_HISTORY_PATH+'/all',).pipe(map(
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
        return this.apiService.get(this.ISSUE_HISTORY_PATH,id).pipe(map(
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

    createProject(issueHistory): Observable<any>{
        return this.apiService.post(this.ISSUE_HISTORY_PATH,issueHistory).pipe(map(
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
        return this.apiService.post(this.ISSUE_HISTORY_PATH+'/delete/'+id).pipe(map(
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
