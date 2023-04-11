package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalsRepository extends JpaRepository<Festival, Long> {
}
