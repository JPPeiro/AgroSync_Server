package agroSync.service;
import agroSync.repository.IngredienteDBRepository;
import agroSync.repository.model.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar los ingredientes en la base de datos.
 */
@Service
public class IngredienteDBService {

    @Autowired
    public IngredienteDBRepository repository;

    /**
     * Añade un nuevo ingrediente a la base de datos.
     *
     * @param ingrediente el ingrediente a añadir
     * @return el ingrediente añadido
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Ingrediente addIngrediente(Ingrediente ingrediente) throws SQLException {
        return repository.addIngrediente(ingrediente);
    }

    /**
     * Actualiza un ingrediente en la base de datos.
     *
     * @param ingrediente el ingrediente a actualizar
     * @return el ingrediente actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Ingrediente updateIngrediente(Ingrediente ingrediente) throws SQLException {
        return repository.updateIngrediente(ingrediente);
    }

    /**
     * Elimina un ingrediente de la base de datos.
     *
     * @param id el ID del ingrediente a eliminar
     * @return el ingrediente eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Ingrediente deleteIngrediente(int id) throws SQLException {
        return repository.deleteIngrediente(id);
    }

    /**
     * Obtiene todos los ingredientes de la base de datos.
     *
     * @return Lista de ingredientes
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Ingrediente> getAllIngredientes() throws SQLException {
        return repository.getAllIngredientes();
    }
}