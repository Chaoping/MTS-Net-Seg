package ubco.cosc520.dynamicprogramming2;

public class Interval {
	public int start;
	public int end;
	public void set(int start, int end) {
		this.start = start;
		this.end = end;
	}
	Interval(int start, int end){
		this.start = start;
		this.end = end;
	}
}
