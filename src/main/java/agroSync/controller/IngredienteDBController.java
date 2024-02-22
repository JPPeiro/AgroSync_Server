package agroSync.controller;

import agroSync.repository.model.Ingrediente;
import agroSync.service.IngredienteDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IngredienteDBController {
    @Autowired
    private IngredienteDBService service;

    /**
     * Maneja la solicitud POST para agregar un nuevo ingrediente.
     *
     * @param ingrediente el objeto ingrediente que se va a agregar
     * @return una ResponseEntity que contiene el ingrediente agregado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/ingredientes/")
    public ResponseEntity<?> addingrediente(@RequestBody Ingrediente ingrediente) {
        try{
            Ingrediente i = service.addIngrediente(ingrediente);
            return new ResponseEntity<>(i,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud PUT para actualizar un ingrediente existente.
     *
     * @param ingrediente el objeto ingrediente actualizado
     * @return una ResponseEntity que contiene el ingrediente actualizado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/ingredientes/")
    public ResponseEntity<?> updateingrediente(@RequestBody Ingrediente ingrediente){
        try{
            Ingrediente i = service.updateIngrediente(ingrediente);
            return new ResponseEntity<>(i,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ingredientesCantidad/")
    public ResponseEntity<?> updateCantidadIngrediente(@RequestParam int id, @RequestParam String cantidad) {
        try {
            Ingrediente i = service.updateCantidadIngrediente(id, cantidad);
            return new ResponseEntity<>(i, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /**
     * Maneja la solicitud DELETE para eliminar un ingrediente.
     *
     * @param id el ID del ingrediente a eliminar
     * @return una ResponseEntity que contiene el ingrediente eliminado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("ingredientes/{id}")
    public ResponseEntity<?> deleteingrediente(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deleteIngrediente(id),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todos los ingredientes.
     *
     * @return una ResponseEntity que contiene una lista de ingredientes si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/ingredientes/")
    public ResponseEntity<?> getAllingredientes(){
        try {
            return new ResponseEntity<>(service.getAllIngredientes(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}