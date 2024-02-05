package agroSync.controller;

import agroSync.repository.model.ComposicionPienso;
import agroSync.service.ComposicionDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ComposicionDBController {
    @Autowired
    private ComposicionDBService service;

    /**
     * Maneja la solicitud POST para agregar una nueva composicion.
     *
     * @param composicion el objeto composicion que se va a agregar
     * @return una ResponseEntity que contiene la composicion agregada si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/composiciones/")
    public ResponseEntity<?> addcomposicion(@RequestBody ComposicionPienso composicion) {
        try{
            ComposicionPienso u = service.addcomposicion(composicion);
            return new ResponseEntity<>(u,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud PUT para actualizar una composicion existente.
     *
     * @param composicion el objeto composicion actualizado
     * @return una ResponseEntity que contiene la composicion actualizada si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/composiciones/")
    public ResponseEntity<?> updatecomposicion(@RequestBody ComposicionPienso composicion){
        try{
            ComposicionPienso u = service.updateComposicion(composicion);
            return new ResponseEntity<>(u,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud DELETE para eliminar una composicion.
     *
     * @param id el ID del composicion a eliminar
     * @return una ResponseEntity que contiene la composicion eliminada si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("composiciones/{id}/{idIngrediente}")
    public ResponseEntity<?> deleteComposicion(@PathVariable("id") int id, @PathVariable("idIngrediente") int idIngrediente) {
        try {
            return new ResponseEntity<>(service.deleteComposicion(id,idIngrediente),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todas las composiciones.
     *
     * @return una ResponseEntity que contiene una lista de las composiciones si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/composiciones/")
    public ResponseEntity<?> getAllcomposicions(){
        try {
            return new ResponseEntity<>(service.getAllcomposicions(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}