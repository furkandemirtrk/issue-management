import {User} from "./user.model";

export class Project {
    id: number;
    projectName: string;
    projectCode: string;
    manager: User;
}
