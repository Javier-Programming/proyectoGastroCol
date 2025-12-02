import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive, CommonModule],
  standalone: true,
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
// Clase del componente de la barra de navegación
export class Navbar {
  // Inyectar AuthService
  constructor(private authService: AuthService) {}

  // Getter para verificar si el usuario está autenticado
  get isLogged(): boolean {
    return this.authService.isLogged();
  }

  // Método para cerrar sesión
  logout() {
    this.authService.logout();
  }
}
