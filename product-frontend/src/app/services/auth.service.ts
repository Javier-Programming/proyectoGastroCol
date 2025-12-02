import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
// Servicio de autenticación para manejar el inicio de sesión y el token
export class AuthService {
  // URL base de la API de autenticación
  private apiUrl = 'http://localhost:8080/api/auth';

  // Inyectar HttpClient y Router
  constructor(private http: HttpClient, private router: Router) {}

  // Método para iniciar sesión
  login(credentials: { correo: string; contrasena: string }) {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials);
  }

  // Método para guardar el token en el almacenamiento local
  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  // Método para obtener el token del almacenamiento local
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Método para verificar si el usuario está autenticado
  isLogged(): boolean {
    return !!this.getToken();
  }

  // Método para cerrar sesión
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
