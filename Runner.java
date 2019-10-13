//this class is purely for testing purposes without having to individually type in all of the user 
//input. We've added onto this class to prove that our program works with a graph larger than 20 nodes. 




public class Runner {
  public static void main(String[] args) {
    Node a = new Node("pat", "firstname");
    Node b = new Node("lip", "item");
    Node c = new Node("paula", "firstname");
    Node d = new Node("jones", "lastname");
    Node e = new Node("doctor", "job");
    Node f = new Node("lawyer", "job");
    Node g = new Node("smith", "lastname");
    Node h = new Node("file", "item");

    Node i = new Node("teacher", "job");
    Node j = new Node("pam", "firstname");
    Node k = new Node("johnson", "lastname");
    Node l = new Node("compact", "item");

    Node m = new Node("pilot", "job");
    Node n = new Node("peggy", "firstname");
    Node o = new Node("dixon", "lastname");
    Node p = new Node("comb", "item");

    Node q = new Node("judge", "job");
    Node r = new Node("penny", "firstname");
    Node s = new Node("doe", "lastname");
    Node t = new Node("keyring", "item");
    
    Node u= new Node("red","hair");
    Node v= new Node("brown","hair");
    Node w= new Node("blonde", "hair");
    Node x= new Node("black","hair");
    Node y= new Node("strawberry","hair");
    Node z= new Node("sandy","hair");
    
    Node aa= new Node("patrice","firstname");
    Node bb= new Node("nurse","job");
    Node cc= new Node("miller","lastname");
    Node ab= new Node("wallet","item");
    

    Node[] list = new Node[30];
    list[0] = a;
    list[1] = b;
    list[2] = c;
    list[3] = d;
    list[4] = e;
    list[5] = f;
    list[6] = g;
    list[7] = h;
    list[8] = i;
    list[9] = j;
    list[10] = k;
    list[11] = l;
    list[12] = m;
    list[13] = n;
    list[14] = o;
    list[15] = p;
    list[16] = q;
    list[17] = r;
    list[18] = s;
    list[19] = t;
    list[20]=u;
    list[21]=v;
    list[22]=w;
    list[23]=x;
    list[24]=y;
    list[25]=z;
    list[26]=aa;
    list[27]=bb;
    list[28]=cc;
    list[29]=ab;
    
    WeightedGraph wg = new WeightedGraph(list);
    //wg.printGraph();
    
    wg.setCatNum(5);;
    wg.setItemNum(6);

    //#1 Pilot last name johnson lost file
    wg.addConnection(m, k, true, true);
    wg.addConnection(h, m, true, true);
    wg.addConnection(h, k, true, true);

    //#2 teacher lost a comb
    wg.addConnection(i, p, true, true);

    //#3 dixon lost lipstick
    wg.addConnection(o, b, true, true); //dixon lipstick

    //#4 paula penny peggy not docs didn't lose ombs
    wg.addConnection(c, e, false, true);
    wg.addConnection(c, p, false, true);
    wg.addConnection(r, e, false, true);
    wg.addConnection(r, p, false, true);
    wg.addConnection(n, e, false, true);
    wg.addConnection(n, p, false, true);

    //#5 pam didn't lose file or keyring
    wg.addConnection(j, h, false, true);
    wg.addConnection(j, t, false, true);

    //#6 paula, not smith or doe, lost compact
    wg.addConnection(c, g, false, true);
    wg.addConnection(c, s, false, true);
    wg.addConnection(c, l, true, true);

    //#7 penny not lawyer or judge
    wg.addConnection(r, f, false, true);
    wg.addConnection(r, q, false, true); //judge  penny NOT

    //#8 peggy not teacher, jones, smith, or johnson
    wg.addConnection(n, i, false, true);
    wg.addConnection(n, d, false, true);
    wg.addConnection(n, g, false, true);
    wg.addConnection(n, k, false, true);

    //#9 jones not judge, doe not doc
    wg.addConnection(c, q, false, true); //judge  paula NOT
    wg.addConnection(s, e, false, true);

    //#10 peggy and pat didn't lose lipstick
    wg.addConnection(n, b, false, true);
    wg.addConnection(a, b, false, true);
    
    //#11 patrice does not have red hair, and lost her wallet and is a nurse
    wg.addConnection(aa, u, false, true);
    wg.addConnection(aa,  ab,  true, true);
    wg.addConnection(aa, bb, true, true);
    
    //#12 miller has brown hair, jones has red hair, dixon has black hair
    wg.addConnection(cc,  v, true, true);
    wg.addConnection(c, u, true, true);
    wg.addConnection(o, x, true, true);

    //#13 smith has blonde hair, Johnson has strawberry hair, doe has sandy hair
    wg.addConnection(aa, cc, true, true);
    wg.addConnection(g, w, true, true);
    wg.addConnection(k, y, true, true);
    wg.addConnection(s, z, true, true);



    System.out.println(wg.checkForCompletion());
    wg.printGraph();
    System.out.println();
    wg.printCompleteGraph();
//test2
  }


}
