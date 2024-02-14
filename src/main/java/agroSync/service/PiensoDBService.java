package agroSync.service;

import agroSync.repository.PiensoDBRepository;
import agroSync.repository.model.Pienso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para manejar los piensos en la base de datos.
 */
@Service
public class PiensoDBService {

    @Autowired
    public PiensoDBRepository repository;

    /**
     * Añade un nuevo pienso a la base de datos.
     *
     * @param pienso el pienso a añadir
     * @return el pienso añadido
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Pienso addPienso(Pienso pienso) throws SQLException {
        return repository.addPienso(pienso);
    }

    /**
     * Actualiza un pienso en la base de datos.
     *
     * @param pienso el pienso a actualizar
     * @return el pienso actualizado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Pienso updatePienso(Pienso pienso) throws SQLException {
        return repository.updatePienso(pienso);
    }

    /**
     * Elimina un pienso de la base de datos.
     *
     * @param id el ID del pienso a eliminar
     * @return el pienso eliminado
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public Pienso deletePienso(int id) throws SQLException {
        return repository.deletePienso(id);
    }

    /**
     * Obtiene todos los piensos de la base de datos.
     *
     * @return Lista de piensos
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public List<Pienso> getAllPiensos() throws SQLException {
        return repository.getAllPiensos();
    }

    /**
     *  Agrega la cantidad fabricada de pienso y resta al stock de ingredientes
     * @param piensoId el id del pienso
     * @param cantidadTotal la cantidad a añadir del pienso.
     * @throws SQLException
     */
    public void agregarPienso(String piensoId, String cantidadTotal) throws SQLException {
        repository.agregarPienso(piensoId, cantidadTotal);
    }
}