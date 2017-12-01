import java.util.HashMap;

public class Parameters{
   
   Parameter Single_parameter;
   Parameters M_Parameter;
   Parameters(Parameter p, Parameters Mp){
      Single_parameter=p;
      M_Parameter=Mp;
   }
   
   void printParseTree(String indent) {
      String indent1 = indent + " ";
      Single_parameter.printParseTree(indent1);
      if (M_Parameter!=null) M_Parameter.printParseTree(indent);
   }

   Val Eval(HashMap<String, Val> state) {
      return null;
      // TODO Auto-generated method stub
      
   }

   void emitInstructions() {
      // TODO Auto-generated method stub
      
   }


}
