package it.unibs.ing.fp.arnaldo.tamagolem;
import java.util.*;

public class Graph {


	private Map<Node, List<Node>> adjacentList;

	public Graph(Map<Node, List<Node>> adjacentList) {
		this.adjacentList = adjacentList;
	}

	public Map<Node, List<Node>> getAdjacentList() {
		return adjacentList;
	}
	
	public void addNode(Node nodo) {
		adjacentList.put(nodo, new LinkedList<Node>());
	}
	
	public void addListElement(Node nodo,Node newNode) {
		adjacentList.get(nodo).add(newNode);
	}
	

	public void setAdjacentList(Map<Node, List<Node>> adjacentList) {
		this.adjacentList = adjacentList;
	}
	
	public void printGraph() {
		for (Node nodo : adjacentList.keySet()) {
			System.out.println("L'elemento "+nodo.keyNode()+" è: \n" + adjacentList.get(nodo));
		}
	}
	
}
