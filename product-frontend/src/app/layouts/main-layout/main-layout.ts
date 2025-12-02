import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from '../../components/navbar/navbar';

@Component({
  standalone: true,
  imports: [RouterOutlet, Navbar],
  templateUrl: './main-layout.html',
})
export class MainLayout {}
