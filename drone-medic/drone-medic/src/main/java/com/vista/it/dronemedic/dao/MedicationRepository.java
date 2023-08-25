package com.vista.it.dronemedic.dao;

import com.vista.it.dronemedic.model.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication,Long> {
}
