package dogcare.entity;

	import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

	@Entity
	@Data
	public class Dogcare {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long dogcareId;
		private String dogcareName;
		private String dogcareAddress;
		private String dogcareCity;
		private String dogcareZip;
		private String dogcareState;
		private String dogcarePhone;
		
		@EqualsAndHashCode.Exclude
		@ToString.Exclude
		@ManyToMany(cascade = CascadeType.PERSIST)
		@JoinTable(name = "dogcare_customer", joinColumns = @JoinColumn( name = "dogcare_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
		private Set<Customer> customers = new HashSet<>();
		
		
		@OneToMany(mappedBy = "dogcare", cascade = CascadeType.ALL, orphanRemoval = true)
		@EqualsAndHashCode.Exclude
		@ToString.Exclude
		private Set<Employee> employees = new HashSet<>();
		
	}