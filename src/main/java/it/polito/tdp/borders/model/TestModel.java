package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Map;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		model.creaGrafo(2000);
		
		//System.out.println("TestModel -- TODO");
		
//		System.out.println("Creo il grafo relativo al 2000");
//		model.createGraph(2000);
		
		Map<Integer,Country> countries = model.getCountries();
		System.out.format("Trovate %d nazioni\n", countries.size());

		//System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
	}

}
