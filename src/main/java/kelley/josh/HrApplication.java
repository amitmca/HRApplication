package kelley.josh;

import kelley.josh.Model.Employee;
import kelley.josh.Model.EmployeeRepository;
import kelley.josh.Model.TimeCard;
import kelley.josh.Model.TimeCardRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableWebSecurity
public class HrApplication {
	SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");

	@Bean
	InitializingBean saveData(EmployeeRepository empRepo, TimeCardRepository tcRepo){//method returning an initializing bean
		return () -> {
			empRepo.save(new Employee("Josh","Kelley",100));
			empRepo.save(new Employee("Heron","Ziegel",100));
			empRepo.save(new Employee("Harvey","Dent",100));
			tcRepo.save(new TimeCard("Jul 21, 2016 14:00:00","Jul 21, 2016 19:00:00",1,false));
			tcRepo.save(new TimeCard("Jul 22, 2016 14:00:00","Jul 22, 2016 19:00:00",1,false));
			tcRepo.save(new TimeCard("Jul 21, 2016 09:00:00","Jul 22, 2016 17:00:00",2,false));
			tcRepo.save(new TimeCard("Jul 22, 2016 09:00:00","Jul 22, 2016 17:00:00",2,false));
			tcRepo.save(new TimeCard("Jul 21, 2016 19:00:00","Jul 22, 2016 1:00:00",3,false));
			tcRepo.save(new TimeCard("Jul 22, 2016 19:00:00","Jul 23, 2016 1:00:00",3,false));
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}
}
