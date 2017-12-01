import java.util.*;

class FunDef extends ab_FunDefList
{
	Header header; // variable on the left side of the assignment
	ab_Exp e;       // expression on the right side of the assignment

	FunDef(Header h, ab_Exp exp)
	{
		header = h;
		e = exp;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";			
		IO.displayln(indent + indent.length() + " <Fun Def>");
		header.printParseTree(indent1);
		//IO.displayln(indent1 + indent1.length() + " =");
		e.printParseTree(indent1);	
		IO.displayln("-------------------------------");
	}
	
	void Eval(HashMap<String,Val> state) // function to interpret this assignment
	{
		Val eVal = e.Eval(state); // evaluate expression e
		if ( eVal != null )
			state.put(header.funName.id.id, eVal); // assign the value eVal to id
		
		/* For practical reason of efficiency, the error state is not implemented.
		   Rather, the error state is implicitly assumed whenever Eval returns null representing
		   the runtime error value. */
	}
	
	void emitInstructions()
	{
		e.emitInstructions();
		IO.displayln("pop " + header.funName.id.id);
	}
}
