package agroSync.repository;

import agroSync.repository.model.Ingrediente;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para acceder a los datos de los ingredientes en la base de datos.
 */
public interface IIngredienteRepository {

    /**
     * Agrega un nuevo ingrediente a la base de datos.
     *
     * @param ingrediente el ingrediente a agregar
     * @return el ingrediente agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Ingrediente addIngrediente(Ingrediente ingrediente) throws SQLException;

    /**
     * Actualiza un ingrediente existente en la base de datos.
     *
     * @param ingrediente el ingrediente a actualizar
     * @return el ingrediente actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Ingrediente updateIngrediente(Ingrediente ingrediente) throws SQLException;

    /**
     * Elimina un ingrediente de la base de datos.
     *
     * @param id el ID del ingrediente a eliminar
     * @return el ingrediente eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Ingrediente deleteIngrediente(int id) throws SQLException;

    /**
     * Obtiene todos los ingredientes de la base de datos.
     *
     * @return Lista de ingredientes
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<Ingrediente> getAllIngredientes() throws SQLException;
}