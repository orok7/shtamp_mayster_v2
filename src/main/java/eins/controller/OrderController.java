package eins.controller;

import eins.entity.Invoice;
import eins.entity.enums.PaymentType;
import eins.entity.ProductToBuy;
import eins.entity.User;
import eins.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, Model model){
        model.addAttribute("listProd", getProductsToBuy(request));
        return "cart";
    }

    @GetMapping("/cart/remProd{prodId}")
    public String remProdFromCart(@PathVariable("prodId") int prodId,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("prodid_")) {
                int id = Integer.valueOf(cookie.getName().split("prodid_")[1]);
                if (id == prodId) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "redirect:/order/cart";
    }

    @GetMapping("/cart/remAllProd")
    public String remAllProdFromCart(HttpServletRequest request,
                                  HttpServletResponse response){
        clearProductsToBuy(request,response);
        return "redirect:/";
    }

    @GetMapping("/newOrder")
    public String newOrder(HttpServletRequest request, Model model){
        model.addAttribute("listProd", getProductsToBuy(request));
        String[] paymentTypes = (String[]) Arrays.stream(PaymentType.values()).map(PaymentType::toString).toArray();
        model.addAttribute("paymentTypes", paymentTypes);
        return "newOrder";
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam(required = false) String odName,
                           @RequestParam(required = false) String odSurname,
                           @RequestParam(required = false) String odPhoneNumber,
                           @RequestParam String odInvoicePaymentTypes,
                           @RequestParam String odNotes,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Principal principal) {

        List<ProductToBuy> listPB = getProductsToBuy(request);
        if (listPB == null) return "redirect:/";

        String userName = (principal == null) ? "anonymous" : principal.getName();
        if (principal == null) odNotes+=". Name " + odName + " Surname " + odSurname + " Phone " + odPhoneNumber;

        User user = uService.findByUsername(userName);

        Invoice invoice = new Invoice();
        invoice.setBuyer(user);
        invoice.setDate(new Date());
        invoice.setNote(odNotes);
        invoice.setPaymentType(PaymentType.valueOf(odInvoicePaymentTypes));
        double sum = 0;
        for (ProductToBuy pb : listPB) sum += pb.getProduct().getPrice()*pb.getNumber();
        invoice.setSum(sum-(user.getDiscount()*sum));

        invoiceService.save(invoice);

        listPB.forEach( o -> { o.setInvoice(invoice); pbService.save(o); });

        invoice.setProducts(listPB);

        invoiceService.save(invoice);

        invoiceService.findAllWithProducts().forEach(o -> {
            System.out.println(o.getId());
            System.out.println(o.getSum());
            o.getProducts().forEach(p -> System.out.println(p.getNumber()));
            o.getProducts().forEach(p -> System.out.println(p.getProduct().getPrice()));
        });

        String subject = "New order from " + user.getName();

        String text = "Sum of order " + invoice.getSum();

        mailService.sendNewOrder("orok.java@gmail.com", subject, text);

        clearProductsToBuy(request,response);

        return "redirect:/";
    }

    /////////////////////////////////////////////////////////////////////////////////////


    public List<ProductToBuy> getProductsToBuy(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<ProductToBuy> productToBuy = new ArrayList<>();
        for (Cookie cookie : cookies) {
            if (cookie.getName().startsWith("prodid_")) {
                int id = Integer.valueOf(cookie.getName().split("prodid_")[1]);
                int num = Integer.valueOf(cookie.getValue());
                ProductToBuy pB = new ProductToBuy();
                pB.setProduct(pService.findOne(id));
                pB.setNumber(num);
                productToBuy.add(pB);
            }
        }
        return productToBuy;
    }

    public void clearProductsToBuy(HttpServletRequest request,
                                   HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().startsWith("prodid_")) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private ProductService pService;

    @Autowired
    private ProductToBuyService pbService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService uService;

    @Autowired
    private MailService mailService;
}
