package eins.entity;

import eins.entity.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    private int discount;

    @Column(name = "is_company")
    private boolean isCompany;

    @CreationTimestamp
    @Column(name = "date_of_registration")
    private Timestamp dateOfRegistration;

    @Column(name = "timestamp_of_pass_rec")
    private Timestamp timestampOfPassRec;

    @Column(name = "pass_rec_code")
    private String passRecCode;

    @Column(unique = true)
    private String email;

    private String name;

    private String surname;

    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private CompanyUser companyData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer", cascade = CascadeType.MERGE)
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();


    ///////////////////////////////////////////////////////////////////////////
    //  Methods implementation for security
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors, getters, setters and another standard methods
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public Timestamp getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Timestamp dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public CompanyUser getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyUser companyData) {
        this.companyData = companyData;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public User(String email, String password, boolean isCompany,
                CompanyUser companyUser) {
        username = email;
        this.password = password;
        this.isCompany = isCompany;
        companyData = companyUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String username, String password,
                Role role, boolean accountNonExpired, boolean accountNonLocked,
                boolean credentialsNonExpired, boolean enabled,
                int discount, boolean isCompany, Timestamp dateOfRegistration,
                String email, String name, String surname, String phoneNumber,
                CompanyUser companyData, List<Invoice> invoices,
                List<Review> reviews) {

        this.username = username;
        this.password = password;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.discount = discount;
        this.isCompany = isCompany;
        this.dateOfRegistration = dateOfRegistration;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.companyData = companyData;
        this.invoices = invoices;
        this.reviews = reviews;
    }

    public Timestamp getTimestampOfPassRec() {
        return timestampOfPassRec;
    }

    public void setTimestampOfPassRec(Timestamp timestampOfPassRec) {
        this.timestampOfPassRec = timestampOfPassRec;
    }

    public String getPassRecCode() {
        return passRecCode;
    }

    public void setPassRecCode(String passRecCode) {
        this.passRecCode = passRecCode;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Special getter for spring forms
    ///////////////////////////////////////////////////////////////////////////

    public boolean getIsCompany() {
        return isCompany;
    }

}
