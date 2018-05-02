package eins.controller;

import eins.entity.Product;
import eins.entity.User;
import eins.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
public class RedirectController {

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String pgName,
                        @RequestParam(required = false) String searchThis,
                        Model model){

        List<Product> productList;

        if (pgName == null || pgName.isEmpty()){
            if (searchThis == null || searchThis.isEmpty()) {
                productList = pService.findAll();
            } else productList = pService.findAllBySearch("%"+searchThis+"%");
        }
        else productList = pService.findAllByProductGroupName(pgName);

        model.addAttribute("productList", productList);
        return "index";
    }

    ///////////////////////////////////////////////////////////////////



    @Autowired
    private ProductService pService;



    ///////////////////////////////////////////////////////////////////



    @ModelAttribute("loggedUser") public User loggedUser() { return new User(); }

    @ModelAttribute("passrecUser") public User passrecUser() { return new User(); }

    @ModelAttribute("regUser") public User regUser() { return new User(); }

}