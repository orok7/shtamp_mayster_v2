package eins.utils.reflect.exmpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class HasntNoArgsConstructor {
    private String name;
    private int age;
    private boolean isMale;

    public HasntNoArgsConstructor(int age) {
        this.age = age;
    }
}
