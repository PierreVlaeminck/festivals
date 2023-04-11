package fr.simplon.festivals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.simplon.festivals.dao.impl.FestivalsRepository;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A Spring Component used to load data from a JSON file to a database when the application starts.
 */
@Component
public class DataLoader implements ApplicationRunner {

    /**
     * A reference to the Festivals Repository, used to save the loaded data to the database.
     */
    private final FestivalsRepository mFestivalRepository;

    /**
     * A constructor to create a new instance of DataLoader.
     *
     * @param pFestivalRepository The Festivals Repository used to save the loaded data to the database.
     */
    @Autowired
    public DataLoader(FestivalsRepository pFestivalRepository) {
        this.mFestivalRepository = pFestivalRepository;
    }

    /**
     * The method called by Spring when the application starts. Loads the data from the JSON file to the database.
     *
     * @param args The Application Arguments passed to the Spring Application.
     * @throws Exception If an error occurs while loading the data.
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (mFestivalRepository.count() == 0) {
            ClassPathResource resource = new ClassPathResource("static/datafestivals.json ");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Festival> festivals = objectMapper.readValue(
                    resource.getInputStream(), new TypeReference<List<Festival>>() {
                    });
            mFestivalRepository.saveAll(festivals);
        }
    }
}

