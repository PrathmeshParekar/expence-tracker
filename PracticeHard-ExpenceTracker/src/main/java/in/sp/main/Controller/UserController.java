package in.sp.main.Controller;

import in.sp.main.Entity.User;
import in.sp.main.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model){
        User user = new User();
        model.addAttribute("user1",user);
        return "register";

    }

    @PostMapping("/register")
    public String savedUser(@Valid @ModelAttribute("user1") User user, BindingResult result,Model model){

        if(result.hasErrors()){
            return "register";
        }


        if(!user.getPassword().equals(user.getConfirm())){
            model.addAttribute("notMatch","Password and Confirm Password is not match");
            return "register";
        }


        try{
            userService.registerUser(user);
        }
        catch (Exception e){
            model.addAttribute("userExists",e.getMessage());
            return "register";
        }
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
