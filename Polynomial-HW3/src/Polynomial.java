import java.util.LinkedList;

public class Polynomial extends Term {
	protected String mPolyString;
	
	public Polynomial(int mExponent, int mCoefficient) {
		super(mExponent, mCoefficient);
		this.mPolyString = mPolyString;
	}
	
	public String getPolyString() {
			return mPolyString;
		}
	
	public void setPolyString(String mPolyString) {
			this.mPolyString = mPolyString;
		}
	
	LinkedList<Term> firstPolynomialList = new LinkedList<Term>();
	LinkedList<Term> secondPolynomialList = new LinkedList<Term>();
	static LinkedList<Term> resultPolynomialList = new LinkedList<Term>();

	public static void split(String polyString, LinkedList<Term> list) {
		if (polyString.length() <= 2 && !polyString.contains("x"))
			polyString += "x^0";
		if (polyString.length() <= 3 && polyString.contains("x") && !polyString.contains("^"))
			polyString += "^1";
		String[] temp = polyString.split("x\\^", -1); 
		int num1, num2;
		Term tempTerm;
		
        for (int i = 0; i < 1; i++) { 
        	num1 = Integer.parseInt(temp[i]);
        	num2 = Integer.parseInt(temp[i + 1]);
        	tempTerm = new Term(num1, num2);
        	list.add(tempTerm);
        }  
	}
	
	public static void add(LinkedList<Term> list1, LinkedList<Term> list2) {
		Term newTerm;
		int newCoefficient;
		boolean match = false;
		
		for(int i = 0; i < list1.size(); i++) {
			for(int j = 0; j < list2.size(); j++) {
				if (list1.get(i).mExponent == list2.get(j).mExponent) {
					newCoefficient = list1.get(i).mCoefficient + list2.get(j).mCoefficient;
					newTerm = new Term(newCoefficient, list1.get(i).mExponent);
					resultPolynomialList.add(newTerm);
					match = true;
				}
			}
			if (!match) {
				newCoefficient = list1.get(i).mCoefficient;
				newTerm = new Term(newCoefficient, list1.get(i).mExponent);
				resultPolynomialList.add(newTerm);
			}
			match = false;
		}
		
		for(int i = 0; i < list2.size(); i++) {
			for(int j = 0; j < list1.size(); j++) {
				if (list2.get(i).mExponent == list1.get(j).mExponent) {
					match = true;
				}
			}
			if (!match) {
				newCoefficient = list2.get(i).mCoefficient;
				newTerm = new Term(newCoefficient, list2.get(i).mExponent);
				resultPolynomialList.add(newTerm);
			}
			match = false;
		}
	}
	
	public static String print() {
		String output = "";
		for(int i = 0; i < resultPolynomialList.size(); i++) {
			if (i == resultPolynomialList.size() - 1)
				if (resultPolynomialList.get(i).mExponent == 0)
					output += resultPolynomialList.get(i).mCoefficient;
				else if(resultPolynomialList.get(i).mExponent == 1)
					output += resultPolynomialList.get(i).mCoefficient + "x";
				else
					output += resultPolynomialList.get(i).mCoefficient + "x^" + resultPolynomialList.get(i).mExponent;
			else
				if(resultPolynomialList.get(i).mExponent == 0)
					output += resultPolynomialList.get(i).mCoefficient + " + ";
				else if(resultPolynomialList.get(i).mExponent == 1)
					output += resultPolynomialList.get(i).mCoefficient + "x" + " + ";
				else
					output += resultPolynomialList.get(i).mCoefficient + "x^" + resultPolynomialList.get(i).mExponent + " + ";
		}
		return output;
	}

}
