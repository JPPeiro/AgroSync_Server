package agroSync.controller;

import agroSync.repository.model.PedidoIngrediente;
import agroSync.service.PedidoIngredienteDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PedidoIngredienteDBController {
    @Autowired
    private PedidoIngredienteDBService service;

    /**
     * Maneja la solicitud POST para agregar un nuevo pedidoIngrediente.
     *
     * @param pedidoIngrediente el objeto pedidoIngrediente que se va a agregar
     * @return una ResponseEntity que contiene el pedidoIngrediente agregado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/pedidosIngredientes/")
    public ResponseEntity<?> addPedidoIngrediente(@RequestBody PedidoIngrediente pedidoIngrediente) {
        try{
            PedidoIngrediente u = service.addPedidoIngrediente(pedidoIngrediente);
            return new ResponseEntity<>(u,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud PUT para actualizar un pedidoIngrediente existente.
     *
     * @param pedidoIngrediente el objeto pedidoIngrediente actualizado
     * @return una ResponseEntity que contiene el pedidoIngrediente actualizado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/pedidosIngredientes/")
    public ResponseEntity<?> updatePedidoIngrediente(@RequestBody PedidoIngrediente pedidoIngrediente){
        try{
            PedidoIngrediente u = service.updatePedidoIngrediente(pedidoIngrediente);
            return new ResponseEntity<>(u,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud DELETE para eliminar un pedidoIngrediente.
     *
     * @param id el ID del pedidoIngrediente a eliminar
     * @return una ResponseEntity que contiene el pedidoIngrediente eliminado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("pedidosIngredientes/{id}")
    public ResponseEntity<?> deletePedidoIngrediente(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deletePedidoIngrediente(id),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todos los pedidoIngredientes.
     *
     * @return una ResponseEntity que contiene una lista de pedidoIngredientes si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/pedidosIngredientes/")
    public ResponseEntity<?> getAllPedidosIngredientes(){
        try {
            return new ResponseEntity<>(service.getAllPedidosIngredientes(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}