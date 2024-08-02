package dogcare.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dogcare.controller.model.DogcareData;
import dogcare.controller.model.DogcareData.DogcareCustomer;
import dogcare.controller.model.DogcareData.DogcareEmployee;
import dogcare.dao.DogcareDao;
import dogcare.entity.Customer;
import dogcare.entity.Employee;
import dogcare.entity.Dogcare;
import org.springframework.beans.factory.annotation.*;
import dogcare.dao.CustomerDao;
import dogcare.dao.EmployeeDao;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.LinkedList;

@Service
public class DogcareService {

@Autowired
private DogcareDao dogcareDao;
      
@Autowired 
private EmployeeDao employeeDao;
      
@Autowired
private CustomerDao customerDao;


@Transactional (readOnly = false)
public DogcareData saveDogcare (DogcareData dogcareData){
		Long dogcareId = dogcareData.getDogcareId( );
		Dogcare dogcare = findOrCreateDogcare(dogcareId);
		copyDogcareFields(dogcare, dogcareData);
		return new DogcareData(dogcareDao.save(dogcare));
	}   

private void copyDogcareFields (Dogcare dogcare, DogcareData dogcareData){
	dogcare.setDogcareName(dogcareData.getDogcareName());
	dogcare.setDogcareAddress(dogcareData.getDogcareAddress());
	dogcare.setDogcareCity(dogcareData.getDogcareCity());
	dogcare.setDogcareState(dogcareData.getDogcareState());
	dogcare.setDogcareZip(dogcareData.getDogcareZip());
	dogcare.setDogcarePhone(dogcareData.getDogcarePhone());
	dogcare.setDogcareId(dogcareData.getDogcareId());
}

private Dogcare findOrCreateDogcare(Long dogcareId){
 Dogcare dogcare = new Dogcare();

if (!Objects.isNull(dogcareId)){
      dogcare = findDogcareById(dogcareId);
}

return dogcare;
}

private Dogcare findDogcareById (Long dogcareId){
return dogcareDao.findById(dogcareId). orElseThrow (()-> new NoSuchElementException("dogcare with Id: " + dogcareId + " Was not found"));
 }

@Transactional(readOnly = false)
public DogcareData.DogcareEmployee saveEmployee (Long dogcareId, DogcareEmployee employee){
	Dogcare dogcare = findDogcareById(dogcareId);
	Long employeeId = employee.getEmployeeId();
	Employee Employees = findOrCreateEmployee(dogcareId, employeeId);
	
	copyEmployeeFields(Employees, employee);
	Employees.setDogcare(dogcare);
	dogcare.getEmployees().add(Employees);
	
	return new DogcareData.DogcareEmployee(employeeDao.save(Employees));	
} 
	@SuppressWarnings("null")
	private Employee findOrCreateEmployee (Long dogcareId, Long employeeId) {
		Employee employee;
	
		
	if(Objects.isNull(employeeId)){
		employee = new Employee();
		
	} else {
		
		employee = findEmployeeById(dogcareId, employeeId);
	}
	
	return employee;
}

private Employee findEmployeeById (long DogcareId, long employeeId) {
	Employee employee = employeeDao.findById(employeeId).orElseThrow(
			() -> new NoSuchElementException("Employee with ID: " + employeeId + " does not exists.")
	);

	if (Objects.equals(employee.getDogcare().getDogcareId(), DogcareId)){
			return employee;
	
	} else {
		
			throw new IllegalArgumentException ("Employee iwth ID " + employee + "Does not exists at:" + DogcareId);
	}
}

private void copyEmployeeFields(Employee employee, DogcareData.DogcareEmployee employees) {
	employee.setEmployeeFirstName(employees.getEmployeeFirstName());
	employee.setEmployeeLastName(employees.getEmployeeLastName());
	employee.setEmployeePhone(employees.getEmployeePhone());
	employee.setEmployeeJobTitle(employees.getEmployeeJobTitle());
}

@Transactional (readOnly = false)
public DogcareData.DogcareCustomer saveCustomer(Long dogcareId, DogcareData.DogcareCustomer DogcareCustomer){
		Dogcare dogcare = findDogcareById(dogcareId);
		Long customerId = DogcareCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(dogcareId, customerId);

		
	copyCustomerFields(customer, DogcareCustomer);
	customer.getDogcare().add(dogcare);
	dogcare.getCustomers().add(customer);

	
	return new DogcareData.DogcareCustomer (customerDao.save(customer));
}

private Customer findOrCreateCustomer(Long dogcareId, Long customerId){
	Customer customer;
	
	if(Objects.isNull(customerId)) {
		customer = new Customer();
	
	} else {
		
		customer = findCustomerById(dogcareId, customerId);
	}
		
	return customer;
}
		
private Customer findCustomerById (Long dogcareId, Long customerId) {
	Customer customer = customerDao.findById(customerId).orElseThrow(
			() -> new NoSuchElementException ("Customer with ID: " + customerId + "Does not exists.")
	);
	
	boolean DogcareFound = false;
	
	for (Dogcare dogcare : customer.getDogcare()) {
		if(Objects.equals(dogcare.getDogcareId(), dogcareId)){
			DogcareFound = true;
		}
	}
	
	if(DogcareFound){
		return customer;
		
	} else {
		
		throw new IllegalArgumentException(" Dogcare " + dogcareId + " does not have a customer " + customerId);
			}
	
		}

	private void copyCustomerFields(Customer customer, DogcareData.DogcareCustomer dogcareCustomer){ 
		customer.setCustomerFirstName(dogcareCustomer.getCustomerFirstName());
		customer.setCustomerLastName(dogcareCustomer.getCustomerLastName());
		customer.setCustomerEmail(dogcareCustomer.getCustomerEmail());	
		customer.setCustomerAddress(dogcareCustomer.getCustomerAddress());
		customer.setCustomerState(dogcareCustomer.getCustomerState());
		customer.setCustomerCity(dogcareCustomer.getCustomerCity());
		customer.setCustomerZipCode(dogcareCustomer.getCustomerZipCode());
		customer.setCustomerPetName(dogcareCustomer.getCustomerPetName());
		
}

@Transactional(readOnly = true)
public List <DogcareData> retrieveAllDogcares(){
		List<Dogcare> dogcares = dogcareDao.findAll();
		List<DogcareData> result = new LinkedList<>();
		
		
		for(Dogcare dogcare : dogcares){
			DogcareData dogcareData = new DogcareData(dogcare);
			
				result.add(dogcareData);
			}
		
		return result;
	}
	
@Transactional(readOnly = true)
public DogcareData retrieveDogcareById(Long dogcareId){
	return new DogcareData(findDogcareById(dogcareId));	
}

	public void deleteDogcareById(long dogcareId){
		Dogcare dogcare = findDogcareById(dogcareId);
		dogcareDao.delete(dogcare);
	}
}