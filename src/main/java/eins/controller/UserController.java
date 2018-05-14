package eins.controller;

import eins.entity.CompanyUser;
import eins.entity.Invoice;
import eins.entity.Review;
import eins.entity.User;
import eins.service.InvoiceService;
import eins.service.MailService;
import eins.service.ReviewService;
import eins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Controller
@RequestMapping("/user")
public class UserController {

    //TODO REGISTRATION. Check email.
    //TODO change password
    //TODO recovery password

    @PostMapping("/userPage/savePersonal")
    public String savePersonal(@RequestParam String urId,
                               @RequestParam(required = false) String urOwnership,
                               @RequestParam(required = false) String urFullName,
                               @RequestParam(required = false) String urShortName,
                               @RequestParam(required = false) String urCode,
                               @RequestParam String urName,
                               @RequestParam String urSurname,
                               @RequestParam String urPhoneNumber,
                               @RequestParam String urEmail){

        long id = 0;
        try { id = Integer.valueOf(urId); } catch (NumberFormatException e) { }
        User user = uService.findOneWithCompanyData(id);
        if (user == null) return "redirect:/user/userPage/main";

        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);

        if (!user.isCompany()) {
            uService.save(user);
            return "redirect:/user/userPage/main";
        }

        CompanyUser cUser = user.getCompanyData();
        cUser.setOwnership(urOwnership);
        cUser.setFullName(urFullName);
        cUser.setShortName(urShortName);
        cUser.setCode(urCode);

        uService.save(user, cUser);

        return "redirect:/user/userPage/main";
    }

    @GetMapping("/userPage/personal")
    public String userPagePersonal(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("upPersonalShow","block");
        model.addAttribute("upInvoiceShow","none");
        model.addAttribute("upReviewShow","none");
        model.addAttribute("upChangePassShow","none");
        model.addAttribute("userPagePersonal","active");
        model.addAttribute("loggedUser", uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId()));
        return "userPage";
    }

    @GetMapping({"/userPage/main", "/userPage/invoice"})
    public String userPageInvoice(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        User user = uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId());
        model.addAttribute("upPersonalShow","none");
        model.addAttribute("upInvoiceShow","block");
        model.addAttribute("upReviewShow","none");
        model.addAttribute("upChangePassShow","none");
        model.addAttribute("userPageInvoice","active");
        model.addAttribute("listInvoice", invService.findAllByBuyerId(user.getId()));
        model.addAttribute("loggedUser", user);
        return "userPage";
    }

    @GetMapping("/userPage/review")
    public String userPageReview(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("upPersonalShow","none");
        model.addAttribute("upInvoiceShow","none");
        model.addAttribute("upReviewShow","block");
        model.addAttribute("upChangePassShow","none");
        model.addAttribute("userPageReview","active");
        List<Review> reviews = reviewService.findAllByUserUsername(principal.getName());
        System.out.println(reviews);
        model.addAttribute("reviews", reviews);
        model.addAttribute("loggedUser", uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId()));
        return "userPage";
    }

    @GetMapping("/userPage/changePass")
    public String userPageChangePass(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("upPersonalShow","none");
        model.addAttribute("upInvoiceShow","none");
        model.addAttribute("upReviewShow","none");
        model.addAttribute("upChangePassShow","block");
        model.addAttribute("userPageCangePass","active");
        model.addAttribute("loggedUser", uService.findOneWithCompanyData(uService.findByUsername(principal.getName()).getId()));
        return "userPage";
    }

    @GetMapping("/userPage/detailsInvoice{id}")
    public String detailsInvoice(@PathVariable("id") long id,
                                 Model model) {
        Invoice invoice = invService.findOneWithProducts(id);
        model.addAttribute("invoiceProducts", invoice.getProducts());
        model.addAttribute("invoiceSum", invoice.getSum());
        return "userInvoiceDetails";
    }


    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (error != null) {
            model.addAttribute("error", "Не вірне ім'я та пароль.");
        }

        if (logout != null) {
            model.addAttribute("msg", "Для входу в систему введіть Ваш логін та пароль.");
        }

        return "loginPage";

    }

    @PostMapping(value = "/loginOk")
    public String loginOk(){
        return "redirect:/main/index";
    }

    @PostMapping(value = "/logout")
    public String logout(){
        return "redirect:/login?logout";
    }


    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }


    @GetMapping("/registrationPage")
    public String registrationPage(Model model) {
        return "registrationPage";
    }



    @PostMapping("/regCompanyUser")
    public String regCompanyUser(@RequestParam String urOwnership,
                                 @RequestParam String urFullName,
                                 @RequestParam String urShortName,
                                 @RequestParam String urCode,
                                 @RequestParam String urName,
                                 @RequestParam String urSurname,
                                 @RequestParam String urPhoneNumber,
                                 @RequestParam String urUsername,
                                 @RequestParam String urEmail,
                                 @RequestParam String urPassword) {

        User user = new User();

        user.setUsername(urUsername);
        user.setPassword(passwordEncoder.encode(urPassword));
        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);

        CompanyUser cUser = new CompanyUser(0L, urOwnership, urFullName, urShortName, urCode);

        uService.save(user, cUser);

        return "redirect:/user/login";
    }



    @PostMapping("/regIndividualUser")
    public String regIndividualUser(@RequestParam String urName,
                                    @RequestParam String urSurname,
                                    @RequestParam String urPhoneNumber,
                                    @RequestParam String urUsername,
                                    @RequestParam String urEmail,
                                    @RequestParam String urPassword) {

        User user = new User();
        user.setUsername(urUsername);
        user.setPassword(passwordEncoder.encode(urPassword));
        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);
        uService.save(user);

        return "redirect:/user/login";
    }


    @GetMapping("/passrecPage")
    public String passrecPage() {
        return "passrecPage";
    }

    @PostMapping("/passrecovery")
    public String passrecovery(@RequestParam String userEmail,
                               @RequestHeader String host,
                               Model model) {

        System.out.println(userEmail);

        User user = uService.findByEmail(userEmail);

        if (user == null){
            //TODO Add message
            return "redirect:/user/passrecPage";
        }

        String tempLink = generateTempPass(30);
        String fullTempLink = "/user/setNewPass?tempLink="+ tempLink;

        user.setPassRecCode(passwordEncoder.encode(tempLink));
        user.setTimestampOfPassRec(Timestamp.valueOf(LocalDateTime.now()));

        fullTempLink += "&un="+user.getUsername();

        uService.save(user);

        mailService.sendMailRecPass(user.getEmail(),"http://" + host + fullTempLink, 5);

        //TODO Add message

        return "redirect:/main/index";
    }

    @GetMapping("setNewPass")
    public String setNewPass(@RequestParam String tempLink,
                             @RequestParam String un,
                             Model model) {
        User user = uService.findByUsername(un);

        if (user == null) return "redirect:/main/index";

        if (!passwordEncoder.matches(tempLink,user.getPassRecCode())) {
            System.out.println("This link is damaged");
            //TODO Add message
            return "redirect:/main/index";
        }
        Timestamp timestampOfPassRec = user.getTimestampOfPassRec();
        long timePassed = System.currentTimeMillis() - timestampOfPassRec.getTime();
        if (timePassed > 300000) {
            System.out.println("This link doesn't valid");
            //TODO Add message
            return "redirect:/main/index";
        }
        model.addAttribute("tempLink", tempLink);
        model.addAttribute("un", un);
        return "setNewPassPage";
    }

    @PostMapping("/saveNewPass")
    public String saveNewPass(@RequestParam String newPass,
                              @RequestParam String tempLink,
                              @RequestParam String un) {
        User user = uService.findByUsername(un);
        if (!passwordEncoder.matches(tempLink,user.getPassRecCode())) {
            System.out.println("Something's wrong");
            //TODO Add message
            return "redirect:/main/index";
        }
        System.out.println(newPass);
        user.setPassword(passwordEncoder.encode(newPass));
        user.setPassRecCode(null);
        user.setTimestampOfPassRec(null);
        uService.save(user);
        return "redirect:/user/login";
    }


    //////////////////////////////////////////////////////////////////////////////



    @Autowired
    private UserService uService;
    @Autowired
    private InvoiceService invService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;



    private String generateTempPass(int length){
        String pass = "";
        Random r = new Random();
        List<Supplier<Integer>> funcs = new ArrayList<>();
        // number char code [48 - 57]
        funcs.add(() -> {return (r.nextInt(10)+48);});
        // bigger = 65 - 90
        funcs.add(() -> {return (r.nextInt(26)+65);});
        // smaller = 97 - 122
        funcs.add(() -> {return (r.nextInt(26)+97);});
        for (int i = 0; i < length; i++){
            char ch = (char) (int) funcs.get(r.nextInt(3)).get();
            pass += ch;
        }
        return pass;
    }
}