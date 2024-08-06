package org.example;

class A {
    private int m;
    private int n;
    public A(int mIn, int nIn) {
        m = mIn;
        n = nIn;
    }
    public void m1() {
        m = m + n;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void up_m(int res){
        m = res;
    }

    @Override
    public String toString() {
        return ("A =(" + m + "," + n + ")");
    }
}

public class B extends A {
    public B(int m, int n){
        super(m,n);
    }

    @Override
    public void m1() {
        up_m(getM()-getN());
    }

    @Override
    public String toString() {
       return ("B =(" + getM() + "," + getN() + ")");
    }

    public static void main(String[] args) {
        A a = new A(1, 2);
        A b = new B(1, 2);
        System.out.println(a + " " + b);
        a.m1();
        b.m1();
        System.out.println(a + " " + b);
    }
}   