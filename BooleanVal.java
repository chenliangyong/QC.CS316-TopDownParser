class BooleanVal extends Val
{
    boolean val;

    BooleanVal(boolean i)
    {
        val = i;
    }

    public String toString()
    {
        return val+"";
    }

    Val cloneVal()
    {
        return new BooleanVal(val);
    }

    boolean isZero()
    {
       return val == false;
    }

   float floatVal() {
      if (val!=true){
         return 0;
      }
      return 1;
   }
}
