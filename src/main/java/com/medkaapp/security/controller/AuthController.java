package com.medkaapp.security.controller;

import com.medkaapp.dto.Mensaje;
import com.medkaapp.security.dto.JwtDto;
import com.medkaapp.security.dto.LoginUsuario;
import com.medkaapp.security.dto.NuevoUsuario;
import com.medkaapp.security.entity.Rol;
import com.medkaapp.security.entity.Usuario;
import com.medkaapp.security.enums.RolNombre;
import com.medkaapp.security.jwt.JwtProvider;
import com.medkaapp.security.service.RolService;
import com.medkaapp.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Endponit para buscar usuario por nombre
     * @param usuario body
     * @return
     */
    @GetMapping("/findUsuario/{usuario}")
    public Usuario PacienteId(@PathVariable(name = "usuario") String usuario) {
        return usuarioService.getByNombreUsuario(usuario).get();
    }

    /**
     * Endponit para listar usuarios
     * @return
     */
    @GetMapping("/findUsuarios")
    public List<Usuario> UsuarioList() {
        return usuarioService.getListUsuarios();
    }

    /**
     * Endponit para listar usuario por id
     * @return
     */
    @GetMapping("/usuario/{id}")
    public Usuario usuarioId(@PathVariable(name = "id") Integer id) {
        Usuario usuarioId = new Usuario();
        usuarioId = usuarioService.getByUserId(id);
        System.out.println("Paciente seleccionado fue: " + usuarioId);
        return usuarioId;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Usuario user){

        Usuario usuarioSelect = new Usuario();

        usuarioSelect = usuarioService.getByUserId(id);
        usuarioSelect.setCelular(user.getCelular());
        usuarioSelect.setDireccion(user.getDireccion());
        usuarioSelect.setEdad(user.getEdad());
        usuarioSelect.setEmail(user.getEmail());

        usuarioService.updateUser(usuarioSelect);

        return new ResponseEntity(new Mensaje("usuario actualizado"), HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombres(), nuevoUsuario.getApellidos(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        nuevoUsuario.getEdad(), nuevoUsuario.getCedula(), nuevoUsuario.getDireccion(), nuevoUsuario.getCelular(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        if(nuevoUsuario.getRoles().contains("medico")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_MEDICO).get());
        } else {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
