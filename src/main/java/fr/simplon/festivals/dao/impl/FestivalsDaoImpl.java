package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.dao.FestivalsDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of FestivalsDao interface for accessing and modifying Festival data in the database.
 */
@Repository
public class FestivalsDaoImpl implements FestivalsDao {
    @Autowired
    private FestivalsRepository festivalsRepository;


    /**
     * Saves a new Festival object to the database.
     *
     * @param festival Festival object to be saved to the database.
     */
    @Override
    public void saveFestivals(Festival festival) {
        festivalsRepository.save(festival);
    }


    /**
     * Retrieves a list of all Festivals from the database.
     *
     * @return List of all Festivals present in the database.
     */
    @Override
    public List<Festival> getAllFestivals() {
        return festivalsRepository.findAll();
    }

    /**
     * Retrieves a Festival from the database based on its ID.
     *
     * @param id ID of the Festival to be retrieved.
     * @return Optional of Festival object with the given ID.
     */
    @Override
    public Optional<Festival> findById(Long id) {
        return festivalsRepository.findById(id);
    }

    @Override
    public void deleteFestival(Long id) {
        festivalsRepository.deleteById(id);
    }

}
