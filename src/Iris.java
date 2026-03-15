import java.util.Arrays;

public class Iris {
    double[] features;
    String label;

    public Iris(double[] features, String label) {
        this.features = features;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "features=" + Arrays.toString(features) +
                ", label='" + label + '\'' +
                '}';
    }
}
