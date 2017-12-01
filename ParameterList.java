import java.util.HashMap;

public class ParameterList
{   
    Parameters parameters;
    ParameterList(Parameters ps){
       parameters=ps;
    }

    void printParseTree(String indent){
       if (parameters == null ) return ;
       IO.displayln(indent + indent.length() + " <Parameter List>");
       parameters.printParseTree(indent);
    };
    Val Eval(HashMap<String,Val> state){
       return null;
    }; // function to interpret this assignment list
    void emitInstructions(){};


}