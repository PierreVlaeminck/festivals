package fr.simplon.festivals.dao;

import fr.simplon.festivals.entity.Festival;

import java.util.List;

public interface FestivalsDao {
    void saveFestivals(Festival festival);

    List<Festival> getAllFestivals();
}
