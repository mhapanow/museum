package com.ciessa.museum.tools;

public class Range implements Cloneable {
	private int to;
	private int from;
	private String cursor;

	public Range(int from, int to) {
		super();
		this.to = to;
		this.from = from;
		this.cursor = null;
	}
	
	public Range(int from, int to, String cursor) {
		super();
		this.to = to;
		this.from = from;
		this.cursor = cursor;
	}

	/**
	 * @return the to
	 */
	public int getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(int to) {
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
		if( this.from < 0 ) this.from = 0;
	}


	/**
	 * Checks if the received value is in the Range values.
	 **/
	public boolean isInRange(int value) {

		if((from <= value) && (to >= value))
			return true;

		return false; 
	}

	/**
	 * @return the cursor
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * @param cursor the cursor to set
	 */
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Range [to=" + to + ", from=" + from + ", cursor=" + cursor
				+ "]";
	}

	/**
	 * Clones this instances
	 */
	public Range clone() {
		Range ret = new Range(this.from, this.to, this.cursor);
		return ret;
	}

}
