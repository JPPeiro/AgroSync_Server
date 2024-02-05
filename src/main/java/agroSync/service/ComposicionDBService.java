package agroSync.service;

import agroSync.repository.ComposicionDBRepository;
import agroSync.repository.model.ComposicionPienso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar las composiciones en la base de datos.
 */
@Service
public class ComposicionDBService {

    @Autowired
    public ComposicionDBRepository repository;

    /**
     * Añade una nueva composicion a la base de datos.
     *
     * @param composicion la composicion a añadir
     * @return la composicion añadida
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public ComposicionPienso addcomposicion(ComposicionPienso composicion) throws SQLException {
        return repository.addComposicion(composicion);
    }

    /**
     * Actualiza una composicion en la base de datos.
     *
     * @param composicion la composicion a actualizar
     * @return la composicion actualizada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public ComposicionPienso updateComposicion(ComposicionPienso composicion) throws SQLException {
        return repository.updateComposicion(composicion);
    }

    /**
     * Elimina una composicion de la base de datos.
     *
     * @param id el ID del pienso
     * @param idIngrediente el ID del ingrediente
     * @return la composicion eliminada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public ComposicionPienso deleteComposicion(int id, int idIngrediente) throws SQLException {
        return repository.deleteComposicion(id,idIngrediente);
    }

    /**
     * Obtiene todas las composiciones de la base de datos.
     *
     * @return Lista de las composiciones
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<ComposicionPienso> getAllcomposicions() throws SQLException {
        return repository.getAllComposiciones();
    }
}