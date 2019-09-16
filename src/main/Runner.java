public class Runner {
  public static void main(String[] args) {
    Node a = new Node("pat", "firstname");
    Node b = new Node("lipstick", "item");
    Node c = new Node("paula", "firstname");
    Node d = new Node("jones", "lastname");
    Node e = new Node("Doctor", "job");

    Node[] list = new Node[5];
    list[0] = a;
    list[1] = b;
    list[2] = c;
    list[3] = d;
    list[4] = e;
    WeightedGraph wg = new WeightedGraph(list);
    //wg.buildGraph();

  }


}
