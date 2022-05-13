package it.polito.tdp.borders.model;

import java.util.HashSet;
import java.util.Set;

public class Border {
	private int paese1;
	private int paese2;
	private int anno;
	private Set<Integer> paesi;
	
	public Border(int paese1, int paese2, int anno) {
		super();
		this.paese1 = paese1;
		this.paese2 = paese2;
		this.anno = anno;
		paesi = new HashSet<Integer>();
		paesi.add(paese2);
		paesi.add(paese1);
	}
	
	
	
	public int getPaese1() {
		return paese1;
	}



	public void setPaese1(int paese1) {
		this.paese1 = paese1;
	}



	public int getPaese2() {
		return paese2;
	}



	public void setPaese2(int paese2) {
		this.paese2 = paese2;
	}



	public int getAnno() {
		return anno;
	}



	public void setAnno(int anno) {
		this.anno = anno;
	}



	public Set<Integer> getPaesi() {
		return paesi;
	}



	public void setPaesi(Set<Integer> paesi) {
		this.paesi = paesi;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anno;
		result = prime * result + ((paesi == null) ? 0 : paesi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (anno != other.anno)
			return false;
		if (paesi == null) {
			if (other.paesi != null)
				return false;
		} else if (!paesi.equals(other.paesi))
			return false;
		return true;
	}

	
	
}
