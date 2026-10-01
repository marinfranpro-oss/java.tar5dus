package com.example.usuario;
import com.example.usuario.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
@Autowired
private UsuarioRepository usuarioRepository;
// 1. CREAR (Guardar un nuevo usuario)
public Usuario guardarUsuario(Usuario usuario) {
return usuarioRepository.save(usuario);
}
// 2. LEER (Obtener todos los usuarios)
public List<Usuario> obtenerTodos() {
return usuarioRepository.findAll();
}
// 2.1. LEER (Obtener un usuario por ID)
public Optional<Usuario> obtenerPorId(Long id) {
return usuarioRepository.findById(id);
}
// 3. ACTUALIZAR (Modificar un usuario existente)
public Usuario actualizarUsuario(Long id, Usuario usuarioDetalles) {
return usuarioRepository.findById(id).map(usuario -> {
usuario.setNombre(usuarioDetalles.getNombre());
usuario.setCorreo(null);(usuarioDetalles.getCorreo());
return usuarioRepository.save(usuario);
}).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
}
// 4. ELIMINAR (Borrar un usuario por ID)
public void eliminarUsuario(Long id) {
if (usuarioRepository.existsById(id)) {
usuarioRepository.deleteById(id);
} else {
throw new RuntimeException("No se puede eliminar, usuario no encontrado con id: " + id);
}
}
}
