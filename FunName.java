import java.util.HashMap;

public class FunName extends ab_FunDefList
{
   Id id;
   public FunName(Id i){
      id = i;
   }

   void printParseTree(String indent) {
      IO.displayln(indent + indent.length() + " <FunName> " + id.id);
   }

   void Eval(HashMap<String, Val> state) {

   }

   void emitInstructions() {
      // TODO Auto-generated method stub
      
   }

}