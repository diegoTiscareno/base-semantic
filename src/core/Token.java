package core;

/**
 *
 * @author diego
 * The only possible tokens in this case are digit and base
 * 
 */
public class Token {
    
    public static enum TokenKind {
        DIGIT, BASE, ERROR
    }
    
    private String lexeme;
    private TokenKind kind;
    
    public Token(){
    
    }
    
    /**
     * @param lexeme the lexeme to set
     * @param kind the kind if this token
     */
    public Token(String lexeme, TokenKind kind) {
        this.lexeme = lexeme;
        this.kind = kind;
    }
    
    /**
     * @return the lexeme
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * @param lexeme the lexeme to set
     */
    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    /**
     * @return the kind
     */
    public TokenKind getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(TokenKind kind) {
        this.kind = kind;
    }
    
    @Override
    public String toString() {
        return "" + kind + ": " + lexeme;
    }

}
