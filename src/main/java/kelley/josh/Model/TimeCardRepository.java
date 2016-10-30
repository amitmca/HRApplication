package kelley.josh.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by joshuakelley on 10/25/16.
 */
public interface TimeCardRepository extends JpaRepository<TimeCard, Long> {
    List<TimeCard> findTimeCardByEmployeeID(int employeeID);
    TimeCard findTimeCardByTimeCardId(Long id);
}
