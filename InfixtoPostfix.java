//This class converts infix into postfix.
/* Extra Credit:do not require blanks (i.e. allow users to input "5+7*2" as well as "5 + 7 * 2" ) 5% must
	be documented - it's your responsibility to point it out)
	Extra Credit : support negative and positive numbers - unary +/- (i.e.allow users to input " -5 + -7 *
	+2" ) 5% must be documented - it's your responsibility to point it out)
	Extra Credit : modulo (use "%" operator same association as in JAVA ) 5% must be documented - it's
	your responsibility to point it out
*/

public class InfixtoPostfix {
	
	public static int Precedence(char symbol)
    {
		//Rank the operators: 
		// + and - is higher than %. 
		// % is higher than * and /
		// ^ is higher than all other operators.
        if (symbol == '+' || symbol == '-')
            return 1;
        else if (symbol == '*' || symbol == '/')
            return 3;
        else if(symbol == '%') { // This is the third extra credit: modulo.
        	return 2;
        }
        else if (symbol == '^')
            return 4;
        else
            return -1;
    }
	
	public static Queue<Object> InfixToPostfix(String exp) {
        Queue<Object> q = new Queue<Object>();
        for(int i = 0;i<exp.length();i++) {
        	if(exp.charAt(i) == ' ') { // This is the first extra credit: do not require blanks.
        		continue;
        	}
        	//Check if the character is digit, if yes, put all number characters (double or int)
        	// into the string d and enqueue into the q.
        	else if(Character.isDigit(exp.charAt(i))){
        		int count = 1;
        		int j = i;
        		String d ="";
        		d+=exp.charAt(i);
        		if(i != exp.length()-1) {
	        		while(j+count<exp.length() && (Character.isDigit(exp.charAt(j+count))|| exp.charAt(j+count)=='.') ){
        					d+= exp.charAt(j+count);
        					count++;
        					i++;
	        		}
        		}
        		q.enqueue((String) d);
        		
        	}
        	//If the character is equal to one of the operators, just enqueue into q directly.
        	else if(exp.charAt(i)=='+'||exp.charAt(i)=='-'||exp.charAt(i)=='*'||exp.charAt(i)=='/'||exp.charAt(i)=='^'
        			||exp.charAt(i)=='%'){ // I included the % here (the third extra credit)
        		q.enqueue((char)exp.charAt(i));
        	}
        	//if the character is equal to open or close parenthesis, enqueue it into q.
        	else if(exp.charAt(i) == '(' || exp.charAt(i) == ')'){
        		q.enqueue((char) exp.charAt(i));
        	
        	}
        }
        
        //Check token:
        // Initialize the Stack for storing operator and newQ for storing postfix.
    	Stack<Character> operator = new Stack<Character>();
    	Queue<Object> newQ = new Queue<Object>();
    	
    	while(!q.isEmpty()) {
             // Assess token in the q queue.
            Object token = q.dequeue();
            if (token.equals('(')) {
                 operator.push((char) token);
             }
    		 
             // If the scanned Token is an ')' pop and append
             //  it to output from the stack until an '(' is encountered.
             else if (token.equals(')')) {
                 while (!operator.isEmpty()
                        && operator.peek() != '(') {
                     newQ.enqueue(operator.pop());
                 }
                 operator.pop();
             }
            //if the token is equal to numbers, put it into the newQ.
            else if(!token.equals('+')&&!token.equals('-')&&!token.equals('*')&&!token.equals('/')&&!token.equals('^')&&!token.equals('%')) {
            	 newQ.enqueue(token);
             }
             
             // If an operator is encountered then taken the
             // further action based on the precedence of the operator.
            else {
                 while (!operator.isEmpty()
                     && Precedence((char) token) <= Precedence(operator.peek())) {
                     newQ.enqueue(operator.pop());
                 }
                 operator.push((Character) token);
                 
             }
         }
  
         // pop all the remaining operators from the stack and append them to output
         while (!operator.isEmpty()) {
             assert operator.peek() != '(';
             newQ.enqueue(operator.pop());
         }
         
    	/*
         //test code: 
         URLinkedList<Object> l = new URLinkedList<Object>();
         l = newQ;
         for(int p = 0;p<l.size();p++) {
         	System.out.println(l.get(p));
         }
         */
         
    	return newQ;
   	}
 
	
	/**************************************************************************************************/
	//Extra credit two: this method supports negative and positive numbers - unary +/-
	public static Queue<Object> IntoPost2(String exp) {
        Queue<Object> q = new Queue<Object>();
        //Delete any space in the exp string:
        String newExp = "";
        for(int i = 0; i< exp.length(); i++) {
        	if(exp.charAt(i) != ' ') { 
        		newExp += exp.charAt(i);
        	}
        }
        exp = newExp;
        
        
        for(int i = 0;i<exp.length();i++) {
        	//If the character is equal to one of the operators, go into this if statement:
        	if(exp.charAt(i)=='+'||exp.charAt(i)=='-'||exp.charAt(i)=='*'||exp.charAt(i)=='/'||exp.charAt(i)=='^') {
        		//if the operator is located in front of the number, this operator belongs to the string of the number.
        		if(i+1<exp.length() && Character.isDigit(exp.charAt(i+1))){
            		int count = 1;
            		int j = i;
            		String d = "";
            		d += exp.charAt(i);
         
            		if(i != exp.length()-1) {
    	        		while(j+count<exp.length() && (Character.isDigit(exp.charAt(j+count))|| exp.charAt(j+count)=='.') ){
            					d+= exp.charAt(j+count);
            					count++;
            					i++;
    	        		}
            		}
            		
            		q.enqueue((String) d);
            	}
        		//If the operator is not located in front of the number, which means it is used for actual operation, enqueue it 
        		//  into the q directly.
        		else {
        			q.enqueue(exp.charAt(i));
        		}
        	}
        	//if the character is equal to open or close parenthesis, enqueue it into q.
        	else if(exp.charAt(i) == '(' || exp.charAt(i) == ')'){
        		q.enqueue((char) exp.charAt(i));
        	
        	}
        }
        
        
        //Check token:
        // Initialize the Stack for storing operator and newQ for storing postfix.
    	Stack<Character> operator = new Stack<Character>();
    	Queue<Object> newQ = new Queue<Object>();
    	
    	while(!q.isEmpty()) {
             // Assess token in the q queue.
            Object token = q.dequeue();
            if (token.equals('(')) {
                 operator.push((char) token);
             }
    		 
             // If the scanned Token is an ')' pop and append
             //  it to output from the stack until an '(' is encountered.
             else if (token.equals(')')) {
                 while (!operator.isEmpty()
                        && operator.peek() != '(') {
                     newQ.enqueue(operator.pop());
                 }
                 operator.pop();
             }
            //if the token is equal to numbers, put it into the newQ.
            else if(!token.equals('+')&&!token.equals('-')&&!token.equals('*')&&!token.equals('/')&&!token.equals('^')&&!token.equals('%')) {
            	 newQ.enqueue(token);
             }
             
             // If an operator is encountered then taken the
             // further action based on the precedence of the operator.
            else {
                 while (!operator.isEmpty()
                     && Precedence((char) token) <= Precedence(operator.peek())) {
                     newQ.enqueue(operator.pop());
                 }
                 operator.push((Character) token);
                 
             }
         }
  
         // pop all the remaining operators from the stack and append them to output
         while (!operator.isEmpty()) {
             assert operator.peek() != '(';
             newQ.enqueue(operator.pop());
         }
         
    	/*
         //test code: 
         URLinkedList<Object> l2 = new URLinkedList<Object>();
         l2 = newQ;
         for(int p = 0;p<l2.size();p++) {
         	System.out.print(l2.get(p));
         }
         */
    	return newQ;
   	}
 
	/*
	public static void main(String[] args) {
        String exp = "11-2^3^3-(4+5*6)*7";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + InfixToPostfix(exp));
        
        String exp2 = "+ 11 -- 2";
        System.out.println("Infix Expression: " + exp2);
        System.out.println("Postfix Expression: " + IntoPost2(exp2));
    }
    */
    
}

