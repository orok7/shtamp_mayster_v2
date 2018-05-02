/*
 * SomeDto
 *
 * Version 1.0-SNAPSHOT
 *
 * 03.05.18
 *
 * All rights reserved by DoubleO Team (Team#1)
 * */

package eins.utils.dto.exmpl;

import eins.utils.dto.Relation;
import lombok.Data;

@Data
public class SomeDto {
    @Relation(className = SomeEntity1.class, fieldName = "name")
    private String name;
    @Relation(className = SomeEntity2.class, fieldName = "city")
    private String city;
}
