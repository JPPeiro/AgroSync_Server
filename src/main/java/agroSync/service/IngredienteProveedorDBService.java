package agroSync.service;

import agroSync.repository.IngredienteProveedorDBRepository;
import agroSync.repository.model.ComposicionPienso;
import agroSync.repository.model.IngredienteProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar los IngredienteProveedors en la base de datos.
 */
@Service
public class IngredienteProveedorDBService {

    @Autowired
    public IngredienteProveedorDBRepository repository;

    /**
     * Añade un nuevo IngredienteProveedor a la base de datos.
     *
     * @param ingredienteProveedor el IngredienteProveedor a añadir
     * @return el IngredienteProveedor añadido
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public IngredienteProveedor addIngredienteProveedor(IngredienteProveedor ingredienteProveedor) throws SQLException {
        return repository.addIngredienteProveedor(ingredienteProveedor);
    }

    /**
     * Actualiza un IngredienteProveedor en la base de datos.
     *
     * @param ingredienteProveedor el IngredienteProveedor a actualizar
     * @return el IngredienteProveedor actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public IngredienteProveedor updateIngredienteProveedor(IngredienteProveedor ingredienteProveedor) throws SQLException {
        return repository.updateIngredienteProveedor(ingredienteProveedor);
    }

    /**
     * Elimina un IngredienteProveedor de la base de datos.
     *
     * @param id el ID del Proveedor
     * @param idIngrediente el ID del ingrediente
     * @return el IngredienteProveedor eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public IngredienteProveedor deleteIngredienteProveedor(int id, int idIngrediente) throws SQLException {
        return repository.deleteIngredienteProveedor(id,idIngrediente);
    }

    /**
     * Obtiene todos los IngredienteProveedor de la base de datos.
     *
     * @return Lista de IngredienteProveedor
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<IngredienteProveedor> getAllIngredienteProveedor() throws SQLException {
        return repository.getAllIngredienteProveedor();
    }
}