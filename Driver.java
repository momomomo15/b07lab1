import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Driver {
    public static void main(String[] args) {
        
        // Create an instance of Polynomial using the no-argument constructor
        Polynomial p = new Polynomial();
        System.out.println("Evaluating polynomial p at x = 3: " + p.evaluate(3));

        // Define coefficients and exponents for the first polynomial
        double[] c1 = {6, 0, 0, 5}; // Represents 6 + 0x + 0x^2 + 5x^3
        int[] c2 = {0, 1, 2, 3};    // Corresponding exponents
        Polynomial p1 = new Polynomial(c1, c2);

        // Define coefficients and exponents for the second polynomial
        double[] c3 = {0, -2, 0, 0, -9}; // Represents -2x + 0x^2 + 0x^3 - 9x^4
        int[] c4 = {0, 1, 2, 3, 4};       // Corresponding exponents
        Polynomial p2 = new Polynomial(c3, c4);

        // Add the two polynomials
        Polynomial s = p1.add(p2);
        // Multiply the two polynomials
        Polynomial m = p1.multiply(p2);

        // Evaluate and display results
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s");
        }

        // Display the product of p1 and p2
        System.out.println("Product of p1 & p2: ");
        System.out.println(m); // Using the toString method

        // Test the file constructor
        try {
            // Create a test file with a polynomial
            String filename = "polynomial.txt";
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.println("5-3x2+7x4"); // Writing a polynomial to the file
            writer.close();

            // Initialize a polynomial from the file
            Polynomial polyfromfile = new Polynomial(new File(filename));
            System.out.println("Polynomial created from file: ");
            System.out.println(polyfromfile); // Using the toString method

            // Save the polynomial back to a new file
            polyfromfile.saveToFile("saved_polynomial.txt");
            System.out.println("Polynomial saved to 'saved_polynomial.txt'");

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}