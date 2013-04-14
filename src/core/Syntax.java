package core;

import java.util.List;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author diego
 */
public class Syntax {
    
    private List tokenList;
    private Token currentToken;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode currentNode;
    private List errorList;
    private int index;
    
    public Syntax (List tokenList) {
        this.tokenList = tokenList;
        root = new DefaultMutableTreeNode();
        currentNode = root;
        index = 0;
        currentToken = (Token) tokenList.get(index);
        errorList = new ArrayList();
    }
    
    //basenum -> number base
    private void basenum() {
        number();
        base();
    }
    
    //number -> number digit | digit
    //number -> {digit}
    private void number() {
        while (digit()) {
        
        }
    }
    
    private boolean digit(){
        if (match(Token.TokenKind.DIGIT)) {
            DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(currentToken);
            currentNode.add(newChild);
            currentNode = newChild;
            advance();
            return true;
        } else {
            return false;
        }
    }
    
    private void base() {
    
    }
    
    private boolean match(Token.TokenKind kind) {
        return (currentToken.getKind() == kind)? true : false;
    }
    
    private void advance() {
        index++;
        currentToken = (Token) tokenList.get(index);
    }
}
