public class Polynomial {
    private double[] terms; //coefficients of the polynomial
    private int[] exp; //exponents of the polynomial

    public Polynomial() {
        terms = new double[]{0};
        exp = new int[]{0};
    }

    public Polynomial(double[] terms, int[] exp) {
        this.terms = terms.clone();
        this.exp = exp.clone();
    }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.terms.length, other.terms.length);
        double[] sum = new double[maxDegree];
        int[] exp = new int[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            double thisTerm = (i < this.terms.length) ? this.terms[i] : 0;
            double otherTerm = (i < other.terms.length) ? other.terms[i] : 0;
            sum[i] = thisTerm + otherTerm;
            exp[i] = i;
        }

        return new Polynomial(sum, exp);
    }

    public Polynomial multiply(Polynomial other) {

    int maxDegree = this.exp[this.exp.length - 1] + other.exp[other.exp.length - 1]; //max degree of final poly
    
    double[] prod = new double[maxDegree + 1]; //for final coefficients
    int[] exp = new int[maxDegree + 1]; //for final exponents

    for (int i = 0; i <= maxDegree; i++) {
        exp[i] = i;
    }

    for (int i = 0; i < this.terms.length; i++) {
        for (int j = 0; j < other.terms.length; j++) {
            int newExp = this.exp[i] + other.exp[j]; 
            double newTerm = this.terms[i] * other.terms[j];

            prod[newExp] += newTerm; //combine terms with same exponents
        }
    }

    return new Polynomial(prod, exp);
}

    public double evaluate(double value) {
        double result = 0;
        for (int i = 0; i < terms.length; i++) {
            result += terms[i] * Math.pow(value, i);
        }
        return result;
    }

    public boolean hasRoot(double value) {
        return evaluate(value) == 0;
    }
}
