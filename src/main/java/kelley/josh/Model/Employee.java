package kelley.josh.Model;

import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;

/**
 * Created by joshuakelley on 10/25/16.
 */
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeID;
    private String firstName;
    private String lastName;
    private int hourlyPayRate;

    public Employee(String first, String last, int hourlyPayRate){
        this.firstName=first;this.lastName=last;this.hourlyPayRate=hourlyPayRate;
    }

    Employee(){

    }

    public int getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(int hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
