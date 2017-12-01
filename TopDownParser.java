public abstract class TopDownParser extends LexicalAnalyzer

{
   static boolean errorFound = false;

   // <fun def list> ¡ú <fun def> | <fun def <fun def list>
   public static ab_FunDefList funDefList() {
      FunDef fundef = fundef();
      if (state == State.Keyword_int || state == State.Keyword_float || state == State.Keyword_boolean) {
         ab_FunDefList funDefList = funDefList();
         return new MultipleFunDef(fundef, funDefList);
      } else
         return fundef;
   }

   // <fun def> ¡ú <header> = <exp>
   public static FunDef fundef() {
      Header header = header();
      if (state == State.EQ) {
         getToken();
         ab_Exp exp = exp();
         return new FunDef(header, exp);
      } else
         errorMsg(3);
      return null;
   }
   
   //<header> ¡ú <type><fun name><parameter list>
   public static Header header() {
      if (state == State.Keyword_int || state == State.Keyword_float || state == State.Keyword_boolean) {
         Type type = new Type(str);
         getToken();
         if (state == State.Id) {
            FunName funName = new FunName(new Id(str));
            getToken();
            ParameterList parametersList = parameterList();
            return new Header(type, funName, parametersList);
         } else {
            errorMsg(5);
         }
      }
      return null;
   }

   //<parameter list> ¡ú ¦Å | "(" <parameters> ")"
   public static ParameterList parameterList() {
      if (state == State.LParen) {
         getToken();
         Parameters parameters = parameters();
         return new ParameterList(parameters);
      } else {
         // empty
         return new ParameterList(null);
      }
   }

   //<parameters> ¡ú <parameter> | <parameter> "," <parameters>
   public static Parameters parameters() {
      Parameter parameter = parameter();
      if (state == State.RParen) {
         getToken();
         return new Parameters(parameter, null); // single parameter or end of parameters;
      }
      if (state == State.Comma) {
         getToken();
         Parameters parameters = parameters();
         return new Parameters(parameter, parameters);
      }

      return null;
   }

   //<parameter> ¡ú <type> <id>
   public static Parameter parameter() {
      if (state == State.Keyword_int || state == State.Keyword_float || state == State.Keyword_boolean) {
         Type type = new Type(str);
         getToken();
         if (state == State.Id) {
            Id id = new Id(str);
            getToken();
            return new Parameter(type, id);
         } else {
            errorMsg(5);
         }
      } else {
         errorMsg(2);
      }

      return null;
   }

   //<fun name> ¡ú <id>
   public static FunName funName() {
      if (state == State.Id) {
         FunName fn = new FunName(new Id(str));
         getToken();
         return fn;
      } else {
         errorMsg(5);
      }
      return null;
   }

   //<exp> ¡ú <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <fun exp> ")" | "if" <exp> "then" <exp> "else" <exp>
   public static ab_Exp exp() {      
      switch (state) {
      case Id:

         Id id = new Id(str);
         getToken();
         return id;

      case Int:

         Int intElem = new Int(Integer.parseInt(str));
         getToken();
         return intElem;

      case Float: case FloatE:

         Floatp floatElem = new Floatp(Float.parseFloat(str));
         getToken();
         return floatElem;

      //case Keyword_and: case Keyword_or: case Keyword_not:
      case Keyword_false: case Keyword_true:
         
         BoolLiteral boolLiteral = new BoolLiteral(str);
         getToken();
         return boolLiteral;
         
      case LParen:

         getToken();
         FunExp funexp = funexp();
         if (state == State.RParen)
         {
            getToken();
            return funexp;
         } else {
            errorMsg(1);
         }
              
      case Keyword_if:
         
         getToken();
         ab_Exp exp1 = exp();
         if (state == State.Keyword_then){
            getToken();
            ab_Exp exp2 = exp();
            if (state == State.Keyword_else){
               getToken();
               ab_Exp exp3 = exp();
               return new IfStatement(exp1,exp2,exp3);
            }
            else{
               errorMsg(6);
            }
         }
         else{
            errorMsg(6);
         }
      

      default:

         errorMsg(7);
         return null;
      }
   }
   
   //<fun exp> ¡ú <fun op> <exp list>
   public static FunExp funexp(){
      //System.out.println("-----" + state + " " + str);
      FunOP funop = funOp();
      if (state == State.RParen) return new FunExp(funop, null);
      ExpList explist = expList();
      return new FunExp(funop, explist);
   }
   
   //<fun op> ¡ú <fun name> | <arith op> | <bool op> | <comp op>
   public static FunOP funOp(){
      LexicalAnalyzer.State st;
      switch (state) {
      case Id:
         Id id = new Id(str);
         getToken();
         return new FunOP(new FunName(id));

      case Add: case Sub: case Mul: case Div:
         
         String arithStr = str;
         st = state;
         getToken();
         return new ArithOP(arithStr, st);    
         
      case Keyword_and: case Keyword_or: case Keyword_not:
         
         String boolStr = str;
         st = state;
         getToken();
         return new BoolOP(boolStr, st);    
         
      case LT: case LE: case GT: case GE: case EQ:
         
         String compStr = str;
         st = state;
         getToken();
         return new CompOP(compStr, st);    
 
      default:

         errorMsg(8);
         return null;
         }
   }   
   
   //<exp list> ¡ú ¦Å | <exp> <exp list>
   public static ExpList expList(){
      ab_Exp exp = exp();
      if (state == State.RParen) return new ExpList(exp, null);
      ExpList explist = expList();
      return new ExpList(exp, explist);
   }
   

   public static void errorMsg(int i) {
      errorFound = true;

      display(str + " : Syntax Error, unexpected symbol where");

      switch (i) {
      case 1:
         displayln(" arith op or ) expected");
         return;
      case 2:
         displayln(" int, float, or boolean expected");
         return;
      case 3:
         displayln(" = expected");
         return;
      case 4:
         displayln(" , expected");
         return;
      case 5:
         displayln(" id expected");
         return;
      case 6:
         displayln(" keyword then or else expected");
         return;
      case 7:
         displayln(" <exp> expected");
         return;
      case 8:
         displayln(" <fun exp> expected");
         return;
      }
   }

   public static void main(String argv[]) {
      // argv[0]: input file containing an assignment list
      // argv[1]: output file displaying the parse tree

      setIO(argv[0], argv[1]);

      getToken();

      ab_FunDefList funDefList = funDefList(); // build a parse tree
      if (!str.isEmpty())
         errorMsg(5);
      else if (!errorFound)
         funDefList.printParseTree(""); // print the parse tree in linearly
                                        // indented form in argv[1] file

      closeIO();
   }
}