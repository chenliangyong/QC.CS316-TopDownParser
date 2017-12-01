import java.util.*;

class Int extends ab_Exp
{
	int val;
	String type;

	Int(int i)
	{
		val = i;
		type = "Int";
	}

	void printParseTree(String indent)
	{
       super.printParseTree(indent);
       String indent1 = indent + " ";
       IO.displayln(indent1 + indent1.length() + " " + val);
	}

	Val Eval(HashMap<String,Val> state)
	{
		return new IntVal(val);
	}
	
	void emitInstructions()
	{
		IO.displayln("push " + val);
	}
}
