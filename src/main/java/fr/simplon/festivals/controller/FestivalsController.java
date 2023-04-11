package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalsDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller class for handling requests related to Festivals.
 */
@Controller
public class FestivalsController {

    @Autowired
    private FestivalsDao festivalsDao;

    /**
     * Displays a list of all Festivals in the database on the home page.
     *
     * @param model Model object to be used for storing and accessing data to be displayed on the view.
     * @return String representing the name of the view to be rendered for displaying the list of Festivals.
     */
    @GetMapping("/")
    public String displayAllFestivals(Model model){
        List<Festival> festivals = festivalsDao.getAllFestivals();
        model.addAttribute("festival", festivals);
        return "index";
    }

    @GetMapping("/inscription")
    public String displayForms(Model model){
        model.addAttribute("festival", new Festival());
        return ("inscription");
    }

    /**
     * Displays the form for adding a new Festival.
     *
     * @param festival Festival object representing the new Festival to be added.
     * @return String representing the name of the view to be rendered after adding the new Festival.
     */
    @PostMapping("/inscription")
    public String addFestival(@ModelAttribute("festival") Festival festival) {
        festivalsDao.saveFestivals(festival);
        return ("redirect:/");
    }

}
