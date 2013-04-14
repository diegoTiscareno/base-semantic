package core;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Test {
    public static void main(String args[]){
        Lexical lex = new Lexical("23hfsd23dls");
        ArrayList list = lex.parseAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
