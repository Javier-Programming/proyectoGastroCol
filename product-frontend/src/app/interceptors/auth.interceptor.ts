import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

@Injectable()
// Interceptor para agregar el token de autenticación a las solicitudes HTTP
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  // Método para interceptar las solicitudes HTTP
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.getToken();

    // Si hay un token, clonar la solicitud y agregar el encabezado Authorization
    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
      return next.handle(authReq);
    }

    return next.handle(req);
  }
}
