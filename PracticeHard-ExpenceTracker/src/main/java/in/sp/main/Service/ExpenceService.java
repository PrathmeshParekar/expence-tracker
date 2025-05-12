package in.sp.main.Service;

import in.sp.main.Entity.Expence;
import in.sp.main.Entity.User;
import in.sp.main.Repository.ExpenceRepo;
import in.sp.main.Repository.UserRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenceService {

    @Autowired
    private ExpenceRepo expenceRepo;

    @Autowired
    private UserRepo userRepo;



    public List<Expence> getAlExpenceByUser(String username){
        User user = userRepo.findByUsername(username);

        return expenceRepo.findByUser(user);
    }


    public Expence getExpenceById(Integer id,String username){
        User user = userRepo.findByUsername(username);

        return expenceRepo.findById(id)
                .filter(expence -> expence.getUser().getId().equals(user.getId()))
                .orElseThrow(()-> new RuntimeException("Expence not found"));
    }


    public Expence createExpence(Expence expence,String username){
        User user = userRepo.findByUsername(username);

        expence.setUser(user);

        return expenceRepo.save(expence);
    }

    public Expence updateExpence(Integer id,Expence expence,String username){
        Expence expence1 = getExpenceById(id,username);
        expence1.setExpencename(expence.getExpencename());
        expence1.setDate(expence.getDate());
        expence1.setAmmount(expence.getAmmount());
        expence1.setDescription(expence.getDescription());
        return expenceRepo.save(expence1);
    }

    public void deleteExpence(Integer id,String username){
        Expence expence = getExpenceById(id,username);
        expenceRepo.delete(expence);
    }


}
