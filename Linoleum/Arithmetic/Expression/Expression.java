package Arithmetic.Expression;


import DataStructures.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import java.util.regex.Pattern;


public class Expression {


	public Expression(String expression) {

		this.template = expression;
		int n = this.template.length();

		this.constants = new ArrayList<Double>();

		Stack<Character> par = new Stack<Character>();
		
		for (int i = 0; i < n; i++) {

			char char_i = this.template.charAt(i);

			if (!LANGUAGE_CHARS.contains(char_i)) {

				throw new InvalidSymbolsException();

			} else if (char_i == LP) {

				par.push(char_i);

			} else if (char_i == RP) {

			 	if (par.isEmpty())
					throw new UnbalancedParenthesisException();
				else
					par.pop();
			
			} else if (NUMERIC_CHARS.contains(char_i)) {

				int j = i + 1;
				while (j < n && NUMERIC_CHARS.contains(this.template.charAt(j)))
					j += 1;

				String num = this.template.substring(i, j);
				if (Pattern.matches(REAL_NUMBER_REGEX, num)) {

					if (num.charAt(0) == MINUS)
						num = num.replace(MINUS, SUST);

					Double r = Double.parseDouble(num);
					constants.add(r);

					String d = this.template.substring(j);
					this.template = this.template.substring(0, i);
					this.template += u;
					this.template += d;

					n = this.template.length();
					
				} else {

					throw new InvalidNumberException();

				}

			}

		}

		this.toPostfix();

	}

			
	private int getHierarchy(char op) {

		if (HIERARCHY_1.contains(op))
			return 1;

		if (HIERARCHY_2.contains(op))
			return 2;

		if (HIERARCHY_3.contains(op))
			return 3;

		if (HIERARCHY_4.contains(op))
			return 4;

		if (HIERARCHY_5.contains(op))
			return 5;

		return 0;

	}
	
	
    private int compareHierarchy(char op1, char op2) {
		
		return getHierarchy(op1) - getHierarchy(op2);
		
    }
	

    private String toPostfix() {

		Stack<String> operands = new Stack<String>();
		Stack<Character> operations = new Stack<Character>();
		int n = this.template.length();

		for (int i = 0; i < n; i++) {

			Character op = template.charAt(i);

			if (op == LP) {

				int count_par = 1;
				int j = i;

				while(count_par != 0) {

					j += 1;
					char char_j = template.charAt(j);

					if (char_j == LP)
						count_par += 1;
					else if (char_j == RP)
						count_par -= 1;

				}

				Expression subExpr = new Expression(template.substring(i + 1, j));
				String subExprPostfix = subExpr.toPostfix();

				operands.push(subExprPostfix);

				i = j;

			} else if (LITERALS.contains(op) || VARIABLES.contains(op)) {
				
				operands.push(op + "");

			} else if (UNARY_OPERATIONS.contains(op) || BINARY_OPERATIONS.contains(op)) {

				Character aux = operations.isEmpty() ? 0 : operations.peek();

				while (compareHierarchy(aux, op) >= 0) {

					if (BINARY_OPERATIONS.contains(aux)) {

						if (operands.isEmpty())
							throw new TooFewOperandsException();
						
						String b = operands.pop();

						if (operands.isEmpty())
							throw new TooFewOperandsException();
							
						String a = operands.pop();
						
						operands.push(a.concat(b).concat(aux + ""));

					} else if (UNARY_OPERATIONS.contains(aux)) {

						if (operands.isEmpty())
							throw new TooFewOperandsException();
							
						String a = operands.pop();

						operands.push(a.concat(aux + ""));

					}

					operations.pop(); //aux
					aux = operations.isEmpty() ? 0 : operations.peek();

				}

				operations.push(op);

			}

		}
		
		while (!operations.isEmpty()) {

			Character aux = operations.pop();

			if (BINARY_OPERATIONS.contains(aux)) {

				if (operands.isEmpty())
					throw new TooFewOperandsException();
				
				String b = operands.pop();

				if (operands.isEmpty())
					throw new TooFewOperandsException();
					
				String a = operands.pop();
				
				operands.push(a.concat(b).concat(aux + ""));

			} else if (UNARY_OPERATIONS.contains(aux)) {

				if (operands.isEmpty())
					throw new TooFewOperandsException();
					
				String a = operands.pop();

				operands.push(a.concat(aux + ""));

			}

		}

		if (operands.size() == 1)
			return operands.pop();
		else
			throw new TooManyOperandsException();

    }
	

    public BinaryTree<Character> toExpressionTree() {

		String postfix = this.toPostfix();
		int n = postfix.length();
		Stack<BinaryTree<Character>> operands = new Stack<BinaryTree<Character>>();

		for (int i = 0; i < n; i++) {

			Character op = postfix.charAt(i);

			if (LITERALS.contains(op) || VARIABLES.contains(op)) {

				operands.push(new BinaryTree<Character>(op));

			} else if (UNARY_OPERATIONS.contains(op)) {
				
				BinaryTree<Character> newOp;
				newOp = new BinaryTree<Character>(op);
				newOp.setL(operands.pop());
				operands.push(newOp);

			} else if (BINARY_OPERATIONS.contains(op)) {

				BinaryTree<Character> newOp;
				BinaryTree<Character> aux = operands.pop();
				newOp = new BinaryTree<Character>(op, operands.pop(), aux);
				operands.push(newOp);

			}

		}

		return operands.pop();

    }


	public static Expression toExpression(BinaryTree<Character> leaf) {
		String expL = "", expR = "";
		if (leaf.hasL()) expL = toExpression(leaf.getL()).getTemplate();
		if (leaf.hasR()) expR = toExpression(leaf.getR()).getTemplate();
		Character info = leaf.getData();
		if (info == 'L' || info == 'S' || info == 'C' || info == 'T')
			return new Expression(info + expL);
		//return expL + leaf.getInfo() + expR; 
		return new Expression((expL.length() > 1 ? "(" + expL + ")" : expL).concat((leaf.getData() + "").concat(expR.length() > 1 ? "(" + expR + ")" : expR)));
	}


	public String getTemplate() {

		return this.template;

	}


	public ArrayList<Double> getConstants() {

		return this.constants;

	}
	


	//NUMBERS
	final private char ZERO  = '0';
	final private char ONE 	 = '1';
	final private char TWO 	 = '2';
	final private char THREE = '3';
	final private char FOUR  = '4';
	final private char FIVE  = '5';
	final private char SIX 	 = '6';
	final private char SEVEN = '7';
	final private char EIGHT = '8';
	final private char NINE  = '9';

	//BINARY OPERATIONS
	final private char SUM 	= '+';
	final private char SUST = '-';
	final private char MULT = '*';
	final private char DIV  = '/';
	final private char EXP 	= '^';

	//UNARY OPERATIONS
	final private char LOG 	  = 'L';
	final private char SIN 	  = 'S';
	final private char ASIN   = 's';
	final private char COS    = 'C';
	final private char ACOS   = 'c';
	final private char TAN 	  = 'T';
	final private char ATAN   = 't';
	final private char COT 	  = 'N';
	final private char ACOT   = 'n';
	final private char SEC 	  = 'A';
	final private char ASEC   = 'a';
	final private char CSC    = 'U';
	final private char ACSC   = 'u';
	
	//VARIABLES
	final private char X = 'X';
	final private char Y = 'Y';
	final private char Z = 'Z';
	final private char W = 'W';
	
	//LITERALS
	final private char u  = 'u';
	final private char E  = 'E';
	final private char PI = 'P';

	//SYMBOLS
	final private char MINUS  = '_';
	final private char PERIOD = '.';
	final private char LP     = '(';
	final private char RP     = ')';

	//SYMBOL LISTS
	final private Character[] lang_chars = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, SUM, SUST, MULT, DIV, EXP, MINUS, LOG, SIN, ASIN, COS, ACOS,
		TAN, ATAN, COT, ACOT, SEC, ASEC, CSC, ACSC, X, Y, Z, W, u, E, PI, PERIOD, LP, RP};

	final private Character[] lang_chars_no_vars = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, SUM, SUST, MULT, DIV, EXP, MINUS, LOG, SIN, ASIN, COS, ACOS,
		TAN, ATAN, COT, ACOT, SEC, ASEC, CSC, ACSC, u, E, PI, PERIOD, LP, RP};

	final private Character[] num_chars = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, MINUS, PERIOD};

	final private Character[] literals = {u, E, PI};

	final private Character[] variables = {X, Y, Z, W};

	final private Character[] unary_ops = {SIN, ASIN, COS, ACOS, TAN, ATAN,
		COT, ACOT, SEC, ASEC, CSC, ACSC};

	final private Character[] binary_ops = {SUM, SUST, MULT, DIV, EXP, LOG};

	final private Character[] hier_1 = {SUM, SUST};

	final private Character[] hier_2 = {MULT, DIV};

	final private Character[] hier_3 = {LOG};

	final private Character[] hier_4 = {EXP};

	final private Character[] hier_5 = {LOG, SIN, ASIN, COS, ACOS, TAN, ATAN,
		COT, ACOT, SEC, ASEC, CSC, ACSC};

	final private ArrayList<Character> LANGUAGE_CHARS 		  = new ArrayList<Character>(Arrays.asList(lang_chars));
	final private ArrayList<Character> LANGUAGE_CHARS_NO_VARS = new ArrayList<Character>(Arrays.asList(lang_chars_no_vars));
	final private ArrayList<Character> NUMERIC_CHARS 		  = new ArrayList<Character>(Arrays.asList(num_chars));
	final private ArrayList<Character> LITERALS 			  = new ArrayList<Character>(Arrays.asList(literals));
	final private ArrayList<Character> VARIABLES 			  = new ArrayList<Character>(Arrays.asList(variables));
	final private ArrayList<Character> UNARY_OPERATIONS 	  = new ArrayList<Character>(Arrays.asList(unary_ops));
	final private ArrayList<Character> BINARY_OPERATIONS 	  = new ArrayList<Character>(Arrays.asList(binary_ops));
	final private ArrayList<Character> HIERARCHY_1 			  = new ArrayList<Character>(Arrays.asList(hier_1));
	final private ArrayList<Character> HIERARCHY_2 			  = new ArrayList<Character>(Arrays.asList(hier_2));
	final private ArrayList<Character> HIERARCHY_3 			  = new ArrayList<Character>(Arrays.asList(hier_3));
	final private ArrayList<Character> HIERARCHY_4 			  = new ArrayList<Character>(Arrays.asList(hier_4));
	final private ArrayList<Character> HIERARCHY_5 			  = new ArrayList<Character>(Arrays.asList(hier_5));

	//REGEX
	final private String REAL_NUMBER_REGEX = "^[_]?(\\d*\\.\\d+|\\d+\\.\\d+|\\d+|\\.\\d+)$";
	
	//FIELDS
	String template;
	ArrayList<Double> constants;


}