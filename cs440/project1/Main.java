import java.io.*;

public class Main {

    public static void main(String[] args) {
        XMLFile xml= new XMLFile("asdf", 123, "asdfdf");
        xml.print();
        System.out.println(xml.name);
        System.out.println(xml.size);
        System.out.println(xml.content);

    }

}
