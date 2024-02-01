package agroSync.controller;

import agroSync.repository.model.Usuario;
import agroSync.service.UsuarioDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioDBController {
    @Autowired
    private UsuarioDBService service;

    /**
     * Maneja la solicitud POST para agregar un nuevo usuario.
     *
     * @param usuario el objeto Usuario que se va a agregar
     * @return una ResponseEntity que contiene el usuario agregado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/usuarios/")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try{
            Usuario u = service.addUsuario(usuario);
            return new ResponseEntity<>(u,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud PUT para actualizar un usuario existente.
     *
     * @param usuario el objeto Usuario actualizado
     * @return una ResponseEntity que contiene el usuario actualizado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/usuarios/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario){
        try{
            Usuario u = service.updateUsuario(usuario);
            return new ResponseEntity<>(u,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud DELETE para eliminar un usuario.
     *
     * @param id el ID del usuario a eliminar
     * @return una ResponseEntity que contiene el usuario eliminado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deleteUsuario(id),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todos los usuarios.
     *
     * @return una ResponseEntity que contiene una lista de usuarios si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/usuarios/")
    public ResponseEntity<?> getAllUsuarios(){
        try {
            return new ResponseEntity<>(service.getAllUsuarios(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}