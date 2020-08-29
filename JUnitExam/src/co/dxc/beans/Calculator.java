package co.dxc.beans;

public class Calculator {
	
	public int add(int n1, int n2) {
		int res;
		res = n1 + n2;
		return res;
	}
	
	public int substract(int n1, int n2) {
		int res;
		res = n1 - n2;
		return res;
	}
	
	public int multiply(int n1, int n2) {
		int res;
		res = n1 * n2;
		return res;
	}
	
	public double divide(int n1, int n2) {
		double res;
		res = (double) n1/n2;
		return res;
	
    }
}
