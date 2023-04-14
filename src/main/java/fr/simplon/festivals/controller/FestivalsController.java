package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalsDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String displayAllFestivals(Model model) {
        List<Festival> festivals = festivalsDao.getAllFestivals();
        model.addAttribute("festivals", festivals);
        return "index";
    }

    @GetMapping("/inscription")
    public String displayForms(Model model) {
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

    /**
     * Displays the form for editing an existing Festival.
     *
     * @param id Long representing the ID of the Festival to be edited.
     * @param model Model object to be used for storing and accessing data to be displayed on the view.
     * @return String representing the name of the view to be rendered for displaying the form.
     */
    @GetMapping("/modification")
    public String displayEditForm(@RequestParam("id") Long id, Model model) {
        Optional<Festival> optionalFestival = festivalsDao.findById(id);
        if (optionalFestival.isPresent()) {
            Festival festival = optionalFestival.get();
            model.addAttribute("festival", festival);
            return "modification";
        } else {
            // In case of absence from the festival with the given ID
            return "redirect:/";
        }
    }

    /**
     * Updates an existing Festival in the database.
     *
     * @param festival Festival object representing the Festival to be updated.
     * @return String representing the name of the view to be rendered after updating the Festival.
     */
    @PostMapping("/modification")
    public String editFestival(@ModelAttribute("festival") Festival festival) {

        // Retrieve the existing Festival object using its unique identifier
        Optional<Festival> optionalFestival = festivalsDao.findById(festival.getId());

        if (optionalFestival.isPresent()) {
            Festival existingFestival = optionalFestival.get();

            // Update the other properties of the existing Festival object
            existingFestival.setNom(festival.getNom());
            existingFestival.setVille(festival.getVille());
            existingFestival.setLieu(festival.getLieu());
            existingFestival.setDateDebut(festival.getDateDebut());
            existingFestival.setDateFin(festival.getDateFin());

            // Keep the existing values of latitude and longitude
            existingFestival.setLatitude(existingFestival.getLatitude());
            existingFestival.setLongitude(existingFestival.getLongitude());

            // Save changes in the database
            festivalsDao.saveFestivals(existingFestival);
            return "redirect:/";

        } else {

            // In case of absence from the festival with the given ID
            return "redirect:/";
        }
    }

    /**
     * Retrieves all Festivals from the database and returns them in JSON format for use in API requests.
     *
     * @return A List of Festival objects in JSON format.
     */
    @GetMapping("/api/festivals")
    @ResponseBody
    public List<Festival> getAllFestivalsAsJson() {
        List<Festival> festivals = festivalsDao.getAllFestivals();
        return festivals;
    }

    @PostMapping("/suppression")
    public String deleteFestival(@ModelAttribute("festival") Festival festival) {
        festivalsDao.deleteFestival(festival.getId());
        return "redirect:/";
    }

}
