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
        SomeEntity1 entity1 = SomeEntity1.builder().city("London").street("Green").build();
        SomeEntity2 entity2 = SomeEntity2.builder().name("Peter").someEntity1(entity1).build();

        SomeDto someDto = DtoUtils.convertToDto(SomeDto.class, entity1, entity2);

        if (someDto == null) {
            return;
        }

        someDto.getSomeEntity1().setCity("NY");
        System.out.println(someDto);
        System.out.println(entity1);
        System.out.println(entity2);

        SomeEntity1 someEntity1 = DtoUtils.convertToEntity(SomeEntity1.class, someDto);
        SomeEntity2 someEntity2 = DtoUtils.convertToEntity(SomeEntity2.class, someDto);
        System.out.println(someEntity1);
        System.out.println(someEntity2);
    }
}
