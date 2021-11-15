package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ObjectStack;

/**Class to demonstrate use of Stack like collection.
 * @author Leo Goršić
 *
 */
public class StackDemo {

	private static final String message = "Expression evaluates to ";

	/**
	 * Fucntion used to see if String is Integer.
	 * 
	 * @param expression
	 * @return true if String can be parsed to Integer, false otherwise
	 */
	private static boolean isStringNumber(String expression) {
		try {
			Integer.parseInt(expression);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Method used to compute expressions in postfix notation. Operators + - / % are
	 * supported!
	 * 
	 * @param args must be single String argument!
	 */
	public static void main(String[] args) {
		String[] expression = args[0].split(" ");
		ObjectStack stack = new ObjectStack();
		for (int i = 0; i < expression.length; i++) {
			if (isStringNumber(expression[i])) {
				stack.push(Integer.parseInt(expression[i]));
			} else {
				Integer operand2 = (Integer) stack.pop();
				Integer operand1 = (Integer) stack.pop();

				switch (expression[i]) {

				case "+":
					stack.push(Integer.sum(operand1, operand2));
					break;

				case "-":
					stack.push(operand1.intValue() - operand2.intValue());
					break;

				case "/":
					if (operand2 == 0) {
						System.out.println("Sorry, but division by zero is not allowed!");
						break;
					} else {
						stack.push(operand1.intValue() / operand2.intValue());
						break;
					}

				case "*":
					stack.push(operand1.intValue() * operand2.intValue());
					break;

				case "%":
					stack.push(operand1.intValue() % operand2.intValue());
					break;
				}

			}
		}

		if (stack.size() != 1) {
			System.out.println("Error: Stack contains more than one element");
		} else {
			System.out.println(message + stack.pop().toString());
		}

	}

}
