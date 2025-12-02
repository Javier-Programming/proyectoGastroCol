import { Component, AfterViewInit } from '@angular/core';
import { Fancybox } from '@fancyapps/ui';

@Component({
  selector: 'app-home',
  imports: [],
  standalone: true,
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements AfterViewInit {
  ngAfterViewInit() {
    Fancybox.bind('[data-fancybox="gallery"]', {});
  }
}
