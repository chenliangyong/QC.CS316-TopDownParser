import java.util.*;

class Floatp extends ab_Exp
{
	float val;
	String type;
	
	Floatp(float f)
	{
		val = f;
		type = "Float";
	}

	void printParseTree(String indent)
	{
	      super.printParseTree(indent);
	      String indent1 = indent + " ";
	      IO.displayln(indent1 + indent1.length() + " " + val);
	}

	Val Eval(HashMap<String,Val> state)
	{
		return new FloatVal(val);
	}
	
	void emitInstructions()
	{
		IO.displayln("push " + val);
	}
}
