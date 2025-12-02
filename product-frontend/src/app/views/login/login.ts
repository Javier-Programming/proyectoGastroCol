import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule, CommonModule],
  standalone: true,
  templateUrl: './login.html',
  styleUrl: './login.css',
})

// Clase del componente de inicio de sesión
export class Login {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  // Inyectar AuthService y Router
  constructor(private authService: AuthService, private router: Router) {}

  login() {
    // console.log('Iniciando sesión...');
    // Preparar datos de inicio de sesión
    const data = {
      correo: this.email,
      contrasena: this.password,
    };

    // Llamar al servicio de autenticación
    this.authService.login(data).subscribe({
      next: (res) => {
        this.authService.saveToken(res.token);
        this.router.navigate(['/home']);
      },
      error: () => {
        this.errorMessage = 'Credenciales incorrectas';
      },
    });
  }
}
