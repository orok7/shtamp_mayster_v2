package eins.dto;

import eins.entity.User;
import eins.entity.enums.Role;
import eins.utils.dto.Dto;
import eins.utils.dto.Relation;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Dto(appliedOnlyTo = User.class)
public class UsersListDto {
    @Relation(className = User.class, fieldName = "id")
    private int id;
    @Relation(className = User.class, fieldName = "username")
    private String username;
    @Relation(className = User.class, fieldName = "name")
    private String name;
    @Relation(className = User.class, fieldName = "role")
    private Role role;
    @Relation(className = User.class, fieldName = "discount")
    private int discount;
    @Relation(className = User.class, fieldName = "isCompany")
    private boolean isCompany;
    @Relation(className = User.class, fieldName = "email")
    private String email;
    @Relation(className = User.class, fieldName = "phoneNumber")
    private String phoneNumber;

    private UsersListDto(){}
}


