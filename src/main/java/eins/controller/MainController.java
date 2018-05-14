package eins.controller;

import eins.entity.Product;
import eins.entity.Review;
import eins.entity.User;
import eins.service.ProductService;
import eins.service.ReviewService;
import eins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "redirect:/main/index";
    }

    @GetMapping("/main/productPage{id}")
    public String productPage(@PathVariable("id") long id,
                              Model model){

        Product product = pService.findOne(id);

        List<Review> reviews = reviewService.findAllByProductIdWithUsers(id);
        double sum = reviews.stream().mapToDouble(o -> o.getRating()).sum();
        int size = reviews.size();
        double avrgRating = (size == 0) ? 0 : (sum / size);
        product.setRating(avrgRating);
        product.setNumberOfRatings(size);
        pService.save(product);

        model.addAttribute("ratingAvrgText", new DecimalFormat("###.##").format(avrgRating));
        model.addAttribute("reviews", reviews);
        model.addAttribute("product", product);
        return "productPage";
    }

    @PostMapping("/main/productPage/addReview")
    public String addReview(Principal principal,
                             @RequestParam String ratingVal,
                             @RequestParam String reviewText,
                             @RequestParam String prodId){

        String userName = (principal == null) ? "anonymous" : principal.getName();
        User user = uService.findByUsername(userName);

        Product product = pService.findOne(Integer.valueOf(prodId));

        Double rating = Double.valueOf(ratingVal);

        int num = product.getNumberOfRatings();
        double avrRating = (product.getRating() * num + rating) / (num + 1);
        product.setRating(avrRating);
        product.setNumberOfRatings(num + 1);
        pService.save(product);

        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReviews(reviewText);
        review.setRating(rating);
        reviewService.save(review);

        return "redirect:/main/productPage"+prodId;
    }


    ////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private ProductService pService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService uService;
}
