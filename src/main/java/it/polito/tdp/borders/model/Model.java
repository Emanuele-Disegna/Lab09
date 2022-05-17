package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private BordersDAO dao;
	private Map<Integer, Country> countries;
	private List<Border> borders;
	
	public Model() {
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		dao = new BordersDAO();
	
	}



	public void creaGrafo (int anno) {
		
		borders = new ArrayList<>(dao.getCountryPairs(anno));
		
		Graphs.addAllVertices(grafo, countries.values());
		
		for(Border b : borders) {
			if(isRecente(b,borders)) {
				grafo.addEdge(countries.get(b.getPaese1()), countries.get(b.getPaese2()));
			}
		}
		
	}
	
	private boolean isRecente(Border border, List<Border> borders2) {
		List<Border> lista = new ArrayList<>();
		
		for(Border b : borders2) {
			if(b.getPaesi().containsAll(border.getPaesi())) {
				lista.add(b);
			}
		}
		
		Collections.sort(lista, Comparator.comparing(Border::getAnno));
		
		if(border.getAnno()==lista.get(lista.size()-1).getAnno()) {
			return true;
		}
		
		return false;
	}

	public int getNumeroStatiConfinanti(Country c) {
		return Graphs.neighborListOf(grafo, c).size();
	}
	
	public int getComponenteConnessa(Country c) {
		ConnectivityInspector<Country,DefaultEdge> conn = new ConnectivityInspector<>(grafo);
		return conn.connectedSetOf(c).size();
	}
	
	public Map<Integer,Country> getCountries() {
		if(countries==null) {
			countries = dao.loadAllCountries();
		}
		return countries;
	}
	
	public List<Country> getRaggiungibili(Country c, int anno) {
		if(grafo.vertexSet().size()==0) {
			creaGrafo(anno);
		}
		
		BreadthFirstIterator<Country,DefaultEdge> visita = new BreadthFirstIterator<>(grafo,c);
		List<Country> res = new ArrayList<>();
		
		while(visita.hasNext()) {
			res.add(visita.next());
		}
		
		return res;
	}
	
}
