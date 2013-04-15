package core;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
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
    private boolean misformedTree;
    
    public Syntax (List tokenList) {
        this.tokenList = tokenList;
        root = new DefaultMutableTreeNode();
        currentNode = root;
        index = 0;
        if (!tokenList.isEmpty()) {
            currentToken = (Token) tokenList.get(index);
        }
        errorList = new ArrayList();
    }
    
    //basenum -> number base
    private void basenum() {
        currentNode.setUserObject("Base-Number"); 
        number();
        base();
    }
    
    //number -> number digit | digit
    //number -> digit {digit}
    private void number() {
        DefaultMutableTreeNode turningPoint = currentNode; 
        if (digit()) {
            while (digit()) {
                
            }
            currentNode = turningPoint;
            turningPoint = (DefaultMutableTreeNode) turningPoint.getFirstChild();
            Enumeration num = turningPoint.depthFirstEnumeration();
            DefaultMutableTreeNode realNumTree = new DefaultMutableTreeNode("Number");
            while(num.hasMoreElements()){
                realNumTree.getFirstLeaf().add((DefaultMutableTreeNode)num.nextElement());
            }
            currentNode.add((DefaultMutableTreeNode)realNumTree.getFirstChild()); 
        } else {
            errorList.add("There must be at least one number"); 
            misformedTree = true;
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
        if (match (Token.TokenKind.BASE)) {
            DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(currentToken);
            currentNode.add(newChild);
            currentNode = newChild;
            advance();
        } else {
            errorList.add("The number must specify a base.");
            misformedTree = true;
        }
    
    }
    
    private boolean match(Token.TokenKind kind) {
        return (currentToken.getKind() == kind)? true : false;
    }
    
    private void advance() {
        index++;
        if (index < tokenList.size()){
            currentToken = (Token) tokenList.get(index);
        } else {
            currentToken = new Token();
            currentToken.setKind(Token.TokenKind.END);
        }     
    }
    
    public DefaultMutableTreeNode getRoot() {
        return this.root;
    }
    
    public List getErrorList(){
        return this.errorList;
    }
    
    public boolean parse(){
        if (!tokenList.isEmpty()){
            basenum();
            return true;
        }
        return false;
    }

    public boolean isMisformedTree() {
        return misformedTree;
    }

    public void setMisformedTree(boolean misformedTree) {
        this.misformedTree = misformedTree;
    }
    
    
}
