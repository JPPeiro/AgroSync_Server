package agroSync.repository;

import agroSync.repository.intefaces.IComposicionRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.ComposicionPienso;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComposicionDBRepository implements IComposicionRepository {

    /**
     * Agrega una nueva composicion a la base de datos.
     *
     * @param composicion la composicion a agregar
     * @return la composicion agregada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public ComposicionPienso addComposicion(ComposicionPienso composicion) throws SQLException {
        String sql = " INSERT INTO ComposicionPiensos (PiensosID, IngredienteID, Cantidad) VALUES ( ?, ?, ?)";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, composicion.getIdPienso());
            ps.setInt(2, composicion.getIdIngrediente());
            ps.setFloat(3, composicion.getCantidad());
            ps.executeUpdate();
        }
        return composicion;
    }

    /**
     * Actualiza los datos de una composicion en la base de datos.
     *
     * @param composicion la composicion a actualizar
     * @return la composicion actualizada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public ComposicionPienso updateComposicion(ComposicionPienso composicion) throws SQLException {
        String sql = "UPDATE ComposicionPiensos SET PiensosID = ?, IngredienteID = ?, Cantidad = ? WHERE PiensosID = ? AND IngredienteID = ?";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, composicion.getIdPienso());
            ps.setInt(2, composicion.getIdIngrediente());
            ps.setFloat(3, composicion.getCantidad());
            ps.setInt(4, composicion.getIdPienso());
            ps.setInt(5, composicion.getIdIngrediente());
            ps.executeUpdate();
        }
        return composicion;
    }

    /**
     * Elimina una composicion de la base de datos.
     *
     * @param id el ID del pienso
     * @param idIngrediente el ID del ingrediente
     * @return la composicion eliminada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public ComposicionPienso deleteComposicion (int id, int idIngrediente) throws SQLException {
        ComposicionPienso composicion = getcomposicionById(id,idIngrediente);
        String sql = "DELETE FROM ComposicionPiensos WHERE PiensosID = ? AND IngredienteID = ?";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, idIngrediente);
            ps.executeUpdate();
        }
        return composicion;
    }

    /**
     * Obtiene todas las composiciones de la base de datos.
     *
     * @return Lista de las composiciones
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<ComposicionPienso> getAllComposiciones() throws SQLException {
        ArrayList<ComposicionPienso> composicionsDB = new ArrayList<>();
        String query = "{ call obtener_composiciones() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                composicionsDB.add(ComposicionPienso.builder()
                        .idPienso(rs.getInt(1))
                        .idIngrediente(rs.getInt(2))
                        .cantidad(rs.getFloat(3))
                        .build());
            }
        }

        return composicionsDB;
    }

    /**
     * Obtiene una composicion de la base de datos por su ID.
     *
     * @param id el ID del pienso
     * @param idIngrediente el ID del ingrediente
     * @return la composicion encontrada, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public ComposicionPienso getcomposicionById(int id, int idIngrediente) throws SQLException {
        ComposicionPienso composicion = null;
        String query = "SELECT * FROM ComposicionPiensos WHERE PiensosID=" + id + " AND IngredienteID=" + idIngrediente;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                composicion = ComposicionPienso.builder()
                        .idPienso(rs.getInt(1))
                        .idIngrediente(rs.getInt(2))
                        .cantidad(rs.getFloat(3))
                        .build();
            }
        }

        return composicion;
    }

}