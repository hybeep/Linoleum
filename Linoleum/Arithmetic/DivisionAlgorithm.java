package Arithmetic;


import java.util.Stack;


public class DivisionAlgorithm {

  	private Long sgn_a, sgn_b;
	private Long A, B;
	private Long C, Q, R;
	private final Long ZERO = 0L;
	private final Long ONE = 1L;
	// A * C = B * Q + R , initially C = 1


	private DivisionAlgorithm(Long A, Long B) {
	
		this.sgn_a = (A >= ZERO) ? ONE : -ONE;
		this.A = (A >= ZERO) ? A : -A;
	    
    	this.sgn_b = (B >= ZERO) ? ONE : -ONE;
		this.B = (B >= ZERO) ? B : -B;

		if (Long.compare(this.A, ZERO) != 0 && Long.compare(this.B, ZERO) != 0) {
		
			this.C = 1L;

			Long q = 0L;
			Long H = this.B;
			while (H <= this.A) {

				H += this.B;
				q += 1L;

			}

			this.Q = q;
			this.R = this.A - this.B * this.Q;
			
		}

	}
	

	private Long[] GCD() { //returns an int[3] array {c,q,r} where ca + qb = r = gcd(a,b)
	
    	if (Long.compare(this.A, ZERO) == 0 && Long.compare(this.B, ZERO) == 0) {
    
	    	return new Long[]{ZERO, ZERO, ZERO};
	  
	  	} else if (Long.compare(this.A, ZERO) == 0) {
	  
	    	return new Long[]{ZERO, this.sgn_b, this.B};
	  
	  	} else if (Long.compare(this.B, ZERO) == 0) {
	  
	    	return new Long[]{this.sgn_a, ZERO, this.A};
	  
	  	} else if (Long.compare(this.R, ZERO) == 0) {

			return new Long[]{ZERO , this.sgn_b, this.B};
		
		} else {
			
			Stack<DivisionAlgorithm> EqSyst = new Stack<DivisionAlgorithm>();
			DivisionAlgorithm Eqt = this;
			while (Long.compare(Eqt.R, ZERO) != 0) {

				EqSyst.push(Eqt);
				Eqt = new DivisionAlgorithm(Eqt.B, Eqt.R);

			}

			//EqSyst is not empty
			Long Ce, Qe, Re;
			while (true) {

				Eqt = EqSyst.pop();
				Ce = Eqt.C;
				Qe = Eqt.Q;
				Re = Eqt.R;

				if (!EqSyst.empty()) {

					DivisionAlgorithm lEq = EqSyst.peek();
					lEq.C = lEq.C * Qe;
					lEq.Q = lEq.Q * Qe + Ce;
					lEq.R = -Re;

				} else {

					break;

				}
				
			}
			
			if (Long.compare(Re, ZERO) >= 0)
				return new Long[]{this.sgn_a * Ce, - this.sgn_b * Qe, Re};
			else 
				return new Long[]{- this.sgn_a * Ce, this.sgn_b * Qe, -Re};
				
		}

	}


	public static Long[] gcd(Long A, Long B) {

		DivisionAlgorithm da = new DivisionAlgorithm(A, B);

		return da.GCD();

	}


}