package core;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Lexical {
    
    StringBuilder toParse;
    int index;
     
    public Lexical (String toParse) {
        this.toParse = new StringBuilder(toParse);
        index = 0;
    }
    
    public Token next() {
        Token token = new Token();
        char character;
        if (index < toParse.length()) {
            character = toParse.charAt(index);
            if (Character.isDigit(character)) {
                token.setLexeme("" + character);
                token.setKind(Token.TokenKind.DIGIT);
            } else if (character == 'o' || character == 'O'
                    || character == 'h' || character == 'H'
                    || character == 'l' || character == 'L') {
                token.setLexeme("" + character);
                token.setKind(Token.TokenKind.BASE);
            } else if (character == 'a' || character == 'A'
                    || character == 'b' || character == 'B'
                    || character == 'c' || character == 'C'
                    || character == 'd' || character == 'D'
                    || character == 'e' || character == 'E'
                    || character == 'f' || character == 'F') {
                token.setLexeme("" + character);
                token.setKind(Token.TokenKind.DIGIT);
            } else {
                token.setLexeme("" + character);
                token.setKind(Token.TokenKind.ERROR);
            }
            index++;
            return token;
        } else{
            return null;
        }
    }
    
    public ArrayList parseAll(){
        ArrayList list = new ArrayList();
        Token token;
        do {
            token = next();
            if (token != null) {
                list.add(token);
            }
        } while (token != null);
        return list;
    }
}
