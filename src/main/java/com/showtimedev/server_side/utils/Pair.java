package com.showtimedev.server_side.utils;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Pair<A, B>{
	
	public Pair(A a, B b){
		this.a = a;
		this.b = b;
	}
	
	public final A a;
	
	public final B b;
}
