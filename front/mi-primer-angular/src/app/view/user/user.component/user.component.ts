import { Component } from '@angular/core';
/* Librerias a importar */
import { UserService } from '../../../services/user.service/user.service'; 
import { CommonModule, JsonPipe } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-user.component',
  imports: [JsonPipe, CommonModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
//signals


  users: any[] = [];

  constructor(private userServi: UserService, private cdt: ChangeDetectorRef) {
    this.userServi.getUsers().subscribe(data => {
      this.users = data;
      console.log(this.users);
      this.cdt.detectChanges();
    });
  };

}
