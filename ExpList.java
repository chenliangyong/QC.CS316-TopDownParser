import java.util.HashMap;

public class ExpList
{   
    ab_Exp exp;
    ExpList explist;
    
    ExpList(ab_Exp e,ExpList el){
       exp = e;
       explist = el;
    }
    
    void printParseTree(String indent){
       if (exp!=null) exp.printParseTree(indent);
       if (explist!=null) explist.printParseTree(indent);
    };
    Val Eval(HashMap<String,Val> state){
       return null;
    }; // function to interpret this assignment list
    void emitInstructions(){};
}