package de.eorlbruder.civbudget.batch.repository

import de.eorlbruder.civbudget.batch.domain.Rate
import org.springframework.data.repository.CrudRepository

interface RateRepository : CrudRepository<Rate, Long>