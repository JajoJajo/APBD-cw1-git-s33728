import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KNN {

    static List<Iris> data = new ArrayList<>();
    static List<Iris> train = new ArrayList<>();
    static List<Iris> test = new ArrayList<>();



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
}
