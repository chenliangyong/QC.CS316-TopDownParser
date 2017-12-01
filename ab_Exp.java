import java.util.*;

abstract class ab_Exp
{   
    //ab_Exp singleExp;
    //String type;


    void printParseTree(String indent){
       //String indent1 = indent + " ";     
       IO.displayln(indent + indent.length() + " <exp>");
       //singleExp.printParseTree(indent1);
    };
    abstract Val Eval(HashMap<String,Val> state); // function to interpret this assignment list
    abstract void emitInstructions();


}