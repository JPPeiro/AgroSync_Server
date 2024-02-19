package agroSync.service;

import agroSync.repository.PedidoIngredienteDBRepository;
import agroSync.repository.model.PedidoIngrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar los pedidosIngredientes en la base de datos.
 */
@Service
public class PedidoIngredienteDBService {

    @Autowired
    public PedidoIngredienteDBRepository repository;

    /**
     * Añade un nuevo pedidoIngrediente a la base de datos.
     *
     * @param pedidoIngrediente el pedidoIngrediente a añadir
     * @return el pedidoIngrediente añadido
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public PedidoIngrediente addPedidoIngrediente(PedidoIngrediente pedidoIngrediente) throws SQLException {
        return repository.addPedidoIngrediente(pedidoIngrediente);
    }

    /**
     * Actualiza un pedidoIngrediente en la base de datos.
     *
     * @param pedidoIngrediente el pedidoIngrediente a actualizar
     * @return el pedidoIngrediente actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public PedidoIngrediente updatePedidoIngrediente(PedidoIngrediente pedidoIngrediente) throws SQLException {
        return repository.updatePedidoIngrediente(pedidoIngrediente);
    }

    /**
     * Elimina un pedidoIngrediente de la base de datos.
     *
     * @param id el ID del pedidoIngrediente a eliminar
     * @return el pedidoIngrediente eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public PedidoIngrediente deletePedidoIngrediente(int id) throws SQLException {
        return repository.deletePedidoIngrediente(id);
    }

    /**
     * Obtiene todos los pedidosIngredientes de la base de datos.
     *
     * @return Lista de pedidosIngredientes
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<PedidoIngrediente> getAllPedidosIngredientes() throws SQLException {
        return repository.getAllPedidosIngredientes();
    }
}