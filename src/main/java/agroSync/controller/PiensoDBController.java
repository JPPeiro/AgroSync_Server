package agroSync.controller;
import agroSync.repository.model.Pienso;
import agroSync.service.PiensoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PiensoDBController {

    @Autowired
    private PiensoDBService service;

    /**
     * Maneja la solicitud POST para agregar un nuevo pienso.
     *
     * @param pienso el objeto pienso que se va a agregar
     * @return una ResponseEntity que contiene el pienso agregado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PostMapping("/piensos/")
    public ResponseEntity<?> addpienso(@RequestBody Pienso pienso) {
        try{
            Pienso p = service.addPienso(pienso);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Maneja la solicitud POST para agregar pienso y restar stock de los ingredientes.
     *
     * @param requestBody
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<?> agregarPienso(@RequestBody Map<String, String> requestBody) {
        String piensoId = requestBody.get("piensoId");
        String cantidadTotal = requestBody.get("cantidadTotal");

        try {
            service.agregarPienso(piensoId, cantidadTotal);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  Comprueba si hay stock suficiente de ingredientes
     * @param requestBody
     * @return
     */
    @PostMapping("/verificar/")
    public ResponseEntity<?> verificarStock(@RequestBody Map<String, Integer> requestBody) {
        int piensoId = requestBody.get("piensoId");
        int cantidadTotal = requestBody.get("cantidadTotal");

        try {
            Map<String, Object> result = service.verificarStock(piensoId, cantidadTotal);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Maneja la solicitud PUT para actualizar un pienso existente.
     *
     * @param pienso el objeto pienso actualizado
     * @return una ResponseEntity que contiene el pienso actualizado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @PutMapping("/piensos/")
    public ResponseEntity<?> updatepienso(@RequestBody Pienso pienso){
        try{
            Pienso p = service.updatePienso(pienso);
            return new ResponseEntity<>(p,HttpStatus.OK);

        }catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud DELETE para eliminar un pienso.
     *
     * @param id el ID del pienso a eliminar
     * @return una ResponseEntity que contiene el pienso eliminado si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @DeleteMapping("piensos/{id}")
    public ResponseEntity<?> deletepienso(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deletePienso(id),HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Maneja la solicitud GET para obtener todos los piensos.
     *
     * @return una ResponseEntity que contiene una lista de piensos si la solicitud es exitosa,
     *         o un mapa de respuesta con el código de error y el mensaje de error en caso de fallo.
     */
    @GetMapping("/piensos/")
    public ResponseEntity<?> getAllpiensos(){
        try {
            return new ResponseEntity<>(service.getAllPiensos(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}