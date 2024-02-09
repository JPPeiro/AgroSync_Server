package agroSync.repository.intefaces;

import agroSync.repository.model.Pienso;

import java.sql.SQLException;
import java.util.List;

public interface IPiensoRepository {
    /**
     * Agrega un nuevo pienso a la base de datos.
     *
     * @param pienso el pienso a agregar
     * @return el pienso agregado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Pienso addPienso(Pienso pienso) throws SQLException;

    /**
     * Actualiza un pienso existente en la base de datos.
     *
     * @param pienso el piensos a actualizar
     * @return el pienso actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Pienso updatePienso(Pienso pienso) throws SQLException;

    /**
     * Elimina un pienso de la base de datos.
     *
     * @param id el ID del piensos a eliminar
     * @return el piensos eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    Pienso deletePienso(int id) throws SQLException;

    /**
     * Obtiene todos los piensos de la base de datos.
     *
     * @return Lista de piensos
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    List<Pienso> getAllPiensos() throws SQLException;
}
