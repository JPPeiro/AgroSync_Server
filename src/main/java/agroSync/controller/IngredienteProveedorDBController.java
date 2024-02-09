package agroSync.controller;

import agroSync.repository.model.IngredienteProveedor;
import agroSync.service.IngredienteProveedorDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IngredienteProveedorDBController {
    @Autowired
    private IngredienteProveedorDBService service;

    /**
     * Maneja la solicitud POST para agregar una nueva IngredienteProveedor.
     *
     * @param ingredienteProveedor el objeto IngredienteProveedor que se va a agregar
     * @return una ResponseEntity que contiene la IngredienteProveedor agregada si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/ingredienteproveedor/")
    public ResponseEntity<?> addIngredienteProveedor(@RequestBody IngredienteProveedor ingredienteProveedor) {
        try{
            IngredienteProveedor iu = service.addIngredienteProveedor(ingredienteProveedor);
            return new ResponseEntity<>(iu,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud PUT para actualizar una IngredienteProveedor existente.
     *
     * @param ingredienteProveedor el objeto IngredienteProveedor actualizado
     * @return una ResponseEntity que contiene la IngredienteProveedor actualizada si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/ingredienteproveedor/")
    public ResponseEntity<?> updateIngredienteProveedor(@RequestBody IngredienteProveedor ingredienteProveedor){
        try{
            IngredienteProveedor iu = service.updateIngredienteProveedor(ingredienteProveedor);
            return new ResponseEntity<>(iu,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud DELETE para eliminar un IngredienteProveedor.
     *
     * @param id el ID del IngredienteProveedor a eliminar
     * @return una ResponseEntity que contiene el IngredienteProveedor eliminada si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("ingredienteproveedor/{id}/{idIngrediente}")
    public ResponseEntity<?> deleteIngredienteProveedor(@PathVariable("id") int id, @PathVariable("idIngrediente") int idIngrediente) {
        try {
            return new ResponseEntity<>(service.deleteIngredienteProveedor(id,idIngrediente),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todas los IngredienteProveedor.
     *
     * @return una ResponseEntity que contiene una lista de las IngredienteProveedor si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/ingredienteproveedor/")
    public ResponseEntity<?> getAllIngredienteProveedor(){
        try {
            return new ResponseEntity<>(service.getAllIngredienteProveedor(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}