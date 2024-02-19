package agroSync.repository.intefaces;

import agroSync.repository.model.PedidoIngrediente;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para acceder a los datos de los pedidoIngredientes en la base de datos.
 */
public interface IPedidoIngredienteRepository {

    /**
     * Agrega un nuevo pedidoIngrediente a la base de datos.
     *
     * @param pedidoIngrediente el pedidoIngrediente a agregar
     * @return el pedidoIngrediente agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    PedidoIngrediente addPedidoIngrediente(PedidoIngrediente pedidoIngrediente) throws SQLException;

    /**
     * Actualiza un pedidoIngrediente existente en la base de datos.
     *
     * @param pedidoIngrediente el pedidoIngrediente a actualizar
     * @return el pedidoIngrediente actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    PedidoIngrediente updatePedidoIngrediente(PedidoIngrediente pedidoIngrediente) throws SQLException;

    /**
     * Elimina un pedidoIngrediente de la base de datos.
     *
     * @param id el ID del pedidoIngrediente a eliminar
     * @return el pedidoIngrediente eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    PedidoIngrediente deletePedidoIngrediente(int id) throws SQLException;

    /**
     * Obtiene todos los pedidosIngredientes de la base de datos.
     *
     * @return Lista de pedidosIngredientes
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<PedidoIngrediente> getAllPedidosIngredientes() throws SQLException;
}