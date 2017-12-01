import java.util.*;

class MultipleFunDef extends ab_FunDefList
{
	FunDef fundef;
	ab_FunDefList fundefList;

	MultipleFunDef(FunDef a, ab_FunDefList al)
	{
		fundef = a;
		fundefList = al;
	}
 
	void printParseTree(String indent)
	{		
		fundef.printParseTree(indent);
		fundefList.printParseTree(indent);
	}

	void Eval(HashMap<String,Val> state) // function to interpret this list of multiple assignments
	{
		fundef.Eval(state);
		fundefList.Eval(state);
	}
	
	void emitInstructions()
	{
		fundef.emitInstructions();
		fundefList.emitInstructions();
	}
}
