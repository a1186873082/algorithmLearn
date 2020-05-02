package source_read;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>(10);
        String[] strings = new String[1];
        for (int i = 0; i < 10; i++) {
            s.add("123");
        }
        System.out.println(s);
        String[] newStr = s.toArray(strings);
        for (String s1 : newStr) {
            System.out.printf(s1);
        }

    }
}
