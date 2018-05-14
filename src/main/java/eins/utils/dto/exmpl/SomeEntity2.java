/*
 * SomeEntity1
 *
 * Version 1.0-SNAPSHOT
 *
 * 03.05.18
 *
* Written by Orok.Eins
 * */

package eins.utils.dto.exmpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SomeEntity2{
    private String name;
    private SomeEntity1 someEntity1;
}
