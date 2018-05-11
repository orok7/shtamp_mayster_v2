/*
 * SomeDto
 *
 * Version 1.0-SNAPSHOT
 *
 * 03.05.18
 *
* Written by Orok.Eins
 * */

package eins.utils.dto.exmpl;

import eins.utils.dto.DataTransferObject;
import eins.utils.dto.Dto;
import eins.utils.dto.Relation;
import lombok.ToString;

@ToString
@Dto(appliedTo = SomeEntity2.class)
public class SomeDto implements DataTransferObject {
//    @Relation(appliedTo = SomeEntity1.class, fieldName = "city")
    @Relation(appliedTo = SomeEntity1.class)
    private String city;
//    @Relation(appliedTo = SomeEntity2.class, fieldName = "name")
//    @Relation(appliedTo = SomeEntity2.class)
//    @Relation(fieldName = "name")
    private String name;

    private SomeDto(){};
}
