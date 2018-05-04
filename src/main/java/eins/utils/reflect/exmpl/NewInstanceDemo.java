package eins.utils.reflect.exmpl;

import static eins.utils.reflect.InstanceUtils.newInstance;

public class NewInstanceDemo {

    public static void main(String[] args) {
        HasNoArgsConstructor hasNoArgsConstructor = newInstance(HasNoArgsConstructor.class);
        HasntNoArgsConstructor hasntNoArgsConstructor = newInstance(HasntNoArgsConstructor.class);
        System.out.println(hasNoArgsConstructor);
        System.out.println(hasntNoArgsConstructor);
    }

}
