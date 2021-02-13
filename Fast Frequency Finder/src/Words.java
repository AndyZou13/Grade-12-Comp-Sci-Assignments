public class Words implements Comparable <Words>{
	private String word;
	private int freq = 0;
	public Words (String w, int f) {
		word = w;
		freq = f;
	}
	public String toString () { //for display
		return word;
	}
	public int getFreq () { //getter method
		return freq;
	}
	public String getWord() { //getter method
		return word;
	}
	public int compareTo (Words w1) { //to organize the TreeSet
		return w1.getFreq() - this.getFreq();
	}
}
