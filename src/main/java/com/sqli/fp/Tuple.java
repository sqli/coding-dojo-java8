package com.sqli.fp;

public class Tuple<S1, S2> {

	private S1 _1;
	private S2 _2;

	public Tuple(S1 _1, S2 _2) {
		super();
		this._1 = _1;
		this._2 = _2;
	}

	public S1 _1() {
		return _1;
	}

	public S2 _2() {
		return _2;
	}

}
