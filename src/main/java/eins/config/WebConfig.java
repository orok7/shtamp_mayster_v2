package eins.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("eins.*")
public class WebConfig extends WebMvcConfigurerAdapter{

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("/pages/img/");
        registry
                .addResourceHandler("/imgdb/**")
                .addResourceLocations("file:"+System.getProperty("user.home") + File.separator + "images" + File.separator);
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("/static/styles/");
        registry
                .addResourceHandler("/bs_css/**")
                .addResourceLocations("/static/bs_styles/css/");
        registry
                .addResourceHandler("/bs_js/**")
                .addResourceLocations("/static/bs_styles/js/");
        registry
                .addResourceHandler("/my_js/**")
                .addResourceLocations("/static/js/");
        registry
                .addResourceHandler("/bs/fonts/**")
                .addResourceLocations("/static/bs_styles/fonts/");
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("orok.java@gmail.com");
        mailSender.setPassword("javatest2017");
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        return mailSender;
    }
}