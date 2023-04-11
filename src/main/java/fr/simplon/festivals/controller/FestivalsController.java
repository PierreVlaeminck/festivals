package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalsDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FestivalsController {

    @Autowired
    private FestivalsDao festivalsDao;

    // Mapping de la page d'accueil qui affiche la liste des festivals
    @GetMapping("/")
    public String displayAllFestivals(Model model){
        List<Festival> festivals = festivalsDao.getAllFestivals();
        model.addAttribute("festivals", festivals);
        return "index";
    }

}
