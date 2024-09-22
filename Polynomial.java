public class Polynomial {
    private double[] coefficients;

    // No-argument constructor
    public Polynomial() {
        coefficients = new double[]{0}; // Polynomial is 0
    }

    // Constructor that takes an array of coefficients
    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients.clone(); // Store a copy of the array
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double thisCoeff = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double otherCoeff = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            result[i] = thisCoeff + otherCoeff;
        }

        return new Polynomial(result);
    }

    // Method to evaluate the polynomial at a given x value
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // Method to check if a value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}
