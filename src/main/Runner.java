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
    //wg.printGraph();
    /*
    wg.addConnection(m, k, true, true);
    wg.addConnection(h, m, true, true);
    wg.addConnection(h, k, true, true);

    wg.addConnection(i, p, true, true);

    wg.addConnection(o, b, true, true);

    wg.addConnection(c, e, false, false);
    wg.addConnection(c, p, false, false);
    wg.addConnection(r, e, false, false);
    wg.addConnection(r, p, false, false);
    wg.addConnection(n, e, false, false);
    wg.addConnection(n, p, false, false);

    wg.addConnection(j, h, false, false);
    wg.addConnection(j, t, false, false);

    wg.addConnection(c, g, false, false);
    wg.addConnection(c, s, false, false);
    wg.addConnection(c, l, true, true);

    wg.addConnection(r, f, false, false);
    wg.addConnection(r, q, false, false);

    wg.addConnection(n, i, false, false);

    wg.addConnection(c, q, false, false);
    wg.addConnection(s, e, false, false);

    wg.addConnection(n, b, false, false);
    wg.addConnection(a, b, false, false);

    wg.addConnection(o, q, true, true);*/


    //tests to show that .connectRows() works as intended
    wg.addConnection(a, p, true, true);  //pat 0  and comb 8
    wg.addConnection(e, p, true, true);  //doctor 10   and comb 8
    wg.addConnection(o, e, false, false);
    wg.printGraph();
//test2
  }


}
