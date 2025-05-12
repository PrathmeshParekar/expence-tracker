package in.sp.main.Controller;

import in.sp.main.Entity.Expence;
import in.sp.main.Service.ExpenceService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ExpenceController {

    @Autowired
    private ExpenceService expenceService;


    @GetMapping("/get")
    public String getAll(Model model, Principal principal){

        String username = principal.getName();

        List<Expence> expenceList = expenceService.getAlExpenceByUser(username);
        model.addAttribute("exp1",expenceList);
        return "exp_list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        Expence expence = new Expence();
        model.addAttribute("exp2",expence);
        return "add_exp";
    }

    @PostMapping("/create")
    public String saveExpence(@Valid @ModelAttribute("exp2") Expence expence, BindingResult result,Model model,Principal principal){

        if(result.hasErrors()){
            return "add_exp";
        }

        try{
            String username = principal.getName();
            expenceService.createExpence(expence,username);
        }
        catch (Exception e)
        {
            model.addAttribute("errors",e.getMessage());
            return "add_exp";

        }

        return "redirect:/get";

    }



    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id,Principal principal,Model model){
        String username=principal.getName();

        Expence expence = expenceService.getExpenceById(id,username);
        model.addAttribute("exp3",expence);
        return "edit_exp";
    }


    @PostMapping("/change/{id}")
    public String updateExpence( @Valid @ModelAttribute("exp3") Expence expence, @PathVariable("id") Integer id,Principal principal,BindingResult result,Model model){
        if(result.hasErrors()){
            return "edit_exp";
        }

        try{
            String username = principal.getName();
            expenceService.updateExpence(id,expence,username);
        }
        catch (Exception e)
        {
            model.addAttribute("expError",e.getMessage());
            return "edit_exp";
        }
        return "redirect:/get";

    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,Principal principal){
        String username = principal.getName();
        expenceService.deleteExpence(id,username);
        return "redirect:/get";
    }
}
