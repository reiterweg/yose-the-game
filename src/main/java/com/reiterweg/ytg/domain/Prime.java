package com.reiterweg.ytg.domain;

public class Prime<T> {

	private T number;
	private Integer[] decomposition;
	private String error;

	public T getNumber() {
		return number;
	}

	public void setNumber(T number) {
		this.number = number;
	}

	public Integer[] getDecomposition() {
		return decomposition;
	}

	public void setDecomposition(Integer[] decomposition) {
		this.decomposition = decomposition;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
