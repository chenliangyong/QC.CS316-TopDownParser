import java.util.HashMap;

class BoolLiteral extends ab_Exp
{
    String val;
    String type;
    
    BoolLiteral(String str)
    {
        val = str;
        type = "BooLiteral";
    }

    void printParseTree(String indent)
    {
       super.printParseTree(indent);
       String indent1 = indent + " ";
       IO.displayln(indent1 + indent1.length() + " " + val);
    }

    Val Eval(HashMap<String,Val> state)
    {
        return null;//new BooleanVal(val);
    }
    
    void emitInstructions()
    {
        IO.displayln("push " + val);
    }
}
