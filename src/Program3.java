import java.util.*;

/**
 * Created by Brent Atchison on 4/23/17.
 */
public class Program3 {
	
	/**
	 * @param ap
	 * @return ActivityResult
	 */
    public ActivityResult selectActivity(ActivityProblem ap){
    	
    	/* Constructs for Storing Results */
    	Set<String> selectedActivities = new HashSet<String>();
    	int table[][] = new int[ap.getActivities().length + 1][ap.getRiskBudget() + 1];
    	boolean[][] keptActivities = new boolean[ap.getActivities().length + 1][ap.getRiskBudget() + 1];
    	
    	/* Populate Results Table */
    	for (int i = 0; i <= ap.getActivities().length; ++i) {
    		for (int j = 0; j <= ap.getRiskBudget(); ++j) {
    			if (i == 0 || j == 0) { table[i][j] = 0; }
    			else if (ap.getRiskLevels()[i - 1] <= j) {
        			int a = ap.getFunLevels()[i - 1] + table[i - 1][j - ap.getRiskLevels()[i - 1]];
        			int b = table[i - 1][j];
    				table[i][j] = Math.max(a, b);
    				keptActivities[i][j] = a > b ? true : false;
				}
    			else { table[i][j] = table[i - 1][j]; }
    		}
    	} int maxFunLevel = table[ap.getActivities().length][ap.getRiskBudget()];
    	
    	/* Populate Array of Kept Activities */
        for (int i = ap.getActivities().length, j = ap.getRiskBudget(); i > 0; --i) {
            if (keptActivities[i][j] == true) {
            	selectedActivities.add(ap.getActivities()[i - 1]);
                j = j - ap.getRiskLevels()[i - 1];
            }
        }
        
        return new ActivityResult(maxFunLevel, selectedActivities);
    }
    
    /* Graph Class */
    class Graph {
        
    	/* Graph Information */
        int V, E;
        Edge edge[];
        
		/* Weighted Edge Class */
        class Edge {
            int src, dest, weight;
            Edge() { src = dest = weight = 0; }
        };
     
        /* Create a Graph with Vertices and Edges */
        Graph(int numVertices, int numEdges) {
            V = numVertices; E = numEdges;
            edge = new Edge[numEdges];
            for (int i = 0; i < numEdges; ++i) { edge[i] = new Edge(); }
        }
    }
    
    /**
     * @param sp
     * @return SchedulingResult
     */
    public SchedulingResult selectScheduling(SchedulingProblem sp){
    	
    	/* Result Array */
    	boolean[] schedule = new boolean[sp.mauiCosts.length];
    	
    	/* MauiCosts and OahuCosts Differ in Length */
    	if (sp.getMauiCosts().length != sp.getOahuCosts().length) { return null; }
    	
    	/* Vacation is 0 Days */
    	if (sp.getMauiCosts().length == 0) { return new SchedulingResult(schedule); }
    	
    	/* Vacation is 1 Day */
    	if (sp.getMauiCosts().length == 1) {
    		schedule[0] = (sp.getMauiCosts()[0] < sp.getOahuCosts()[0]) ? false : true;
    		return new SchedulingResult(schedule);
    	}
    	
    	/* Create a Graph from Maui and Oahu */
    	int numVertices = sp.getMauiCosts().length + sp.getOahuCosts().length + 2;
    	int numEdges = (numVertices * 2) - 4;
    	Graph graph = new Graph(numVertices, numEdges);
    	
    	/* Set Start Edges */
		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = sp.getMauiCosts()[0];
		graph.edge[sp.getMauiCosts().length + 1].src = 0;
		graph.edge[sp.getMauiCosts().length + 1].dest = sp.getMauiCosts().length + 1;
		graph.edge[sp.getMauiCosts().length + 1].weight = sp.getOahuCosts()[0];
		
    	/* Set End Edges */
		graph.edge[sp.getMauiCosts().length].src = sp.getMauiCosts().length;
		graph.edge[sp.getMauiCosts().length].dest = numVertices - 1;
		graph.edge[sp.getMauiCosts().length].weight = 0;
		graph.edge[numVertices - 1].src = numVertices - 2;
		graph.edge[numVertices - 1].dest = numVertices - 1;
		graph.edge[numVertices - 1].weight = 0;
		
    	/* Set Maui Edges */
    	for (int i = 1; i < sp.getMauiCosts().length; ++i) {
    		graph.edge[i].src = i;
    		graph.edge[i].dest = i + 1;
    		graph.edge[i].weight = sp.getMauiCosts()[i];
    	}
    	
    	/* Set Oahu Edges */
    	for (int i = sp.getMauiCosts().length + 2, j = 1; i < numVertices - 1; ++i, ++j) {
    		graph.edge[i].src = i - 1;
    		graph.edge[i].dest = i;
    		graph.edge[i].weight = sp.getOahuCosts()[j];
    	}
    	
    	/* Set Maui to Oahu Edges */
    	for (int i = 1, j = sp.getMauiCosts().length + 2, k = numVertices; i < sp.getMauiCosts().length; ++i, ++j, ++k) {
    		graph.edge[k].src = i;
    		graph.edge[k].dest = j;
    		graph.edge[k].weight = sp.getOahuCosts()[i] + sp.getTransferCost();
    	}
    	
    	/* Set Oahu to Maui Edges */
    	for (int i = sp.getMauiCosts().length + 1, j = 2, k = numEdges - sp.getMauiCosts().length + 1; i < numVertices - 2; ++i, ++j, ++k) {
    		graph.edge[k].src = i;
    		graph.edge[k].dest = j;
    		graph.edge[k].weight = sp.getMauiCosts()[j - 1] + sp.getTransferCost();
    	}
    	
    	/* Array to Hold Distances from Source */
        int dist[] = new int[graph.V];
        
        /* Everything Starts Infinite Distance from Source */
        for (int i = 0; i < graph.V; ++i) { dist[i] = Integer.MAX_VALUE; }
        
        /* Distance from Source to Itself is 0 */
        dist[0] = 0;
        
        /* Relax All Edges in a Loop */
        for (int i = 1; i < graph.V; ++i) {
            for (int j = 0; j < graph.E; ++j) {
                int a = graph.edge[j].src;
                int b = graph.edge[j].dest;
                int w = graph.edge[j].weight;
                if (dist[a] != Integer.MAX_VALUE && dist[a] + w < dist[b]) { dist[b] = dist[a] + w; }
            }
        }
        
        /* Check for Negative-Weight Cycles */
        for (int j = 0; j < graph.E; ++j) {
            int a = graph.edge[j].src;
            int b = graph.edge[j].dest;
            int w = graph.edge[j].weight;
            if (dist[a] != Integer.MAX_VALUE && dist[a] + w < dist[b]) {
                System.out.println("Negative-Weight Cycle");
            }
        }
        
        /* Backtrack to Find Path Taken */
        for (int i = sp.getMauiCosts().length, j = graph.V - 2, k = graph.V - 1; i > 0; --i, --j) {
        	if (dist[i] == dist[k]) {
        		schedule[i - 1] = true;
        		dist[k] -= sp.getMauiCosts()[i - 1];
        	} else {
        		schedule[i - 1] = false;
        		dist[k] -= sp.getOahuCosts()[i - 1];
        	}
        	if (dist[k] != dist[i - 1] && dist[k] != dist[j - 1]) { dist[k] -= sp.getTransferCost(); }
        }
    	
        return new SchedulingResult(schedule);
    }

}
