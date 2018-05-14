package eins.controller;

import eins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class MainRestController {

    @Autowired
    private UserService uService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/loginValidate")
    public boolean loginValidate(@RequestParam String userLogin){
        return uService.loadUserByUsername(userLogin) == null;
    }

    @PostMapping("/user/passValidate")
    public boolean passValidate(@RequestParam String userPass, Principal principal){
        if (principal == null) return false;
        return passwordEncoder.matches( userPass,
                uService.loadUserByUsername(principal.getName()).getPassword());
    }

    @PostMapping("/order/cart/addProd")
    public boolean addProdToCart(@RequestParam String prod_id,
                                 @RequestParam String prod_num,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        int num = Integer.valueOf(prod_num);
        int prodId = Integer.valueOf(prod_id);
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("prodid_")) {
                long id = Integer.valueOf(cookie.getName().split("prodid_")[1]);
                if (id == prodId) {
                    num += Integer.valueOf(cookie.getValue());
                    cookie.setValue(""+num);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return true;
                }
            }
        }
        Cookie cookieNew = new Cookie("prodid_" + prodId, "" + num);
        cookieNew.setPath("/");
        response.addCookie(cookieNew);
        return true;
    }

}