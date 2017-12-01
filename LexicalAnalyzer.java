/**
 
This class is a lexical analyzer for the tokens defined by the grammar:

<add>       --> +
<sub>       --> -
<mul>       --> *
<div>       --> /
<LParen>    --> "("
<RParen>    --> ")"
<lt>        --> "<"
<gt>        --> ">"
<le>        --> "<="
<ge>        --> ">="
<eq>        --> "="
<comma>     --> ","
<int> --> [+|-] { <digit> }+
<id> --> <letter> { <letter> | <digit> | '_' } { <letter> | <digit> }+
<float> --> [+|-] { <digit> } "." { <digit> }  
<floatE> --> <float> (E|e) [+|-] { <digit> }+

This class implements a DFA that will accept the above tokens.

The DFA states are represented by the Enum type "State".
The DFA has the following 10 final states represented by enum-type literals:

state     token accepted

Id        identifiers with underscore
Int       integers with [+/-]sign
Float     floats without exponentiation part and with [+/-]sign
FloatE    floats with exponentiation part and with [+/-]sign
Add       +
Sub       -
Mul       *
Div       /
LParen    (
RParen    )
LT        <
GT        >
LE        <=
GE        >=
EQ        =
Comma     ,

The DFA also uses the following 4 non-final states:

state      string recognized

Start      the empty string
Period     Start with [+/-]sign or Empty string then ending with "."
E          float parts ending with E or e
EPlusMinus float parts ending with + or - in exponentiation part
Underscore identifiers ending with underscore

The function "driver" operates the DFA. 
The function "nextState" returns the next state given the current state and the input character.

To recognize a different token set, modify the following:

  enum type "State" and function "isFinal"
  function "nextState" 

The functions "driver", "getToken" remain the same.

**/

public abstract class LexicalAnalyzer extends IO {

   public enum State {
      // non-final states and ordinal number

      Start, // 0
      Period, // 1
      E, // 2
      EPlusMinus, // 3
      UnderScore, // 4

      // final states and ordinal number

      Id, // 5
      Int, // 6
      Float, // 7
      FloatE, // 8
      Add, // 9
      Sub, // 10
      Mul, // 11
      Div, // 12
      LParen, // 13
      RParen, // 14
      LT, // 15
      GT, // 16
      LE, // 17
      GE, // 18
      EQ, // 19
      Comma, // 20

      // Keyword states and ordinal number

      Keyword_int, // 21
      Keyword_float, // 22
      Keyword_boolean, // 23
      Keyword_if, // 24
      Keyword_then, // 25
      Keyword_else, // 26
      Keyword_and, // 27
      Keyword_or, // 28
      Keyword_not, // 29
      Keyword_false, // 30
      Keyword_true, // 31

      // Undefined

      UNDEF;

      private boolean isFinal() {
         return (this.compareTo(State.Id) >= 0);
      }
   }

   public static String str;
   public static State state;

   private static int driver()

   // This is the driver of the FA.
   // If a valid token is found, assigns it to "str" and returns 1.
   // If an invalid token is found, assigns it to "str" and returns 0.
   // If end-of-stream is reached without finding any non-whitespace character,
   // returns -1.

   {
      State nextSt; // the next state of the FA
      // Initialization state and variables;
      str = "";
      state = State.Start;

      if (Character.isWhitespace((char) currVal))
         currVal = getChar(); // get the next non-whitespace character
      if (currVal == -1) // end-of-stream is reached
         return -1;

      while (currVal != -1) // do the body if "currVal" is not end-of-stream
      {
         currChar = (char) currVal;
         nextSt = nextState(state, currChar);
         if (nextSt == State.UNDEF) // The FA will halt.
         {
            if (state.isFinal())
               return 1; // valid token extracted
            else // "currChar" is an unexpected character
            {
               str = str + currChar;
               currVal = getNextChar();
               return 0; // invalid token found
            }
         } else // The FA will go on.
         {
            state = nextSt;
            str = str + currChar;
            currVal = getNextChar();
         }
      }
      // end-of-stream is reached while a token is being extracted
      if (state.isFinal())
         return 1; // valid token extracted
      else
         return 0; // invalid token found
   } // end driver

   public static void getToken()
   // Extract the next token using the driver of the FA.
   // If an invalid token is found, issue an error message.
   {
      int i = driver();
      if (i == 0)
         displayln(str + " : Lexical Error, invalid token");
   }

   private static State nextState(State s, char c)

   // Returns the next state of the FA given the current state and input char;
   // if the next state is undefined, UNDEF is returned.

   {
      switch (state) {
      case Start:
         if (Character.isLetter(c))
            return State.Id;
         else if (Character.isDigit(c))
            return State.Int;
         else if (c == '+')
            return State.Add;
         else if (c == '-')
            return State.Sub;
         else if (c == '*')
            return State.Mul;
         else if (c == '/')
            return State.Div;
         else if (c == '(')
            return State.LParen;
         else if (c == ')')
            return State.RParen;
         else if (c == '<')
            return State.LT;
         else if (c == '>')
            return State.GT;
         else if (c == '=')
            return State.EQ;
         else if (c == ',')
            return State.Comma;
         else if (c == '.') // Start with a period
            return State.Period;
         else
            return State.UNDEF;
      case LT:
         if (c == '=')
            return State.LE;
         else
            return State.UNDEF;
      case GT:
         if (c == '=')
            return State.GE;
         else
            return State.UNDEF;
      case Id:
         if (Character.isLetterOrDigit(c)) {
            // Check if it is one of the keywords
            if ((str + c).equals("int"))
               return State.Keyword_int;
            if ((str + c).equals("float"))
               return State.Keyword_float;
            if ((str + c).equals("boolean"))
               return State.Keyword_boolean;
            if ((str + c).equals("if"))
               return State.Keyword_if;
            if ((str + c).equals("then"))
               return State.Keyword_then;
            if ((str + c).equals("else"))
               return State.Keyword_else;
            if ((str + c).equals("and"))
               return State.Keyword_and;
            if ((str + c).equals("or"))
               return State.Keyword_or;
            if ((str + c).equals("not"))
               return State.Keyword_not;
            if ((str + c).equals("false"))
               return State.Keyword_false;
            if ((str + c).equals("true"))
               return State.Keyword_true;
            return State.Id;
         } else if (c == '_') // Accept identifiers with UnderScore
            return State.UnderScore;
         else
            return State.UNDEF;
      case UnderScore:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         else
            return State.UNDEF;
      case Add:
         if (Character.isDigit(c))
            return State.Int;
         else if (c == '.')
            return State.Period;
         else
            return State.UNDEF;
      case Sub:
         if (Character.isDigit(c))
            return State.Int;
         else if (c == '.')
            return State.Period;
         else
            return State.UNDEF;
      case Int:
         if (Character.isDigit(c))
            return State.Int;
         else if (c == '.')
            return State.Float;
         else if (c == 'e' || c == 'E')
            return State.E;
         else
            return State.UNDEF;
      case Period:
         if (Character.isDigit(c))
            return State.Float;
         else
            return State.UNDEF;
      case Float:
         if (Character.isDigit(c))
            return State.Float;
         else if (c == 'e' || c == 'E')
            return State.E;
         else
            return State.UNDEF;
      case E:
         if (Character.isDigit(c))
            return State.FloatE;
         else if (c == '+' || c == '-')
            return State.EPlusMinus;
         else
            return State.UNDEF;
      case EPlusMinus:
         if (Character.isDigit(c))
            return State.FloatE;
         else
            return State.UNDEF;
      case FloatE:
         if (Character.isDigit(c))
            return State.FloatE;
         else
            return State.UNDEF;
      case Keyword_int:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_float:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_boolean:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_if:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_then:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_else:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_and:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_or:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_not:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_false:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      case Keyword_true:
         if (Character.isLetterOrDigit(c))
            return State.Id;
         if (c == '_')
            return State.UnderScore;
         else
            return State.UNDEF;
      default:
         return State.UNDEF;
      }
   } // end nextState

   public static void main(String[] args) {
      if (args.length != 2) {
         System.out.println("Run as : java LexicalAnalyzer [inputFile] [outputFile]");
         return;
      }
      
      setIO(args[0], args[1]);
      int i;
      while (currVal != -1) // while "currVal" is not end-of-stream
      {
         i = driver(); // extract the next token
         if (i == 1)
            displayln(str + "   : " + state.toString());
         else if (i == 0)
            displayln(str + " : Lexical Error, invalid token");
      }
      closeIO();
   }

}
