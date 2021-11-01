import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//import testLca2.TestingDAG;
//import testLca2.TestingDAG.DirectedDFS;

public class lowestCA2 {
	
	private final int R;
	private final ArrayList<Integer>[] a;
	private final ArrayList<Integer>[] listBck;


	
	public lowestCA2(int R)
	{
		this.R = R;
		a = (ArrayList<Integer>[]) new ArrayList[R];


		listBck = (ArrayList<Integer>[]) new ArrayList[R];

		for (int r = 0; r < R; r++)
		{
			a[r] = new ArrayList<Integer>();



			listBck[r] = new ArrayList<Integer>();
		}
	}

public boolean edge(int r, int w)
{
	if(r >= this.R || w >= this.R || r < 0 || w < 0){ 
		return false;
	}
	

	if(r != w && !hasPath(w, r) && !a[r].contains(w)){
		a[r].add(w);
		listBck[w].add(r);
		return true;
	}	
	else{
		return false;
	}
}

public int R(){
	return R;
}

public ArrayList<Integer> adj(int r)
{ return a[r]; }

public ArrayList<Integer> reverseAdj(int r)
{ return listBck[r]; }

public boolean hasPath(int x, int y){
	DirectedDFS dfsObj = new DirectedDFS(this, x);
	return dfsObj.visited(y);
}

public ArrayList<Integer> lca(int x, int y)
{
	
	ArrayList<Integer> listLC = new ArrayList<Integer>();
	int currentMaxDist = Integer.MAX_VALUE;
	
	
	if(x==y || x>=this.R || y>=this.R || x<0 || y<0) { return listLC; } //If invalid input return empty bag.
	
	DirectedDFS dfsObject = new DirectedDFS(this, x);
	dfsObject.reverseDfs(this, x);
	int xDist, yDist;
	
	for(int r = 0; r < this.R; r++)
	{
	
		if(dfsObject.revVisited(r) && hasPath(r, y))
		{
			xDist = getDistance(r, x);
			yDist = getDistance(r, y);
			
			if(Integer.max(xDist, yDist) < currentMaxDist)
			{		
				listLC = new ArrayList<Integer>();
				listLC.add(r);
				currentMaxDist = Integer.max(xDist, yDist);
			}
			else if(Integer.max(xDist, yDist) == currentMaxDist)
			{
				listLC.add(r);
				currentMaxDist = Integer.max(xDist, yDist);
			}
		}
	}
	return listLC;
}

private int getDistance(int x, int target)
{
    
		if( x == target) { return 0; }
		else {
	        Queue<Integer> q = new LinkedList<Integer>();
	        int[] distTo = new int[this.R];
	        boolean[] marked = new boolean[this.R];
	        
	        for (int r = 0; r < this.R(); r++)
	        {   distTo[r] = Integer.MAX_VALUE;}
	        
	        distTo[x] = 0;
	        marked[x] = true;
	        q.add(x);
	
	        while (!q.isEmpty()) {
	            int r = q.remove();
	            for (int w : this.adj(r)) {
	                if (!marked[w]) {
	                	
	                	distTo[w] = distTo[r] + 1;
	                    marked[w] = true;
	                    
	                    q.add(w);
	                }
	            }
	        }
	        
	        return distTo[target];
		}
}


private class DirectedDFS
{
	private boolean[] comp;
	private boolean[] compbck;
	
	public DirectedDFS(lowestCA2 G, int s)
	{
		comp = new boolean[G.R()];
		compbck = new boolean[G.R()];
		dfs(G, s);
	}
	
	

	private void dfs(lowestCA2 G, int r)
	{
		comp[r] = true;
		for (int w : G.adj(r))
		if (!comp[w]) dfs(G, w);
	}
	
	
	private void reverseDfs(lowestCA2 G, int r)
	{
		compbck[r] = true;
		for (int w : G.reverseAdj(r))
		if (!compbck[w]) reverseDfs(G, w);
	}
	
	public boolean visited(int r)
	{ return comp[r]; }
	
	public boolean revVisited(int r)
	{ return compbck[r]; }
}
	

}
