import { Routes } from '@angular/router';
import { UserComponent } from './view/user/user.component/user.component';


export const routes: Routes = [
    { path: '', redirectTo: 'users',pathMatch:'full' },
    { path: 'users', component: UserComponent}//guards
];
