package agroSync.repository;

import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.Pienso;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PiensoDBRepository implements IPiensoRepository {
    /**
     * Agrega un nuevo pienso a la base de datos.
     *
     * @param pienso el pienso a agregar
     * @return el pienso agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Pienso addPienso(Pienso pienso) throws SQLException {
        String sql = "{call crear_pienso(?,?,?,?)}";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(2, pienso.getId());
            cs.setString(3, pienso.getNombre());
            cs.setString(4, pienso.getComposicion());
            cs.execute();
        }
        return pienso;
    }

    /**
     * Actualiza los datos de un pienso en la base de datos.
     *
     * @param pienso el pienso a actualizar
     * @return el pienso actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Pienso updatePienso(Pienso pienso) throws SQLException {
        String sql = "{? = call actualizar_pienso(?,?,?,?)}";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(2, pienso.getId());
            cs.setString(3, pienso.getNombre());
            cs.setString(3, pienso.getComposicion());
            cs.execute();
        }
        return pienso;
    }

    /**
     * Elimina un pienso de la base de datos.
     *
     * @param id el ID del pienso a eliminar
     * @return el pienso eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Pienso deletePienso(int id) throws SQLException {
        Pienso pienso = getpiensoById(id);
        String sql = " {? = call eliminar_pienso(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(2, id);
            cs.execute();
        }
        return pienso;
    }

    /**
     * Obtiene todos los piensos de la base de datos.
     *
     * @return Lista de piensos
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Pienso> getAllPiensos() throws SQLException {
        ArrayList<Pienso> piensosDB = new ArrayList<>();
        String query = "{ call obtener_piensos() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                piensosDB.add(Pienso.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .composicion(rs.getString(3))
                        .build());
            }
        }

        return piensosDB;
    }

    /**
     * Obtiene un pienso de la base de datos por su ID.
     *
     * @param id el ID del pienso
     * @return el pienso encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Pienso getpiensoById(int id) throws SQLException {
        Pienso pienso = null;
        String query = "SELECT * FROM Pienso WHERE id=" + id;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                pienso = pienso.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .composicion(rs.getString(3))
                        .build();
            }
        }

        return pienso;
    }

}