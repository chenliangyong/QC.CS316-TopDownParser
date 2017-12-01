import java.util.*;

abstract class ab_FunDefList
{
	abstract void printParseTree(String indent);
	abstract void Eval(HashMap<String,Val> state); // function to interpret this assignment list
	abstract void emitInstructions();
}
