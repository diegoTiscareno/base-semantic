package core;

import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author diego
 */
public class Semantics {
    
    DefaultMutableTreeNode syntaxTree;

    
    public Semantics (DefaultMutableTreeNode tree) {
        syntaxTree = tree;
    }
    
    
    
    public int analyze () {
        DefaultMutableTreeNode baseNode = (DefaultMutableTreeNode) syntaxTree.getChildAt(1);
        Token baseToken = (Token) baseNode.getUserObject();
        int value, base;
        switch (baseToken.getLexeme().toUpperCase()) {
            case "O":
                base = 8;
                break;
            case "L":
                base = 10;
                break;
            default:
                base = 16;
                break;
        }
        
        DefaultMutableTreeNode number = (DefaultMutableTreeNode) syntaxTree.getChildAt(0);
        value = analyzeNum(number, base);
        syntaxTree.setUserObject(syntaxTree + " | base: " + base + " | value: " + ((value > 0)? value : "error"));
        return value;
    }
    
    private int analyzeNum (DefaultMutableTreeNode tree, int base) {
        if (!tree.isLeaf()) {
            int number1, number2, value;
            Token token;
            DefaultMutableTreeNode rest = (DefaultMutableTreeNode) tree.getFirstChild();
            number1 = analyzeNum(rest, base);
            token = (Token) tree.getUserObject();
            number2 = decimalValue(token, base);
            if (number1 != -1 && number2 != -1) {
                value = number1*base + number2;
            } else {
                value = -1;
            }
            tree.setUserObject(token + " | base: " + base + " | value: " + ((value > 0)? value : "error"));
            return value;
        } else {
            int value;
            Token token;
            token = (Token) tree.getUserObject();
            value = decimalValue(token, base);
            tree.setUserObject(token + " | base: " + base + " | value: " + ((value > 0)? value : "error"));
            return value;
        }
    }
    
    private int decimalValue(Token value, int base){
        int decvalue = 0;
        switch(value.getLexeme().toUpperCase()) {
            case "1":
                decvalue = 1;
                break;
            case "2":
                decvalue = 2;
                break;
            case "3":
                decvalue = 3;
                break;
            case "4":
                decvalue = 4;
                break;
            case "5":
                decvalue = 5;
                break;
            case "6":
                decvalue = 6;
                break;
            case "7":
                decvalue = 7;
                break;
            case "8":
                decvalue = 8;
                break;
            case "9":
                decvalue = 9;
                break;
            case "A":
                decvalue = 10;
                break;
            case "B":
                decvalue = 11;
                break;
            case "C":
                decvalue = 12;
                break;
            case "D":
                decvalue = 13;
                break;
            case "E":
                decvalue = 14;
                break;
            case "F":
                decvalue = 15;
        }
        
        if (decvalue >= base) {
            decvalue = -1;
        }
        return decvalue;
    }
}
