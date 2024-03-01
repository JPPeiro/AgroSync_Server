package agroSync.repository;

import agroSync.repository.intefaces.IPedidoIngredienteRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.PedidoIngrediente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoIngredienteDBRepository implements IPedidoIngredienteRepository {

    /**
     * Agrega un nuevo pedidoIngrediente a la base de datos.
     *
     * @param pedidoIngrediente el pedidoIngrediente a agregar
     * @return el pedidoIngrediente agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public PedidoIngrediente addPedidoIngrediente(PedidoIngrediente pedidoIngrediente) throws SQLException {
        String sql = " INSERT INTO PedidosIngredientes (proveedorId, ingredienteId, cantidad, coste) VALUES (?, ?, ?, ?)";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pedidoIngrediente.getProveedorId());
            ps.setInt(2, pedidoIngrediente.getIngredienteId());
            ps.setFloat(3, pedidoIngrediente.getCantidad());
            ps.setFloat(4, pedidoIngrediente.getCoste());
            ps.executeUpdate();
        }
        return pedidoIngrediente;
    }


    /**
     * Actualiza los datos de un pedidoIngrediente en la base de datos.
     *
     * @param pedidoIngrediente el pedidoIngrediente a actualizar
     * @return el pedidoIngrediente actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public PedidoIngrediente updatePedidoIngrediente(PedidoIngrediente pedidoIngrediente) throws SQLException {
        String sql = "UPDATE PedidosIngredientes SET id = ?, proveedorId = ?, ingredienteId = ?, cantidad = ?, coste = ? WHERE id = ?";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pedidoIngrediente.getId());
            ps.setInt(2, pedidoIngrediente.getProveedorId());
            ps.setInt(3, pedidoIngrediente.getIngredienteId());
            ps.setFloat(4, pedidoIngrediente.getCantidad());
            ps.setFloat(5, pedidoIngrediente.getCoste());
            ps.setInt(6, pedidoIngrediente.getId());
            ps.executeUpdate();
        }
        return pedidoIngrediente;
    }


    /**
     * Elimina un pedidoIngrediente de la base de datos.
     *
     * @param id el ID del pedidoIngrediente a eliminar
     * @return el pedidoIngrediente eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public PedidoIngrediente deletePedidoIngrediente(int id) throws SQLException {
        PedidoIngrediente pedidoIngrediente = getPedidoIngredienteById(id);
        String sql = "DELETE FROM PedidosIngredientes WHERE id = ?";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        return pedidoIngrediente;
    }

    /**
     * Obtiene todos los pedidosIngredientes de la base de datos.
     *
     * @return Lista de pedidosIngredientes
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<PedidoIngrediente> getAllPedidosIngredientes() throws SQLException {
        ArrayList<PedidoIngrediente> pedidoIngredientesDB = new ArrayList<>();
        String query = "{ call obtener_pedidos() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                pedidoIngredientesDB.add(PedidoIngrediente.builder()
                        .id(rs.getInt(1))
                        .proveedorId(rs.getInt(2))
                        .ingredienteId(rs.getInt(3))
                        .cantidad(rs.getFloat(4))
                        .coste(rs.getFloat(5))
                        .build());
            }
        }

        return pedidoIngredientesDB;
    }

    /**
     * Obtiene un pedidoIngrediente de la base de datos por su ID.
     *
     * @param id el ID del pedidoIngrediente
     * @return el pedidoIngrediente encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public PedidoIngrediente getPedidoIngredienteById(int id) throws SQLException {
        PedidoIngrediente pedidoIngrediente = null;
        String query = "SELECT * FROM PedidosIngredientes WHERE id=" + id;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                pedidoIngrediente = pedidoIngrediente.builder()
                        .id(rs.getInt(1))
                        .proveedorId(rs.getInt(2))
                        .ingredienteId(rs.getInt(3))
                        .cantidad(rs.getFloat(4))
                        .coste(rs.getFloat(5))
                        .build();
            }
        }

        return pedidoIngrediente;
    }

}