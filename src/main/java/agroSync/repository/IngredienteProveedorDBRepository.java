package agroSync.repository;

import agroSync.repository.intefaces.IIngredienteProveedorRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.IngredienteProveedor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredienteProveedorDBRepository implements IIngredienteProveedorRepository {

    /**
     * Agrega una nuevo ingredienteProveedor a la base de datos.
     *
     * @param ingredienteProveedor el ingredienteProveedor a agregar
     * @return el ingredienteProveedor agregada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public IngredienteProveedor addIngredienteProveedor(IngredienteProveedor ingredienteProveedor) throws SQLException {
        String sql = " INSERT INTO IngredientesProveedor (idProveedor, idIngrediente, precio) VALUES ( ?, ?, ?)";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ingredienteProveedor.getIdProveedor());
            ps.setInt(2, ingredienteProveedor.getIdIngrediente());
            ps.setFloat(3, ingredienteProveedor.getPrecio());
            ps.executeUpdate();
        }
        return ingredienteProveedor;
    }

    /**
     * Actualiza los datos de un ingredienteProveedor en la base de datos.
     *
     * @param ingredienteProveedor el ingredienteProveedor a actualizar
     * @return el ingredienteProveedor actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public IngredienteProveedor updateIngredienteProveedor(IngredienteProveedor ingredienteProveedor) throws SQLException {
        String sql = "UPDATE IngredientesProveedor SET idProveedor = ?, idIngrediente = ?, precio = ? WHERE idProveedor = ? AND idIngrediente = ?";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ingredienteProveedor.getIdProveedor());
            ps.setInt(2, ingredienteProveedor.getIdIngrediente());
            ps.setFloat(3, ingredienteProveedor.getPrecio());
            ps.setInt(4, ingredienteProveedor.getIdProveedor());
            ps.setInt(5, ingredienteProveedor.getIdIngrediente());
            ps.executeUpdate();
        }
        return ingredienteProveedor;
    }

    /**
     * Elimina un ingredienteProveedor de la base de datos.
     *
     * @param idProveedor el ID del pienso
     * @param idIngrediente el ID del ingrediente
     * @return el ingredienteProveedor eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public IngredienteProveedor deleteIngredienteProveedor (int idProveedor, int idIngrediente) throws SQLException {
        IngredienteProveedor ingredienteProveedor = getingredienteProveedorById(idProveedor,idIngrediente);
        String sql = "DELETE FROM IngredientesProveedor WHERE idProveedor = ? AND idIngrediente = ?";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ps.setInt(2, idIngrediente);
            ps.executeUpdate();
        }
        return ingredienteProveedor;
    }

    /**
     * Obtiene todos los ingredienteProveedor de la base de datos.
     *
     * @return Lista de los ingredienteProveedor
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<IngredienteProveedor> getAllIngredienteProveedor() throws SQLException {
        ArrayList<IngredienteProveedor> ingredienteProveedorDB = new ArrayList<>();
        String query = "{ call obtener_ingredientes_proveedor() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ingredienteProveedorDB.add(IngredienteProveedor.builder()
                        .idProveedor(rs.getInt(1))
                        .idIngrediente(rs.getInt(2))
                        .precio(rs.getFloat(3))
                        .build());
            }
        }

        return ingredienteProveedorDB;
    }

    /**
     * Obtiene un ingredienteProveedor de la base de datos por su ID.
     *
     * @param id el ID del pienso
     * @param idIngrediente el ID del ingrediente
     * @return el ingredienteProveedor encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public IngredienteProveedor getingredienteProveedorById(int id, int idIngrediente) throws SQLException {
        IngredienteProveedor ingredienteProveedor = null;
        String query = "SELECT * FROM IngredientesProveedor WHERE idPienso=" + id + " AND idIngrediente=" + idIngrediente;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ingredienteProveedor = IngredienteProveedor.builder()
                        .idProveedor(rs.getInt(1))
                        .idIngrediente(rs.getInt(2))
                        .precio(rs.getFloat(3))
                        .build();
            }
        }

        return ingredienteProveedor;
    }

}