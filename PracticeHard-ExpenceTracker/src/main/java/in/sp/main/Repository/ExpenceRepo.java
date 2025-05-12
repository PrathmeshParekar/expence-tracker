package in.sp.main.Repository;

import in.sp.main.Entity.Expence;
import in.sp.main.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenceRepo extends JpaRepository<Expence,Integer> {
    List<Expence> findByUser(User user);
}
