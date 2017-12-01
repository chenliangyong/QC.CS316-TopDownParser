import java.util.*;

public class IfStatement extends ab_Exp
{
	ab_Exp exp1;
	ab_Exp exp2;
	ab_Exp exp3;

	IfStatement(ab_Exp e1, ab_Exp e2, ab_Exp e3)
	{
	   exp1=e1;
	   exp2=e2;
	   exp3=e3;
	}


   @Override
   void printParseTree(String indent) {
      super.printParseTree(indent);
      String indent1 = indent + " ";   
      IO.displayln(indent1 + indent1.length() + " if" );
      exp1.printParseTree(indent1+" ");
      IO.displayln(indent1 + indent1.length() + " then" );
      exp2.printParseTree(indent1+" ");
      IO.displayln(indent1 + indent1.length() + " else" );
      exp3.printParseTree(indent1+" ");
   }

   @Override
   Val Eval(HashMap<String, Val> state) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   void emitInstructions() {
      // TODO Auto-generated method stub
      
   }

}
