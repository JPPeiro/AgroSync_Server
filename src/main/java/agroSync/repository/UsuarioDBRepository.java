package agroSync.repository;

import agroSync.repository.intefaces.IUsuarioRepository;
import agroSync.repository.model.MyDataSource;
import agroSync.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDBRepository implements IUsuarioRepository {

    /**
     * Agrega un nuevo usuario a la base de datos.
     *
     * @param usuario el usuario a agregar
     * @return el usuario agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {
        String sql = "{call crear_usuario(?,?,?,?,?)}";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(2, usuario.getId());
            cs.setString(3, usuario.getNombre());
            cs.setString(4, usuario.getPassword());
            cs.setString(5, usuario.getPermisos());
            cs.execute();
        }
        return usuario;
    }

    /**
     * Actualiza los datos de un usuario en la base de datos.
     *
     * @param usuario el usuario a actualizar
     * @return el usuario actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        String sql = "{? = call actualizar_usuario(?,?,?,?)}";
        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(2, usuario.getId());
            cs.setString(3, usuario.getNombre());
            cs.setString(4, usuario.getPassword());
            cs.setString(5, usuario.getPermisos());
            cs.execute();
        }
        return usuario;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param id el ID del usuario a eliminar
     * @return el usuario eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    @Override
    public Usuario deleteUsuario(int id) throws SQLException {
        Usuario usuario = getUsuarioById(id);
        String sql = " {? = call eliminar_usuario(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(2, id);
            cs.execute();
        }
        return usuario;
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Lista de usuarios
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Usuario> getAllUsuarios() throws SQLException {
        ArrayList<Usuario> usuariosDB = new ArrayList<>();
        String query = "{ call obtener_usuarios() }";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement st = connection.prepareCall(query);
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                usuariosDB.add(Usuario.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .password(rs.getString(3))
                        .permisos(rs.getString(4))
                        .build());
            }
        }

        return usuariosDB;
    }

    /**
     * Obtiene un usuario de la base de datos por su ID.
     *
     * @param id el ID del usuario
     * @return el usuario encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Usuario getUsuarioById(int id) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM Usuario WHERE id=" + id;

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                usuario = Usuario.builder()
                        .id(rs.getInt(1))
                        .nombre(rs.getString(2))
                        .password(rs.getString(3))
                        .permisos(rs.getString(4))
                        .build();
            }
        }

        return usuario;
    }

}