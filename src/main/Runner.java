public class Runner {
  public static void main(String[] args) {
    Node a = new Node("pat", "firstname");
    Node b = new Node("lip", "item");
    Node c = new Node("paula", "firstname");
    Node d = new Node("jones", "lastname");
    Node e = new Node("Doctor", "job");
    Node f = new Node("lawyer", "job");
    Node g = new Node("smith", "lastname");
    Node h = new Node("file", "item");

    Node i = new Node("teacher", "job");
    Node j = new Node("pam", "firstname");
    Node k = new Node("johnson", "lastname");
    Node l = new Node("compact", "item");

    Node[] list = new Node[12];
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
    WeightedGraph wg = new WeightedGraph(list);
    wg.printGraph();
//test2
  }


}
