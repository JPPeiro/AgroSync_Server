package agroSync.repository;

import agroSync.repository.intefaces.IIngredienteRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.Ingrediente;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredienteDBRepository implements IIngredienteRepository {

    /**
     * Agrega un nuevo ingrediente a la base de datos.
     *
     * @param ingrediente el ingrediente a agregar
     * @return el ingrediente agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Ingrediente addIngrediente(Ingrediente ingrediente) throws SQLException {
        String sql = " INSERT INTO Ingredientes (Nombre, CantidadInventario) VALUES ( ?, ?)";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ingrediente.getNombre());
            ps.setString(2, ingrediente.getCantidad());
            ps.execute();
        }
        return ingrediente;
    }

    /**
     * Actualiza los datos de un ingrediente en la base de datos.
     *
     * @param ingrediente el ingrediente a actualizar
     * @return el ingrediente actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Ingrediente updateIngrediente(Ingrediente ingrediente) throws SQLException {
        String sql = "UPDATE Ingredientes SET Nombre = ?, CantidadInventario = ? WHERE id = ?";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ingrediente.getNombre());
            ps.setString(2, ingrediente.getCantidad());
            ps.setInt(3, ingrediente.getId());
            ps.execute();
        }
        return ingrediente;
    }

    /**
     * Elimina un ingrediente de la base de datos.
     *
     * @param id el ID del ingrediente a eliminar
     * @return el ingrediente eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Ingrediente deleteIngrediente(int id) throws SQLException {
        Ingrediente ingrediente = getIngredienteById(id);
        String sql = "DELETE FROM Ingredientes WHERE id = ?";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return ingrediente;
    }

    /**
     * Obtiene todos los ingredientes de la base de datos.
     *
     * @return Lista de ingredientes
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Ingrediente> getAllIngredientes() throws SQLException {
        ArrayList<Ingrediente> ingredientesDB = new ArrayList<>();
        String query = "{ call obtener_ingredientes() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ingredientesDB.add(Ingrediente.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .cantidad(rs.getString(3))
                        .build());
            }
        }

        return ingredientesDB;
    }

    /**
     * Obtiene un ingrediente de la base de datos por su ID.
     *
     * @param id el ID del ingrediente
     * @return el ingrediente encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Ingrediente getIngredienteById(int id) throws SQLException {
        Ingrediente ingrediente = null;
        String query = "SELECT * FROM Ingredientes WHERE id=" + id;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ingrediente = Ingrediente.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .cantidad(rs.getString(3))
                        .build();
            }
        }

        return ingrediente;
    }

}