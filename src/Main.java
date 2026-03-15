public class Main {
    public static void main() {
        try {
            KNN.loadData("data/iris.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        KNN.splitData(0.5);
        for (Iris data : KNN.test)
            System.out.println(data);
        System.out.println(KNN.test.size());
        System.out.println(KNN.train.size());
    }
}
