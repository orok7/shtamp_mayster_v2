/*
 * Demo
 *
 * Version 1.0-SNAPSHOT
 *
 * 03.05.18
 *
* Written by Orok.Eins
 * */

package eins.utils.dto.exmpl;

import eins.utils.dto.DtoUtils;

public class Demo {
    public static void main(String[] args) {
        SomeEntity1 entity1 = SomeEntity1.builder().city("Lviv").street("Zelena").build();
        SomeEntity2 entity2 = SomeEntity2.builder().name("Vasya").build();
        SomeDto someDto = DtoUtils.convertToDto(SomeDto.class, entity1, entity2);
        if (someDto == null) {
            return;
        }
        System.out.println(someDto);
    }
}
