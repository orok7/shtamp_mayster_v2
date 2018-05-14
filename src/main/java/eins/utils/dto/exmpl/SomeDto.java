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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Dto(appliedTo = SomeEntity2.class)
public class SomeDto implements DataTransferObject {
    @Relation(appliedTo = SomeEntity1.class)
    private String city;
    private String name;
    private SomeEntity1 someEntity1;
}
