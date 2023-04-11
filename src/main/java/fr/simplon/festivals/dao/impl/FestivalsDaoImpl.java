package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.dao.FestivalsDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FestivalsDaoImpl implements FestivalsDao {
    @Autowired
    private FestivalsRepository festivalsRepository;

    // Méthode pour enregistrer un nouveau festival dans la base
    @Override
    public void saveFestivals(Festival festival){
        festivalsRepository.save(festival);
    }
    // Récupère tous les festivals present dans la base de données.
    @Override
    public List<Festival> getAllFestivals(){
        return festivalsRepository.findAll();
    }
}
