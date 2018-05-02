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
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SomeDto someDto = DtoUtils.convertToDto(null, SomeDto.class);
    }
}
