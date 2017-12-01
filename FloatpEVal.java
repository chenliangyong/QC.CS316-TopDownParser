class FloatpEVal extends Val
{
	float val;

	FloatpEVal(float f)
	{
		val = f;
	}

	public String toString()
	{
		return val+"";
	}

	Val cloneVal()
	{
		return new FloatpEVal(val);
	}

	float floatVal()
	{
		return val;
	}
	
	boolean isZero()
	{
		return val == 0.0f;
	}
}
