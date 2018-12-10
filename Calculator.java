import java.util.Scanner;
//WHAT THIS PROGRAM CAN DO:   
//Can check and print inputed fractions (mixed numbers and regular) 
//Can print whole numbers
//Can check if improper fraction  
//Can simplify improper fractions, mixed numbers, and regular fractions (Negatives and Positives) 
//Can print out improper fractions as Mixed number or just a whole number   

//ERRORS:
/*
Can't handle BigInteger numbers 
prints 0 726/-289047246 on 22/246813 * 33/51034
*/

/*TEST CASES: 
1/2 + 1/3		5/6 OK
1/2 + 1/2		1 OK
3/4 - 3/4		0 OK
-1/2 + 1/2		0 OK
34_5/6 - 1_2/3		33_1/6 OK
756/234 + 123/789		3_1322/3419 OK
1_1/2 * 3/4		 1_1/8 OK
543/987 - 5_67/89		-5_5934/29281 OK
543/987 / 5_67/89		16109/168448 OK
543/987 * 5_67/89		3_4829/29281 OK
543/987 + 5_67/89		6_8871/29281 OK
22/246813 * 33/51034		121/2099309107 broken 
3/4 / 1/2		1_1/2 OK
-3/4 / 1/2		-1_1/2 OK
3_5/6 / 34/12		1_6/17 OK
3_5/6 * 34/12		10_31/36 OK
33/7 * -1/3		-1_4/7 OK
54/321 - 23/67		-1255/7169 OK
4/8 - 1/3		1/6 OK
6/9 - 3_2/3		-3 OK
1 + 1

5/8	^	5/6	THIS ONE WORKS!!	Try catch 
 */

public class Calculator {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String numerator = null, denominator = null;
		String operator = null, fraction2 = null, fraction1 = null, wholenum = null;
		boolean runprogram = true;
		do {
			System.out.println(
					"Enter an equation (QUIT to stop) (Please enter mixed nums with a \"_\" between whole num and fraction!)");
			String choice = input.nextLine(); // gets user input
			boolean UserWantsToQuit = choice.equals("QUIT");
			if (UserWantsToQuit) {
				runprogram = false;
				break;
			} else { // user enters an equation
				Scanner parse = new Scanner(choice); // scans the equation for individual elements
				fraction1 = parse.next(); // gets first fraction
				if (fraction1.contains("_")) { // Checks if there is a whole number
					wholenum = fraction1.substring(0, fraction1.indexOf("_"));
					fraction1 = improperfraction(wholenum, fraction1);
				}
				operator = parse.next(); // gets operator
				fraction2 = parse.next(); // gets next fraction
				if (fraction2.contains("_")) { // Checks if there is a whole number
					wholenum = fraction2.substring(0, fraction2.indexOf("_"));
					fraction2 = improperfraction(wholenum, fraction2);
					if (fraction1.contains("_") || fraction2.contains("_")) {
						System.out.println(fraction1 + " " + operator + " " + fraction2);
					}
				} else if (!fraction1.contains("/") && !fraction2.contains("/")) {
					int whole = Integer.valueOf(fraction1);
					int whole2 = Integer.valueOf(fraction2);
					if (whole < 0 && whole2 < 0) {
						System.out
								.println(fraction1.substring(0, 2) + " " + operator + " " + fraction2.substring(0, 2));
						numerator = fraction1.substring(0);
						denominator = "1";
						fraction1 = (numerator + "/" + denominator);
						numerator = fraction2.substring(0);
						denominator = "1";
						fraction2 = (numerator + "/" + denominator);
					} else {
						System.out.println(fraction1.substring(0) + " " + operator + " " + fraction2.substring(0));
						numerator = fraction1.substring(0);
						denominator = "1";
						fraction1 = (numerator + "/" + denominator);
						numerator = fraction2.substring(0);
						denominator = "1";
						fraction2 = (numerator + "/" + denominator);
					}
				} else if (!fraction1.contains("/")) {
					System.out.println(fraction1.substring(0) + " " + operator + " " + fraction2);
					numerator = fraction1.substring(0);
					denominator = "1";
					fraction1 = (numerator + "/" + denominator);
				} else if (!fraction2.contains("/")) {
					System.out.println(fraction1 + " " + operator + " " + fraction2.substring(0));
					numerator = fraction2.substring(0);
					denominator = "1";
					fraction2 = (numerator + "/" + denominator);
				} else {
					System.out.println(fraction1 + " " + operator + " " + fraction2);
				}
				if (operator.equals("+")) {
					String total = add(fraction1, fraction2);
					simplify(total);
				} else if (operator.equals("-")) {
					String total = subtract(fraction1, fraction2);
					simplify(total); // Check negatives?
				} else if (operator.equals("*")) {
					String product = multiply(fraction1, fraction2);
					simplify(product);
				} else if (operator.equals("/")) {
					String quotient = divide(fraction1, fraction2);
					simplify(quotient);
				} else {
					try {
						throw new Exception("Invalid Operators");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println();
			}
		} while (runprogram);
		System.out.println("quitting...");
	}

	public static String improperfraction(String wholenum, String fraction) {
		int wholenum1 = Integer.valueOf(wholenum);
		if (wholenum1 < 0) {
			Scanner num = new Scanner(fraction.substring(fraction.indexOf("_") + 1)).useDelimiter("/");
			int numerator = num.nextInt();
			int denominator = num.nextInt();
			numerator = denominator * wholenum1 + numerator;
			return (numerator + "/" + denominator);
		} else {
			Scanner num = new Scanner(fraction.substring(fraction.indexOf("_") + 1)).useDelimiter("/");
			int numerator = num.nextInt();
			int denominator = num.nextInt();
			numerator = denominator * wholenum1 + numerator;
			return (numerator + "/" + denominator);
			// 34_5/6 + 1_2/3
		}
	}

	public static String add(String fraction1, String fraction2) {
		Scanner num = new Scanner(fraction1).useDelimiter("/");
		Scanner num2 = new Scanner(fraction2).useDelimiter("/");
		int F1numerator = num.nextInt();
		int F2numerator = num2.nextInt();
		int F1denominator = num.nextInt();
		int F2denominator = num2.nextInt();
		String total = AddingFormula(F1numerator, F2numerator, F1denominator, F2denominator);
		return total;
	}

	public static String AddingFormula(int F1numerator, int F2numerator, int F1denominator, int F2denominator) {
		int topline = (F1numerator * F2denominator) + (F1denominator * F2numerator);
		int bottomline = F1denominator * F2denominator;
		return (topline + "/" + bottomline);
	}

	public static String subtract(String fraction1, String fraction2) {
		Scanner num = new Scanner(fraction1).useDelimiter("/");
		Scanner num2 = new Scanner(fraction2).useDelimiter("/");
		int F1numerator = num.nextInt();
		int F2numerator = num2.nextInt();
		int F1denominator = num.nextInt();
		int F2denominator = num2.nextInt();
		String total = SubtractFormula(F1numerator, F2numerator, F1denominator, F2denominator);
		return total;
	}

	public static String SubtractFormula(int F1numerator, int F2numerator, int F1denominator, int F2denominator) {
		int topline = (F1numerator * F2denominator) - (F1denominator * F2numerator);
		int bottomline = F1denominator * F2denominator;
		return (topline + "/" + bottomline);
	}

	public static String multiply(String fraction1, String fraction2) {
		Scanner num = new Scanner(fraction1).useDelimiter("/");
		Scanner num2 = new Scanner(fraction2).useDelimiter("/");
		int F1numerator = num.nextInt();
		int F2numerator = num2.nextInt();
		int F1denominator = num.nextInt();
		int F2denominator = num2.nextInt();
		String product = MultiplyFormula(F1numerator, F2numerator, F1denominator, F2denominator);
		return product;
	}

	public static String MultiplyFormula(int F1numerator, int F2numerator, int F1denominator, int F2denominator) {
		int topline = F1numerator * F2numerator;
		int bottomline = F1denominator * F2denominator;
		return (topline + "/" + bottomline);
	}

	public static String divide(String fraction1, String fraction2) {
		Scanner num = new Scanner(fraction1).useDelimiter("/");
		Scanner num2 = new Scanner(fraction2).useDelimiter("/");
		int F1numerator = num.nextInt();
		int F2numerator = num2.nextInt();
		int F1denominator = num.nextInt();
		int F2denominator = num2.nextInt();
		String quotient = DivideFormula(F1numerator, F2numerator, F1denominator, F2denominator);
		return quotient;
	}

	public static String DivideFormula(int F1numerator, int F2numerator, int F1denominator, int F2denominator) {
		int tempnum = F2numerator; // In order to prevent the denominator from being the numerator and denominator
		F2numerator = F2denominator;
		F2denominator = tempnum;
		int topline = F1numerator * F2numerator;
		int bottomline = F1denominator * F2denominator;
		return (topline + "/" + bottomline);
	}

	public static void simplify(String fraction) {
		Scanner num = new Scanner(fraction).useDelimiter("/");
		int numerator = num.nextInt();
		int denominator = num.nextInt();
		int remainder = 0, wholenum = 0;
		boolean NumeratorAndDenominatorAreSameNum = (denominator == 1 || numerator == denominator);
		boolean improperfraction = numerator > denominator;
		boolean DividesIntoWholeNumber = wholenum > 0 && remainder == 0;
		if (numerator < 0) { // if the number is negative
			numerator *= -1;
			for (int i = denominator; i > 0; i--) {
				if (denominator % i == 0 && numerator % i == 0) { // Simplifies fractions like 64/66 and 32/34 with GCF
					denominator /= i;
					numerator /= i;
				}
			}
			if (NumeratorAndDenominatorAreSameNum) {
				wholenum = numerator;
				if (wholenum < 0) {
					System.out.println("-" + wholenum);
				}
				System.out.println(wholenum);
			} else if (improperfraction) {
				wholenum = numerator / denominator;
				remainder = numerator % denominator;
				if (DividesIntoWholeNumber) {
					System.out.println(wholenum);
				} else { // if (HasAFractionAfterWholeNumber)
					numerator = remainder;
					if (numerator == 0) {
						System.out.println(wholenum);
					} else if (denominator % numerator == 0) {
						denominator /= numerator;
						numerator /= numerator;
						numerator *= -1;
						System.out.println(wholenum + " " + numerator + "/" + denominator);
					} else {
						numerator *= -1;
						System.out.println(wholenum + " " + numerator + "/" + denominator);
					}
				}
			} else { // if (CannotBeSimplified)
				numerator *= -1;
				if (numerator == 0) {
					System.out.println("0");
				} else {
					if (numerator < 0) { // This simplifies the negative division and multiplication of improper
						// negatives
						numerator *= -1;
						wholenum = numerator / denominator;
						remainder = numerator % denominator;
						if (DividesIntoWholeNumber) {
							wholenum *= -1;
							System.out.println(wholenum);
						} else { // if (HasAFractionAfterWholeNumber)
							numerator = remainder;
							if (numerator == 0) {
								wholenum *= -1;
								System.out.println(wholenum);
							} else if (denominator % numerator == 0) {
								denominator /= numerator;
								numerator /= numerator;
								wholenum *= -1;
								System.out.println(wholenum + " " + numerator + "/" + denominator);
							} else {
								wholenum *= -1;
								if (wholenum == 0) {
									numerator *= -1;
									System.out.println(numerator + "/" + denominator);
								} else {
									System.out.println(wholenum + " " + numerator + "/" + denominator);
								}
							}
						}
					} else {
						System.out.println(numerator + "/" + denominator);
					}
				}
			}

		} else { // If the number is positive
			for (int i = denominator; i > 0; i--) {
				if (denominator % i == 0 && numerator % i == 0) { // Simplifies fractions like 64/66 and 32/34 with GCF
					denominator /= i;
					numerator /= i;
				}
			}
			if (NumeratorAndDenominatorAreSameNum) {
				wholenum = numerator;
				System.out.println(wholenum);
			} else if (improperfraction) {
				wholenum = numerator / denominator;
				remainder = numerator % denominator;
				if (DividesIntoWholeNumber) {
					System.out.println(wholenum);
				} else { // if (HasAFractionAfterWholeNumber)
					numerator = remainder;
					if (numerator == 0) {
						System.out.println(wholenum);
					} else if (denominator % numerator == 0) {
						denominator /= numerator;
						numerator /= numerator;
						System.out.println(wholenum + " " + numerator + "/" + denominator);
					} else {
						System.out.println(wholenum + " " + numerator + "/" + denominator);
					}
				}
			} else { // if (CannotBeSimplified)
				if (numerator == 0) {
					System.out.println("0");
				} else {
					System.out.println(numerator + "/" + denominator);
				}
			}
		}
	}
}
