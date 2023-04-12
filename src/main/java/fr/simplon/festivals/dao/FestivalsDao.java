package fr.simplon.festivals.dao;

import fr.simplon.festivals.entity.Festival;

import java.util.List;
import java.util.Optional;

public interface FestivalsDao {
    void saveFestivals(Festival festival);

    List<Festival> getAllFestivals();


    Optional<Festival> findById(Long id);
}
