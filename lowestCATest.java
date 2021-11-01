import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class lowestCATest {
	
	
	
	
	@Test
	public void Test1() {
		lowestCA.LcaClass BST = new lowestCA.LcaClass();
		BST.root = new lowestCA.Node(8);
		BST.root.left = new lowestCA.Node(12);
        BST.root.right = new lowestCA.Node(4);


        assertEquals( 8,BST.findLCA(12,4));
        assertEquals( 8,BST.findLCA(4,12));
        
	}
	
	
	@Test
    public void TestWithThree() {
		lowestCA.LcaClass BST = new lowestCA.LcaClass();
        BST.root = new lowestCA.Node(50);
        BST.root.left = new lowestCA.Node(13);
        BST.root.right = new lowestCA.Node(10);
        BST.root.left.left = new lowestCA.Node(1);
        BST.root.left.right = new lowestCA.Node(3);
        BST.root.right.left = new lowestCA.Node(8);
        BST.root.right.right = new lowestCA.Node(4);




        assertEquals( 50,BST.findLCA(1, 4));
        assertEquals( 10,BST.findLCA(8, 4));
        
	}
	
	@Test
	public void dagTest(){
		
		lowestCA2 dag = new lowestCA2(5);
		
		dag = new lowestCA2(5);
		dag.edge(0, 1);
		dag.edge(0, 3);
		dag.edge(2, 3);
		dag.edge(3, 4);
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(5);
		for(int i=0; i<res.size();i++){
			assertTrue( dag.lca(1,4).contains(i));
		}
	}


}
