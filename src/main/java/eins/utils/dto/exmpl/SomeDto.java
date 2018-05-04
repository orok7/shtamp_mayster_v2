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
import eins.utils.dto.Relation;
import lombok.ToString;

@ToString
public class SomeDto implements DataTransferObject {
    @Relation(className = SomeEntity1.class, fieldName = "city")
    private String city;
    @Relation(className = SomeEntity2.class, fieldName = "name")
    private String name;

    private SomeDto(){};
}
