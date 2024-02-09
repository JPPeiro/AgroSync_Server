package agroSync.repository.intefaces;

import agroSync.repository.model.IngredienteProveedor;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para acceder a los datos de los IngredienteProveedor en la base de datos.
 */
public interface IIngredienteProveedorRepository {

    /**
     * Agrega un nuevo IngredienteProveedor a la base de datos.
     *
     * @param IngredienteProveedor el IngredienteProveedor a agregar
     * @return el IngredienteProveedor agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    IngredienteProveedor addIngredienteProveedor(IngredienteProveedor IngredienteProveedor) throws SQLException;

    /**
     * Actualiza un IngredienteProveedor existente en la base de datos.
     *
     * @param IngredienteProveedor el IngredienteProveedor a actualizar
     * @return el IngredienteProveedor actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    IngredienteProveedor updateIngredienteProveedor(IngredienteProveedor IngredienteProveedor) throws SQLException;

    /**
     * Elimina un IngredienteProveedor de la base de datos.
     *
     * @param id el ID del IngredienteProveedor a eliminar
     * @return el IngredienteProveedor eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    IngredienteProveedor deleteIngredienteProveedor(int id, int idIngrediente) throws SQLException;

    /**
     * Obtiene todos los IngredienteProveedor de la base de datos.
     *
     * @return Lista de IngredienteProveedor
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<IngredienteProveedor> getAllIngredienteProveedor() throws SQLException;
}