package agroSync.service;

import agroSync.repository.ProveedorDBRepository;
import agroSync.repository.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar los Proveedores en la base de datos.
 */
@Service
public class ProveedorDBService {

    @Autowired
    public ProveedorDBRepository repository;

    /**
     * Añade un nuevo Proveedor a la base de datos.
     *
     * @param proveedor el Proveedor a añadir
     * @return el Proveedor añadido
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Proveedor addProveedor(Proveedor proveedor) throws SQLException {
        return repository.addProveedor(proveedor);
    }

    /**
     * Actualiza un Proveedor en la base de datos.
     *
     * @param proveedor el Proveedor a actualizar
     * @return el Proveedor actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Proveedor updateProveedor(Proveedor proveedor) throws SQLException {
        return repository.updateProveedor(proveedor);
    }

    /**
     * Elimina un Proveedor de la base de datos.
     *
     * @param id el ID del Proveedor a eliminar
     * @return el Proveedor eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Proveedor deleteProveedor(int id) throws SQLException {
        return repository.deleteProveedor(id);
    }

    /**
     * Obtiene todos los Proveedores de la base de datos.
     *
     * @return Lista de Proveedores
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Proveedor> getAllProveedors() throws SQLException {
        return repository.getAllProveedores();
    }
}