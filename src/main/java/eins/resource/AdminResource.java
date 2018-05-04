package eins.resource;

import eins.dto.UsersListDto;
import eins.entity.Product;
import eins.entity.User;
import eins.service.InvoiceService;
import eins.service.ProductGroupService;
import eins.service.ProductService;
import eins.service.UserService;
import eins.utils.dto.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/admin")
public class AdminResource {

    @Autowired
    private UserService uService;
    @Autowired
    private ProductService pService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ProductGroupService pgService;

    @GetMapping("/getProducts")
    public List<Product> getProducts() {
        return pService.findAllWithGroups();
    }

    @GetMapping("/getUsers")
    public List<UsersListDto> getUsers() {
        List<User> users = uService.findAll();
        if (users == null) return null;
        return users.stream()
                .map(user -> DtoUtils.convertToDto(UsersListDto.class, user))
                .collect(Collectors.toList());
    }

}
