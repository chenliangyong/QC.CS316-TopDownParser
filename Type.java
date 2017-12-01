import java.util.HashMap;

public class Type
{
   String type;
   Type(String t){
      type = t;
   }

   void printParseTree(String indent) {
      //String indent1 = indent + " ";
      IO.displayln(indent + indent.length() + " <Type> " + type);
   }

   void M(HashMap<String, Val> state) {

   }

   void emitInstructions() {

   }

}