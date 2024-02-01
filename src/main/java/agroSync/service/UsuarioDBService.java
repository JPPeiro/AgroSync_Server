package agroSync.service;

import agroSync.repository.UsuarioDBRepository;
import agroSync.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar los usuarios en la base de datos.
 */
@Service
public class UsuarioDBService {

    @Autowired
    public UsuarioDBRepository repository;

    /**
     * Añade un nuevo usuario a la base de datos.
     *
     * @param usuario el usuario a añadir
     * @return el usuario añadido
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Usuario addUsuario(Usuario usuario) throws SQLException {
        return repository.addUsuario(usuario);
    }

    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param usuario el usuario a actualizar
     * @return el usuario actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        return repository.updateUsuario(usuario);
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param id el ID del usuario a eliminar
     * @return el usuario eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Usuario deleteUsuario(int id) throws SQLException {
        return repository.deleteUsuario(id);
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Lista de usuarios
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Usuario> getAllUsuarios() throws SQLException {
        return repository.getAllUsuarios();
    }
}