package kelley.josh.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshuakelley on 10/25/16.
 */
@RestController("/")
public class HRController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimeCardRepository timeCardRepository;

    @RequestMapping(value = "employees",method = RequestMethod.GET)
    public @ResponseBody List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "employees/{id}",method = RequestMethod.DELETE)
    @CrossOrigin
    public Employee fireEmployee(@PathVariable Long id){
        Employee firedEmployee = employeeRepository.findOne(id);
        employeeRepository.delete(firedEmployee);
        return firedEmployee;
    }

    @RequestMapping(value = "employees/{id}",method = RequestMethod.GET)
    @CrossOrigin
    public Employee getEmployee(@PathVariable Long id){

        return employeeRepository.findOne(id);
    }

    @RequestMapping(value = "employees", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public Employee createNewEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "timecards", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public TimeCard submitTimeCard(@RequestBody TimeCard timeCard){
        return timeCardRepository.save(timeCard);
    }

    @RequestMapping(value = "timecards",method = RequestMethod.GET)
    public @ResponseBody List<TimeCard> getTimeCards(){
        return timeCardRepository.findAll();
    }

    @RequestMapping(value = "timecards/{id}",method = RequestMethod.GET)
    public @ResponseBody List<TimeCard> getTimeCardsById(@PathVariable Integer id){
        return timeCardRepository.findTimeCardByEmployeeID(id);
    }

    //untested
    @RequestMapping(value = "pay/{id}",method = RequestMethod.POST)
    @CrossOrigin
    public double getPay(@RequestBody int[] timeCards,@PathVariable Integer id){
        Employee employee = employeeRepository.getOne((long)id);
        List<TimeCard> these = new ArrayList<>();
        for (int num: timeCards){
        these.add(timeCardRepository.findTimeCardByTimeCardId((long)num));
        }
        return DateManager.calculatePayForPeriod(these,employee);
    }



    //calculate amount for employee

    //


}
