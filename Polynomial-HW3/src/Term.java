
public class Term implements Comparable {
	protected int mExponent;
	protected int mCoefficient;
	
	public Term(int mCoefficient, int mExponent) {
		super();
		this.mExponent = mExponent;
		this.mCoefficient = mCoefficient;
	}

	public int getExponent() {
		return mExponent;
	}

	public void setExponent(int mExponent) {
		this.mExponent = mExponent;
	}


	public int getCoefficient() {
		return mCoefficient;
	}

	public void setCoefficient(int mCoefficient) {
		this.mCoefficient = mCoefficient;
	}

	@Override
	public int compareTo(Object o) {
        if (o instanceof Term)
        {
        	Term other = (Term) o;
            int expComp = mExponent - other.mExponent;
            return expComp;
        }
        else
            return -1;
    }
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		if (mCoefficient != other.mCoefficient)
			return false;
		if (mExponent != other.mExponent)
			return false;
		return true;
	}
	
}
