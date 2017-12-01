import java.util.*;

public class FunExp extends ab_Exp
{
	FunOP funop;
	ExpList explist; 

	FunExp(FunOP o, ExpList l)
	{
	   funop = o;
	   explist = l;
	}


   @Override
   void printParseTree(String indent) {
      super.printParseTree(indent);
      String indent1 = indent + " ";
      IO.displayln(indent1 + indent1.length() + " <fun exp>");
      funop.printParseTree(indent1+" ");
      if (explist != null)explist.printParseTree(indent1+" ");
      
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
