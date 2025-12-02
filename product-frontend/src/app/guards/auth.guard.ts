import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
// Guardia de autenticación para proteger rutas privadas
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  // Método para determinar si la ruta puede ser activada
  canActivate(): boolean {
    // Si el usuario no está autenticado, redirigir al login
    if (!this.authService.isLogged()) {
      this.router.navigate(['/login']);
      return false;
    }
    // Si está autenticado, permitir el acceso
    return true;
  }
}
