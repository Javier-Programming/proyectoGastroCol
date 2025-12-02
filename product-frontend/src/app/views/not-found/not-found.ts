import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-not-found',
  imports: [RouterLink, CommonModule],
  standalone: true,
  templateUrl: './not-found.html',
  styleUrl: './not-found.css',
})
export class NotFound {}
