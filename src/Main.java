import java.util.Scanner;

public class Main {
    public static void main() throws Exception {
        KNN.loadData("data/iris.txt");

        KNN.splitData(0.75);

        KNN.evaluateModel();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1 - Klasyfikuj ręcznie");
            System.out.println("2 - Test z pliku");
            System.out.println("0 - Wyjście");

            int choice = sc.nextInt();

            if (choice == 1) KNN.manualInput(sc);
            else if (choice == 2) KNN.fileTest(sc);
            else break;
        }
    }
}
