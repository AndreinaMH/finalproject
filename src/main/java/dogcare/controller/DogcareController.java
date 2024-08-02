package dogcare.controller;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import dogcare.controller.model.DogcareData;
import dogcare.entity.Dogcare;
import dogcare.service.DogcareService;
// localhost: 8080

@RestController
@RequestMapping("/dogcare")
@Slf4j
public class DogcareController {

@Autowired
private DogcareService  dogcareService;

@PostMapping
@ResponseStatus (code = HttpStatus.CREATED)
public DogcareData insertDogcare (@RequestBody DogcareData dogcareData) {
	log.info("Creating dogcare { }", dogcareData);
	return dogcareService.saveDogcare(dogcareData);
}

@PutMapping("/{dogcareId}")
public DogcareData updateDogcare(@PathVariable Long dogcareId,
@RequestBody DogcareData dogcareData) {
	dogcareData.setDogcareId(dogcareId);
	log.info("Updating dogcare with Id={}", dogcareId);
	return dogcareService.saveDogcare(dogcareData);
	}

@PostMapping("/{dogcareId}/employee")
@ResponseStatus(code = HttpStatus.CREATED)
public DogcareData.DogcareEmployee insertEmployeeInDogcare(@PathVariable long dogcareId, @RequestBody DogcareData.DogcareEmployee employee){
	log.info("Adding employee: {} to dogcare {}", employee, dogcareId);
	return dogcareService.saveEmployee(dogcareId, employee);
	}

@PostMapping("/{dogcareId}/customer")
@ResponseStatus(code = HttpStatus.CREATED)
public DogcareData.DogcareCustomer insertCustomerInDogcare(@PathVariable long dogcareId, @RequestBody DogcareData.DogcareCustomer customer){
	log.info("Adding customer: {} to store {}", customer, dogcareId);
	return dogcareService.saveCustomer(dogcareId, customer);
	}

@GetMapping
public List<DogcareData> getAllDogcares() {
	log.info("Retrieving all dogcares information without customer and employee data.");
	return dogcareService.retrieveAllDogcares();
	}
	
@GetMapping("/{dogcareId}")
public DogcareData getDogcareById(@PathVariable Long dogcareId) {
	log.info("Retrieving information for dogcare with ID: '{}' ", dogcareId);
	return dogcareService.retrieveDogcareById(dogcareId);
	}

@DeleteMapping("/{dogcareId}")
public Map<String, String> deleteDogcareById (@PathVariable Long dogcareId){
	log.info("Deleting dogcare with ID: '{}' ", dogcareId);
	dogcareService.deleteDogcareById(dogcareId);
	
	return Map.of( "message", "Deletion of dogcare with Id: '" + dogcareId + "' was successful. ");
	}
}