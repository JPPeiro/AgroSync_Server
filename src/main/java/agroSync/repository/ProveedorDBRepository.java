package agroSync.repository;

import agroSync.repository.intefaces.IProveedorRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProveedorDBRepository implements IProveedorRepository {

    /**
     * Agrega un nuevo Proveedor a la base de datos.
     *
     * @param proveedor el Proveedor a agregar
     * @return el Proveedor agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Proveedor addProveedor(Proveedor proveedor) throws SQLException {
        String sql = "{call crear_Proveedor(?,?,?)}";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(2, proveedor.getId());
            cs.setString(3, proveedor.getNombre());
            cs.execute();
        }
        return proveedor;
    }

    /**
     * Actualiza los datos de un Proveedor en la base de datos.
     *
     * @param proveedor el Proveedor a actualizar
     * @return el Proveedor actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Proveedor updateProveedor(Proveedor proveedor) throws SQLException {
        String sql = "{? = call actualizar_Proveedor(?,?)}";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(2, proveedor.getId());
            cs.setString(3, proveedor.getNombre());
            cs.execute();
        }
        return proveedor;
    }

    /**
     * Elimina un Proveedor de la base de datos.
     *
     * @param id el ID del Proveedor a eliminar
     * @return el Proveedor eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Proveedor deleteProveedor(int id) throws SQLException {
        Proveedor proveedor = getProveedorById(id);
        String sql = " {? = call eliminar_Proveedor(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(2, id);
            cs.execute();
        }
        return proveedor;
    }

    /**
     * Obtiene todos los Proveedores de la base de datos.
     *
     * @return Lista de Proveedores
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Proveedor> getAllProveedores() throws SQLException {
        ArrayList<Proveedor> proveedorDB = new ArrayList<>();
        String query = "{ call obtener_proveedores() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                proveedorDB.add(Proveedor.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .build());
            }
        }

        return proveedorDB;
    }

    /**
     * Obtiene un Proveedor de la base de datos por su ID.
     *
     * @param id el ID del Proveedor
     * @return el Proveedor encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Proveedor getProveedorById(int id) throws SQLException {
        Proveedor proveedor = null;
        String query = "SELECT * FROM Proveedor WHERE id=" + id;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                proveedor = Proveedor.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .build();
            }
        }

        return proveedor;
    }

}