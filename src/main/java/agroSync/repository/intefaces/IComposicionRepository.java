package agroSync.repository.intefaces;

import agroSync.repository.model.ComposicionPienso;
import java.sql.SQLException;
import java.util.List;

public interface IComposicionRepository {
    /**
     * Agrega una nueva composicion a la base de datos.
     *
     * @param composicion la composicion a agregar
     * @return la composicion agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    ComposicionPienso addComposicion(ComposicionPienso composicion) throws SQLException;

    /**
     * Actualiza una composicion existente en la base de datos.
     *
     * @param composicion la composicion a actualizar
     * @return la actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    ComposicionPienso updateComposicion(ComposicionPienso composicion) throws SQLException;

    /**
     * Elimina una composicion de la base de datos.
     *
     * @param idPienso el ID del pienso
     * @param idIngrediente el ID del ingrediente
     * @return la composicion eliminada
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    ComposicionPienso deleteComposicion(int idPienso, int idIngrediente) throws SQLException;

    /**
     * Obtiene todas las composiciones de la base de datos.
     *
     * @return Lista de composiciones
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<ComposicionPienso> getAllComposiciones() throws SQLException;
}