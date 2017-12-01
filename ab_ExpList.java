import java.util.HashMap;

abstract class ab_ExpList
{   
    ab_Exp singleExp;

    abstract void printParseTree(String indent);
    abstract Val Eval(HashMap<String,Val> state); // function to interpret this assignment list
    abstract void emitInstructions();


}