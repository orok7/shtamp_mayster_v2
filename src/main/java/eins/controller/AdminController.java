package eins.controller;

import eins.entity.*;
import eins.entity.enums.InvoiceStatus;
import eins.entity.enums.MeasurementUnits;
import eins.entity.enums.PaymentType;
import eins.service.InvoiceService;
import eins.service.ProductGroupService;
import eins.service.ProductService;
import eins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService uService;
    @Autowired
    private ProductService pService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ProductGroupService pgService;

    @PostMapping("/saveProduct")
    public String saveProduct(@RequestParam String nameProduct,
                              @RequestParam String idProduct,
                              @RequestParam String productProductGroup,
                              @RequestParam String articleProduct,
                              @RequestParam String productMeasurementUnits,
                              @RequestParam String priceProduct,
                              @RequestParam String descriptionProduct,
                              @RequestParam MultipartFile picture,
                              Model model) throws IOException {
        int id = 0;
        try {
            id = Integer.valueOf(idProduct);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: try to parse: \"" + idProduct + "\"");
        }

        Product product = (id == 0) ? new Product() : pService.findOne(id);
        product.setName(nameProduct);
        product.setId(id);
        product.setGroup(pgService.findOne(Integer.valueOf(productProductGroup)));
        product.setArticle(articleProduct);
        product.setMeasurementUnits(MeasurementUnits.valueOf(productMeasurementUnits));
        product.setPrice(Double.valueOf(priceProduct));
        product.setDescription(descriptionProduct);

        String originalFilename = picture.getOriginalFilename();
        if (!originalFilename.isEmpty()) {
            String fileName = "" + (originalFilename + articleProduct).hashCode()
                    + originalFilename.substring(originalFilename.lastIndexOf("."));
            if (id == 0 || !product.getMainPicture().equals("/imgdb/" + fileName)) {
                String realpath = System.getProperty("user.home") + File.separator + "images" + File.separator;
                picture.transferTo(new File(realpath + fileName));
                product.setMainPicture("/imgdb/" + fileName);
            }
        }

        try {
            pService.save(product);
        } catch (Exception e) {
            model.addAttribute("errorAdminPage", "Продукт з таким артикулом вже існує");
        }
        return "redirect:/admin/newProduct";
    }


    @PostMapping("/saveUser")
    public String savePersonal(@RequestParam String urId,
                               @RequestParam(required = false) String urOwnership,
                               @RequestParam(required = false) String urFullName,
                               @RequestParam(required = false) String urShortName,
                               @RequestParam(required = false) String urCode,
                               @RequestParam String urName,
                               @RequestParam String urSurname,
                               @RequestParam String urPhoneNumber,
                               @RequestParam String urEmail,
                               @RequestParam String urDiscount,
                               @RequestParam(required = false) String urAccountNonExpired,
                               @RequestParam(required = false) String urAccountNonLocked,
                               @RequestParam(required = false) String urCredentialsNonExpired,
                               @RequestParam(required = false) String urEnabled) {

        User user = uService.findOneWithCompanyData(Integer.valueOf(urId));
        if (user == null) {
            return "redirect:/admin/listUser";
        }

        user.setName(urName);
        user.setSurname(urSurname);
        user.setPhoneNumber(urPhoneNumber);
        user.setEmail(urEmail);
        user.setDiscount(Integer.valueOf(urDiscount));
        user.setAccountNonExpired(urAccountNonExpired != null);
        user.setAccountNonLocked(urAccountNonLocked != null);
        user.setCredentialsNonExpired(urCredentialsNonExpired != null);
        user.setEnabled(urEnabled != null);

        if (!user.isCompany()) {
            uService.save(user);
            return "redirect:/admin/listUser";
        }

        CompanyUser cUser = user.getCompanyData();
        cUser.setOwnership(urOwnership);
        cUser.setFullName(urFullName);
        cUser.setShortName(urShortName);
        cUser.setCode(urCode);

        uService.save(user, cUser);

        return "redirect:/admin/listUser";
    }

    @GetMapping("/listProduct")
    public String listProduct(Model model) {
        List<Product> list = pService.findAllWithGroups();
        list.sort(Comparator.comparing(Product::getName));
        model.addAttribute("productList", list);
        return "productList";
    }

    @GetMapping("/listUser")
    public String listUser(Model model) {
        List<User> list = uService.findAll();
        list.sort(Comparator.comparing(User::getUsername));
        model.addAttribute("userList", list);
        return "userList";
    }

    @GetMapping("/listInvoice")
    public String listInvoice(Model model) {
        List<Invoice> list = invoiceService.findAllWithBuyer();
        list.sort(Comparator.comparing(Invoice::getDate));
        String[] paymentTypes = (String[]) Arrays.stream(PaymentType.values()).map(PaymentType::toString).toArray();
        model.addAttribute("paymentTypes", paymentTypes);
        String[] statuses = (String[]) Arrays.stream(InvoiceStatus.values()).map(InvoiceStatus::toString).toArray();
        model.addAttribute("invoiceStatus", statuses);
        model.addAttribute("listInvoice", list);
        return "invoiceList";
    }

    @PostMapping("/saveInvoice{id}")
    public String saveInvoice(@PathVariable("id") int id,
                              @RequestParam String odPayed,
                              @RequestParam String odInvoicePaymentTypes,
                              @RequestParam String odInvoiceStatus) {

        Invoice invoice = invoiceService.findOne(id);
        invoice.setPayed(Double.valueOf(odPayed));
        invoice.setPaymentType(PaymentType.valueOf(odInvoicePaymentTypes));
        invoice.setStatus(InvoiceStatus.valueOf(odInvoiceStatus));
        invoiceService.save(invoice);

        return "redirect:/admin/listInvoice";
    }

    @GetMapping("/modifyInvoice{id}")
    public String modifyInvoice(@PathVariable("id") int id,
                                Model model) {
        Invoice invoice = invoiceService.findOneWithProducts(id);
        model.addAttribute("invoiceProducts", invoice.getProducts());
        model.addAttribute("invoiceSum", invoice.getSum());
        return "invoiceDetails";
    }

    @GetMapping("/modifyUser{id}")
    public String modifyUser(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", uService.findOneWithCompanyData(id));
        return "userDetails";
    }

    @GetMapping("/newProduct")
    public String newProduct(@RequestParam(required = false) String errorAdminPage,
                             Model model) {
        List<ProductGroup> productGroups = pgService.findAll();
        if (errorAdminPage != null) {
            model.addAttribute("errorAdminPage", errorAdminPage);
        }
        model.addAttribute("productGroups", productGroups);
        model.addAttribute("measurementUnits", MeasurementUnits.values());
        return "newProduct";
    }

    @GetMapping("/modifyProduct{id}")
    public String modifyProduct(@PathVariable("id") int id, Model model) {

        Product product = pService.findOneWithGroup(id);

        List<ProductGroup> productGroups = pgService.findAll();
        model.addAttribute("productGroups", productGroups);
        model.addAttribute("productProductGroupId", product.getGroup().getId());
        model.addAttribute("measurementUnits", MeasurementUnits.values());
        model.addAttribute("measurementUnitSel", product.getMeasurementUnits());

        model.addAttribute("productId", id);
        model.addAttribute("productName", product.getName());
        model.addAttribute("productArticle", product.getArticle());
        model.addAttribute("productPrice", product.getPrice());
        model.addAttribute("productDescription", product.getDescription());
        model.addAttribute("productMainPicture", product.getMainPicture());
        return "newProduct";
    }

    @GetMapping("/removeProduct{id}")
    public String removeProduct(@PathVariable("id") int id) {
        pService.remove(id);
        return "redirect:/admin/listProduct";
    }

    @PostMapping("/saveProductGroup")
    public String saveProductGroup(@RequestParam String nameProductGroup,
                                   @RequestParam String idProductGroup,
                                   Model model) {
        int id = 0;
        try {
            id = Integer.valueOf(idProductGroup);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: try to parse: \"" + idProductGroup + "\"");
        }
        try {
            pgService.save(new ProductGroup(id, nameProductGroup));
        } catch (Exception e) {
            model.addAttribute("errorAdminPage", "Група з такою назвою вже існує");
        }
        return "/newProductGroup";
    }

    @GetMapping("/listProductGroup")
    public String listProductGroup(Model model) {
        List<ProductGroup> list = pgService.findAll();
        model.addAttribute("productGroupList", list);
        return "/productGroupList";
    }

    @GetMapping("/newProductGroup")
    public String newProductGroup() {
        return "/newProductGroup";
    }

    @GetMapping("/modifyProductGroup{id}")
    public String modifyProductGroup(@PathVariable("id") int id, Model model) {
        model.addAttribute("productGroupId", id);
        model.addAttribute("productGroupName", pgService.findOne(id).getName());
        return "/newProductGroup";
    }

    @GetMapping("/removeProductGroup{id}")
    public String removeProductGroup(@PathVariable("id") int id) {
        pgService.remove(id);
        return "redirect:/admin/listProductGroup";
    }


    @GetMapping("/adminPage")
    public String adminPage() {
        return "adminPage";
    }

}
