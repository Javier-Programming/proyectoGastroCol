import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Login } from './views/login/login';
import { Register } from './views/register/register';
import { Home } from './views/home/home';
import { Profile } from './views/profile/profile';
import { NotFound } from './views/not-found/not-found';
import { MainLayout } from './layouts/main-layout/main-layout';
import { AuthLayout } from './layouts/auth-layout/auth-layout';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  // Rutas públicas
  {
    path: '',
    component: AuthLayout,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: Login },
      { path: 'register', component: Register },
    ],
  },

  // Rutas privadas
  {
    path: '',
    component: MainLayout,
    canActivate: [AuthGuard],
    children: [
      { path: 'home', component: Home },
      { path: 'profile', component: Profile },
      { path: 'NotFound', component: NotFound }
    ],
  },

  // Wildcard
  // { path: '**', redirectTo: 'login' },
  { path: '**', redirectTo: 'NotFound' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
