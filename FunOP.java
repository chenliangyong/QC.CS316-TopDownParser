import java.util.*;

public class FunOP
{
    FunName funName;
    LexicalAnalyzer.State s;
    String str;
    FunOP(String t, LexicalAnalyzer.State state){
       str = t;
       s = state;
    }
    FunOP(FunName f){
       funName=f;
    }

   void printParseTree(String indent){
      //String indent1 = indent + " ";
      if (funName!=null) IO.displayln(indent + indent.length() + " " + funName.id.id);
      if (str != null) IO.displayln(indent + indent.length() + " " + str);
      
   };

   void Eval(HashMap<String, Val> state){

   }; 

   void emitInstructions(){};
    
}
