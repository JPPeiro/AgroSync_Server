package agroSync.controller;

import agroSync.repository.model.Proveedor;
import agroSync.service.ProveedorDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProveedorDBController {
    @Autowired
    private ProveedorDBService service;

    /**
     * Maneja la solicitud POST para agregar un nuevo Proveedor.
     *
     * @param proveedor el objeto Proveedor que se va a agregar
     * @return una ResponseEntity que contiene el Proveedor agregado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/proveedores/")
    public ResponseEntity<?> addProveedor(@RequestBody Proveedor proveedor) {
        try{
            Proveedor p = service.addProveedor(proveedor);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud PUT para actualizar un Proveedor existente.
     *
     * @param proveedor el objeto Proveedor actualizado
     * @return una ResponseEntity que contiene el Proveedor actualizado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/proveedores/")
    public ResponseEntity<?> updateProveedor(@RequestBody Proveedor proveedor){
        try{
            Proveedor p = service.updateProveedor(proveedor);
            return new ResponseEntity<>(p,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud DELETE para eliminar un Proveedor.
     *
     * @param id el ID del Proveedor a eliminar
     * @return una ResponseEntity que contiene el Proveedor eliminado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("proveedores/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deleteProveedor(id),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todos los Proveedores.
     *
     * @return una ResponseEntity que contiene una lista de Proveedores si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/proveedores/")
    public ResponseEntity<?> getAllProveedores(){
        try {
            return new ResponseEntity<>(service.getAllProveedors(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}