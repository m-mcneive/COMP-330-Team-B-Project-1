public class aSecondRunner {
  public static void main(String[] args) {
    Node a = new Node("vic", "firstname");
    Node b = new Node("sleep", "task");
    Node c = new Node("val", "firstname");
    Node d = new Node("law", "show");
    Node e = new Node("tabby", "cat");
    Node f = new Node("tiger", "cat");
    Node g = new Node("cheers", "show");
    Node h = new Node("read", "task");

    Node i = new Node("toby", "cat");
    Node j = new Node("vinnie", "firstname");
    Node k = new Node("simpson", "show");
    Node l = new Node("climb", "task");

    Node m = new Node("tom", "cat");
    Node n = new Node("vera", "firstname");
    Node o = new Node("90210", "show");
    Node p = new Node("run", "task");

    Node q = new Node("tasha", "cat");
    Node r = new Node("van", "firstname");
    Node s = new Node("star trek", "show");
    Node t = new Node("sing", "task");

    Node[] list = new Node[20];

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
    WeightedGraph wg = new WeightedGraph(list);


    //1 tom likes to climb
    wg.addConnection(l, m, true, true);

    //2 vic and val dont like sleeping or star trek
    wg.addConnection(a, b, false, true);
    wg.addConnection(a, s, false, true);
    wg.addConnection(c, b, false, true);
    wg.addConnection(c, s, false, true);

    //3 tabbys owner likes sleep and star trek
    wg.addConnection(e, b, true, true);
    wg.addConnection(e, s, true, true);

    //4 vera likes sing and 90210
    wg.addConnection(n, t, true, true);
    wg.addConnection(n, o, true, true);

    //5 van hates sleeping, law, cheers
    wg.addConnection(r, b, false, true);
    wg.addConnection(r, d, false, true);
    wg.addConnection(r, g, false, true);

    //6 val hates climbing and cheers
    wg.addConnection(c, g, false, true);
    wg.addConnection(c, l, false, true);

    //7 vics pet is toby
    wg.addConnection(a, i, true, true);

    //8 val likes reading, tasha
    wg.addConnection(c, q, true, true);
    wg.addConnection(c, h, true, true);






    System.out.println(wg.checkForCompletion());
    wg.printGraph();
    System.out.println();
    wg.printCompleteGraph();
  }
}
