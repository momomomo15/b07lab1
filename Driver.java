public class Driver {
public static void main(String [] args) {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));

double [] c1 = {6,0,0,5};
int [] c2 = {0,1,2,3};
Polynomial p1 = new Polynomial(c1,c2);

double [] c3 = {0,-2,0,0,-9};
int [] c4 = {0,1,2,3,4};
Polynomial p2 = new Polynomial(c3,c4);

Polynomial s = p1.add(p2);

System.out.println("s(0.1) = " + s.evaluate(0.1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
}
}