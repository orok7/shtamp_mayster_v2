package eins.utils.reflect.exmpl;

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
public class HasNoArgsConstructor extends Super {
    private String name;
    private int age;
    private boolean isMale;
}
