import java.util.HashMap;

public class Parameter{
   
   Type type;
   Id id;
   
   Parameter(Type t, Id i){
      type=t;
      id=i;
   }
   void printParseTree(String indent) {
      IO.displayln(indent + indent.length() + " <Parameter> " + type.type + " " + id.id); 
   }


   void emitInstructions() {
      
   }


   Val Eval(HashMap<String, Val> state) {
      return null;
      // TODO Auto-generated method stub
      
   }


}
