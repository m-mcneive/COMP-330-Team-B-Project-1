public class Runner {
  public static void main(String[] args) {
    Node a = new Node("pat", "firstname");
    Node b = new Node("lipstick", "item");
    Node c = new Node("paula", "firstname");
    Node d = new Node("jones", "lastname");
    Node[] list = new Node[4];
    list[0] = a;
    list[1] = b;
    list[2] = c;
    list[3] = d;
    WeightedGraph wg = new WeightedGraph(list);
    //wg.buildGraph();

  }


}
