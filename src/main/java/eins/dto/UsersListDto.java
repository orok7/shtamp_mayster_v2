package eins.dto;

import eins.entity.User;
import eins.entity.enums.Role;
import eins.utils.dto.Dto;
import eins.utils.dto.Relation;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Dto(appliedTo = User.class)
public class UsersListDto {
    private long id;
    private String username;
    private String name;
    private Role role;
    private int discount;
    private boolean isCompany;
    private String email;
    private String phoneNumber;

    private UsersListDto(){}
}


