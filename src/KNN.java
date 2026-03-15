import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class KNN {

    static List<Iris> data = new ArrayList<>();
    static List<Iris> train = new ArrayList<>();
    static List<Iris> test = new ArrayList<>();

    static int k = 5;

    static void loadData(String filename) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        br.readLine();

        while ((line = br.readLine()) != null){
            String[] parts = line.split(",");

            double[] features = new double[4];

            for (int i = 0; i < 4; i++) {
                features[i] = Double.parseDouble(parts[i]);
            }
            data.add(new Iris(features, parts[4]));
        }
    }

    static void splitData(double trainRatio){
        List<Iris> irisSetosa = new ArrayList<>();
        List<Iris> irisVersicolor = new ArrayList<>();
        List<Iris> irisVirginica = new ArrayList<>();

        for (Iris datum : data) {
            if (datum.label.equals("Iris-setosa"))
                irisSetosa.add(datum);
            else if (datum.label.equals("Iris-versicolor"))
                irisVersicolor.add(datum);
            else
                irisVirginica.add(datum);
        }

        Collections.shuffle(irisSetosa);
        Collections.shuffle(irisVersicolor);
        Collections.shuffle(irisVirginica);

        int trainSize = (int)(data.size() * trainRatio / 3);

        for (int i = 0; i < trainSize; i++) {
            train.add(irisSetosa.get(i));
            train.add(irisVersicolor.get(i));
            train.add(irisVirginica.get(i));
        }
        for (int i = trainSize; i < data.size() / 3; i++) {
            test.add(irisSetosa.get(i));
            test.add(irisVersicolor.get(i));
            test.add(irisVirginica.get(i));
        }
    }

    static double distance(double[] a, double[] b){
        double sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    static String classify(double[] vector){
        List<Iris> neighbors = new ArrayList<>(train);

        neighbors.sort(Comparator.comparingDouble(
                iris -> distance(vector, iris.features)
        ));

        Map<String, Integer> votes = new HashMap<>();

        for (int i = 0; i < k; i++) {
            String label = neighbors.get(i).label;
            votes.put(label, votes.getOrDefault(label, 0) + 1);
        }

        return Collections.max(votes.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    static void evaluateModel(){
        int correct = 0;
        for (Iris iris : test){
            String predicted = classify(iris.features);

            if (predicted.equals(iris.label))
                correct++;
        }
        double accuracy = 100.0 * correct / test.size();

        System.out.println("Celność modelu: " + accuracy + "%");
    }
}
