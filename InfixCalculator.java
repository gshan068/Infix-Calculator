//This class evaluate the RPN expression (postfix).
/* Extra Credit:do not require blanks (i.e. allow users to input "5+7*2" as well as "5 + 7 * 2" ) 5% must
	be documented - it's your responsibility to point it out)
	Extra Credit : support negative and positive numbers - unary +/- (i.e.allow users to input " -5 + -7 *
	+2" ) 5% must be documented - it's your responsibility to point it out)
	Extra Credit : modulo (use "%" operator same association as in JAVA ) 5% must be documented - it's
	your responsibility to point it out
*/

import java.util.Scanner;

public interface InfixCalculator{
	// Below is the user input and the result:
	public static void main(String[] args) {
	        Scanner equation = new Scanner(System.in);
	        System.out.println("Enter the equation: ");
	        String equ = equation.nextLine();
	        
	        if(equ.charAt(0)=='+' || equ.charAt(0)=='-') { // This is the second extra credit that support negative and positive numbers.
	        	System.out.println("The result of this equation is "+ evalExp2(equ));
	        }
	        else { // This is only for positive number.
	        	System.out.println("The result of this equation is "+ evalExp(equ));
	        }
	        equation.close();
	}
	
	/* exp is the expression string input from the keyboard and the
	   Stirng returned is the display of the calculator
	*/
	//I changed this evalExp method into static method.
	public static String evalExp(String exp) {
		Queue<Object> postfix= InfixtoPostfix.InfixToPostfix(exp);
		Stack<Double> stack = new Stack<>();
		
		while(!postfix.isEmpty()){
			Object token = postfix.dequeue();
			if(token.equals('+')) {
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2+num1);
			}
			else if(token.equals('-')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2-num1);
			}
			else if(token.equals('*')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2*num1);
			}
			else if(token.equals('/')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2/num1);
			}
			else if(token.equals('^')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(Math.pow(num2, num1));
			}
			else if(token.equals('%')) { // This is the third extra credit: modulo (%).
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2%num1);
			}
			else {
				stack.push(Double.valueOf((String) token));
			}
		}
	
		return String.valueOf(stack.peek());
	}
	
	/****************************************************************************************************************/
	//This is for the second extra credit that support negative and positive numbers.
	public static String evalExp2(String exp) {
		Queue<Object> postfix= InfixtoPostfix.IntoPost2(exp);
		Stack<Double> stack = new Stack<>();
		
		while(!postfix.isEmpty()){
			Object token = postfix.dequeue();
			if(token.equals('+')) {
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2+num1);
			}
			else if(token.equals('-')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2-num1);
			}
			else if(token.equals('*')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2*num1);
			}
			else if(token.equals('/')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2/num1);
			}
			else if(token.equals('^')){
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(Math.pow(num2, num1));
			}
			else if(token.equals('%')) { // This is the third extra credit: modulo (%).
				double num1 = stack.pop();
				double num2 = stack.pop();
				stack.push(num2%num1);
			}
			else {
				stack.push(Double.valueOf((String) token));
			}
		}
	
		return String.valueOf(stack.peek());
	}
	
}



