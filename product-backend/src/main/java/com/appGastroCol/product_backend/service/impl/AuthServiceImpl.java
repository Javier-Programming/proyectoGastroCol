package com.appGastroCol.product_backend.service.impl;

import com.appGastroCol.product_backend.dto.LoginRequest;
import com.appGastroCol.product_backend.dto.RegisterRequest;
import com.appGastroCol.product_backend.entity.Rol;
import com.appGastroCol.product_backend.entity.Usuario;
import com.appGastroCol.product_backend.repository.RolRepository;
import com.appGastroCol.product_backend.repository.UsuarioRepository;
import com.appGastroCol.product_backend.security.JwtUtil;
import com.appGastroCol.product_backend.service.AuthService;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository; // 👈 Necesario para leer el rol
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository, // 👈 se inyecta aquí
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil) {

        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository; // 👈 se asigna aquí
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasena()));

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return jwtUtil.generateToken(usuario.getCorreo());
    }

    @Override
    public Usuario register(RegisterRequest request) {

        // Verificar si existe correo
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // 🔥 Cargar rol desde la BD
        Rol rol = rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new RuntimeException("El rol especificado no existe"));

        // Crear usuario
        Usuario nuevo = new Usuario();
        nuevo.setNombre(request.getNombre());
        nuevo.setCorreo(request.getCorreo());
        nuevo.setContrasena(passwordEncoder.encode(request.getContrasena()));
        nuevo.setBiografia(request.getBiografia());
        nuevo.setUbicacion(request.getUbicacion());
        nuevo.setImagen(request.getImagen());
        nuevo.setRol(rol); // 👈 Ahora SÍ se usa una variable válida
        nuevo.setFechaCreacion(LocalDateTime.now());

        return usuarioRepository.save(nuevo);
    }
}
