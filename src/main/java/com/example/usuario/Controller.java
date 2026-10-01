package com.example.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
@Autowired
private UsuarioService usuarioService;
// 1. GET - Obtener todos los usuarios
@GetMapping
public ResponseEntity<List<UsuarioController>> obtenerTodos() {
List<UsuarioController> usuarios = usuarioService.obtenerTodos();
return ResponseEntity.ok(usuarios);
}
// 2. GET - Obtener un usuario por su ID
@GetMapping("/{id}")
public ResponseEntity<UsuarioController> obtenerPorId(@PathVariable Long id) {
Optional<UsuarioController> usuario = usuarioService.obtenerPorId(id);
return usuario.map(ResponseEntity::ok)
.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
// 3. POST - Crear un nuevo usuario
@PostMapping
public ResponseEntity<UsuarioController> crearUsuario(@RequestBody UsuarioController usuario) {
UsuarioController nuevoUsuario = usuarioService.guardarUsuario(usuario);
return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
}
// 4. PUT - Actualizar un usuario existente
@PutMapping("/{id}")
public ResponseEntity<UsuarioController> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioController
usuarioDetalles) {
try {
UsuarioController usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDetalles);
return ResponseEntity.ok(usuarioActualizado);
} catch (RuntimeException e) {
return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
}
// 5. DELETE - Eliminar un usuario por ID
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
try {
usuarioService.eliminarUsuario(id);
return ResponseEntity.noContent().build(); // Retorna 204 No Content al eliminar con éxito
} catch (RuntimeException e) {
return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
}
}
