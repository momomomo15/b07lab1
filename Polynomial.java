public class Polynomial {
    private double[] terms;

    public Polynomial() {
        terms = new double[]{0};
    }

    public Polynomial(double[] terms) {
        this.terms = terms.clone();
    }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.terms.length, other.terms.length);
        double[] sum = new double[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            double thisTerm = (i < this.terms.length) ? this.terms[i] : 0;
            double otherTerm = (i < other.terms.length) ? other.terms[i] : 0;
            sum[i] = thisTerm + otherTerm;
        }

        return new Polynomial(sum);
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
