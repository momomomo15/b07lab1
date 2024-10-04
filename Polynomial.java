import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Polynomial {
    private double[] terms; // coefficients of the polynomial
    private int[] exp; // exponents of the polynomial

    public Polynomial() { // no-argument constructor
        terms = new double[]{0};
        exp = new int[]{0};
    }

    public Polynomial(double[] terms, int[] exp) {
        this.terms = terms.clone();
        this.exp = exp.clone();
    }

    // Constructor that initializes the polynomial from a file
    public Polynomial(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();

        // Split the polynomial string into terms, retaining signs
        String[] splitTerms = line.split("(?=[+-])");

        // Initialize the terms array and exponents array based on the highest exponent
        int maxDegree = 0;

        for (String term : splitTerms) { //
            int exponent = 0;

            // Determine the exponent from the term
            if (term.contains("x")) {
                String[] parts = term.split("x");
                //exponent = (parts.length > 1) ? Integer.parseInt(parts[1]) : 1; // Default exponent is 1
                if (parts.length > 1) {
                exponent = Integer.parseInt(parts[1]);
                } else {
                exponent = 1; // Default exponent is 1
                }
            }

            maxDegree = Math.max(maxDegree, exponent); // Update the maximum degree found
        }

        // Initialize the arrays based on the maximum degree found
        terms = new double[maxDegree + 1];
        exp = new int[maxDegree + 1];

        // Populate the terms and exp arrays
        for (String term : splitTerms) {
            double coefficient = 1.0;
            int exponent = 0;

            // Identify coefficient and exponent
            if (term.contains("x")) {
                String[] parts = term.split("x");

                // Handle coefficient
                if (term.startsWith("-")) {
                    coefficient = 1.0 * Double.parseDouble(parts[0]);
                } else if (term.startsWith("+") || term.startsWith("x")) {
                    coefficient = 1.0 * Double.parseDouble(parts[0]);
                } else {
                    coefficient = Double.parseDouble(parts[0]);
                }

                // Handle exponent
                exponent = (parts.length > 1) ? Integer.parseInt(parts[1]) : 1; // Default exponent is 1

            } else {
                // Constant term
                coefficient = Double.parseDouble(term);
                exponent = 0; // Constant term corresponds to exponent 0
            }

            // Set the coefficient in the terms array and the exponent in the exp array
            terms[exponent] = coefficient; // Directly assign the coefficient
            exp[exponent] = exponent;       // Assign the exponent to the exp array
        }
    }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.terms.length, other.terms.length);
        double[] sum = new double[maxDegree];
        int[] exp = new int[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            double thisTerm = (i < this.terms.length) ? this.terms[i] : 0;
            double otherTerm = (i < other.terms.length) ? other.terms[i] : 0;
            sum[i] = thisTerm + otherTerm;
            exp[i] = i; // Setting the exponent corresponding to index
        }

        return new Polynomial(sum, exp);
    }

    public Polynomial multiply(Polynomial other) {
        int maxDegree = this.terms.length + other.terms.length - 2; // max degree of final poly
        double[] prod = new double[maxDegree + 1]; // for final coefficients
        int[] exp = new int[maxDegree + 1]; // for final exponents

        for (int i = 0; i < this.terms.length; i++) {
            for (int j = 0; j < other.terms.length; j++) {
                prod[i + j] += this.terms[i] * other.terms[j]; // combine terms with same exponents
            }
        }

        // Assigning corresponding exponents
        for (int i = 0; i < exp.length; i++) {
            exp[i] = i; // Assign the exponent corresponding to the index
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

    public void saveToFile(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));

        writer.print(this.toString()); 
        
        writer.close();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] != 0) {
                if (sb.length() > 0) {
                    sb.append(terms[i] > 0 ? "+" : "").append(terms[i]);
                } else {
                    sb.append(terms[i]);
                }
                if (i > 0) {
                    sb.append("x").append(i);
                }
            }
        }
        return sb.toString();
    }
}
