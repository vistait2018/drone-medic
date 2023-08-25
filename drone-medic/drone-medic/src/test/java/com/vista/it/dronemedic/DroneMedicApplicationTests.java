package com.vista.it.dronemedic;

import com.vista.it.dronemedic.constants.Charger;
import com.vista.it.dronemedic.constants.State;
import com.vista.it.dronemedic.dao.DroneRepository;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.Medication;
import com.vista.it.dronemedic.service.DroneAutomationService;
import com.vista.it.dronemedic.service.DroneService;
import com.vista.it.dronemedic.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class DroneMedicApplicationTests {

	@Test
	void contextLoads() {
	}



}
