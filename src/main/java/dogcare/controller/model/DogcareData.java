package dogcare.controller.model;

import dogcare.entity.Customer;
import dogcare.entity.Employee;
import dogcare.entity.Dogcare;
import java.util.HashSet;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
public class DogcareData {

private Long dogcareId;
private String dogcareName;
private String dogcareAddress;
private String dogcareCity;
private String dogcareState;
private String dogcareZip;
private String dogcarePhone;
private Set<DogcareCustomer> customers = new HashSet <>();
private Set<DogcareEmployee> employees = new HashSet <>();

public DogcareData (Dogcare dogcare){
         dogcareId = dogcare.getDogcareId();
         dogcareName = dogcare.getDogcareName();
         dogcareAddress = dogcare.getDogcareAddress();
         dogcareCity = dogcare.getDogcareCity();
         dogcareState = dogcare.getDogcareState();
         dogcareZip = dogcare.getDogcareZip();
         dogcarePhone = dogcare.getDogcarePhone();

         	for (Customer customer : dogcare.getCustomers()) {
                 customers.add(new DogcareCustomer(customer));
         	}
         	for(Employee employee : dogcare.getEmployees()) {
                  employees.add(new DogcareEmployee(employee ));
         	}
	}
@Data
@NoArgsConstructor
public static class DogcareCustomer{
   private Long customerId;
   private String customerFirstName;
   private String customerLastName;
	private String customerAddress;
	private String customerCity;
	private String customerState;
	private String customerZipCode;	 
	private String customerPetName;
	private String customerEmail;
	
   public DogcareCustomer(Customer customer){
        customerId = customer.getCustomerId();
        customerFirstName = customer.getCustomerFirstName();
        customerLastName = customer.getCustomerLastName();
        customerEmail = customer.getCustomerEmail();
        customerAddress = customer.getCustomerAddress();    	
        customerCity = customer.getCustomerCity();
    	customerState = customer.getCustomerState();
    	customerZipCode = customer.getCustomerZipCode(); 
    	customerPetName = customer.getCustomerPetName();
    	
   }
}
@Data
@NoArgsConstructor
public static class DogcareEmployee{
      private Long employeeId;
      private String employeeFirstName;
      private String employeeLastName;
      private String employeePhone;
      private String employeeJobTitle;

public DogcareEmployee(Employee employee){
     employeeId = employee.getEmployeeId();
     employeeFirstName = employee.getEmployeeFirstName();
     employeeLastName = employee.getEmployeeLastName();
     employeePhone = employee.getEmployeePhone ();
     employeeJobTitle = employee.getEmployeeJobTitle();
		}
	}
}
