package agroSync.repository;

import agroSync.repository.intefaces.IPiensoRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.Pienso;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            cs.setInt(4, pienso.getCantidad());
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
            cs.setInt(3, pienso.getCantidad());
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
                        .cantidad(rs.getInt(3))
                        .build());
            }
        }

        return piensosDB;
    }

    /**
     * Llama al procedimiento agregarPienso que añade la cantidad fabricada de pienso y resta la cantidad de ingredientes
     * @param piensoId el id del pienso
     * @param cantidadTotal la cantidad total a añadir al stock de pienso
     * @throws SQLException
     */
    public void agregarPienso(String piensoId, String cantidadTotal) throws SQLException {
        int id = Integer.parseInt(piensoId);
        int cantidad = Integer.parseInt(cantidadTotal);
        String query = "{ call AgregarPienso(?, ?) }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id);
            statement.setInt(2, cantidad);
            statement.execute();
        }
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
                        .cantidad(rs.getInt(3))
                        .build();
            }
        }

        return pienso;
    }

    /**
     *  Comprueba si hay stock suficiente de ingredientes
     * @param piensoId el id del pienso
     * @param cantidadTotal la cantidad total a fabricar
     * @return true si hay stock false y un map con el id del igrediente y la cantidad necesaria
     * @throws SQLException
     */
    public Map<String, Object> verificarStock(int piensoId, int cantidadTotal) throws SQLException {
        String sql = "{call verificarStock(?,?,?,?)}";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, piensoId);
            cs.setInt(2, cantidadTotal);
            cs.registerOutParameter(3, Types.BOOLEAN);
            cs.registerOutParameter(4, Types.VARCHAR);

            cs.execute();

            boolean result = cs.getBoolean(3);
            String mapJson = cs.getString(4);

            Gson gson = new Gson();
            Type type = new TypeToken<List<Object>>(){}.getType();
            List<Object> resultList = gson.fromJson(mapJson, type);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", result);
            resultMap.put("data", resultList);

            return resultMap;
        }
    }


}