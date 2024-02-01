package agroSync.repository;

import agroSync.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz para acceder a los datos de los usuarios en la base de datos.
 */
public interface IUsuarioRepository {

    /**
     * Agrega un nuevo usuario a la base de datos.
     *
     * @param usuario el usuario a agregar
     * @return el usuario agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Usuario addUsuario(Usuario usuario) throws SQLException;

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuario el usuario a actualizar
     * @return el usuario actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Usuario updateUsuario(Usuario usuario) throws SQLException;

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param id el ID del usuario a eliminar
     * @return el usuario eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Usuario deleteUsuario(int id) throws SQLException;

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Lista de usuarios
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<Usuario> getAllUsuarios() throws SQLException;
}