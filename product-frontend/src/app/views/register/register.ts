import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [RouterLink],
  standalone: true,
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {}
