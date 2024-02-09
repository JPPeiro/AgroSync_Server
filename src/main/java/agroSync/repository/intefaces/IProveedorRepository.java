package agroSync.repository.intefaces;

import agroSync.repository.model.Proveedor;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para acceder a los datos de los Proveedores en la base de datos.
 */
public interface IProveedorRepository {

    /**
     * Agrega un nuevo Proveedor a la base de datos.
     *
     * @param Proveedor el Proveedor a agregar
     * @return el Proveedor agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Proveedor addProveedor(Proveedor Proveedor) throws SQLException;

    /**
     * Actualiza un Proveedor existente en la base de datos.
     *
     * @param Proveedor el Proveedor a actualizar
     * @return el Proveedor actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Proveedor updateProveedor(Proveedor Proveedor) throws SQLException;

    /**
     * Elimina un Proveedor de la base de datos.
     *
     * @param id el ID del Proveedor a eliminar
     * @return el Proveedor eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Proveedor deleteProveedor(int id) throws SQLException;

    /**
     * Obtiene todos los Proveedores de la base de datos.
     *
     * @return Lista de Proveedores
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<Proveedor> getAllProveedores() throws SQLException;
}