import java.util.*;

public class Header
{
    Type type;
	FunName funName;
	ParameterList pList;
	
	public Header(Type t, FunName f, ParameterList pl){
	   type = t;
	   funName = f;
	   pList = pl;
	}

   void printParseTree(String indent) {
      String indent1 = indent + " ";
      IO.displayln(indent + indent.length() + " <Header>");
      type.printParseTree(indent1);
      funName.printParseTree(indent1);
      pList.printParseTree(indent1);
      
   }

   void Eval(HashMap<String, Val> state) {
      // TODO Auto-generated method stub
      
   }

   void emitInstructions() {
      // TODO Auto-generated method stub
      
   }

}