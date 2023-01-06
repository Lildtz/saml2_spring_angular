import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-xyz',
  templateUrl: './xyz.component.html',
  styleUrls: ['./xyz.component.scss'],
})
export class XyzComponent implements OnInit {
  user: User[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<User[]>(
      'http://localhost:8080/api/user'
    ).subscribe({
      next: (v) => {
        this.user = v;
      },
      error: (e) => console.log(e),
      complete: () => console.log('done')
    })
  }
}

export interface User {
  id: number;
  nome: string;
}
