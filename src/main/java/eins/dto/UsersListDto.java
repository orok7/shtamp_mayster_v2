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
    @Relation
    private int id;
    @Relation
    private String username;
    @Relation
    private String name;
    @Relation
    private Role role;
    @Relation
    private int discount;
    @Relation
    private boolean isCompany;
    @Relation
    private String email;
    @Relation
    private String phoneNumber;

    private UsersListDto(){}
}


