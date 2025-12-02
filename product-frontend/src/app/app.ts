import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthInterceptor } from './interceptors/auth.interceptor';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css',
  // Configurar el interceptor de autenticación
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useValue: AuthInterceptor,
      multi: true,
    },
  ],
})
export class App {
  protected readonly title = signal('GastroCol - Social');
}
