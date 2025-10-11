
public class Main {
    public static void main(String[] args) {
        // --- Datos binarios ---
        double[][] binaryInputs = { {0, 0}, {0, 1}, {1, 0}, {1, 1} };
        int[] andLabels = {0, 0, 0, 1};
        int[] orLabels  = {0, 1, 1, 1};

        // --- Datos bipolares ---
        double[][] bipolarInputs = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
        int[] bipolarAndLabels = {-1, -1, -1, 1};
        int[] bipolarOrLabels  = {-1, 1, 1, 1};

        System.out.println("\n===== BINARIO: AND (sin bias) =====");
        Perceptron binAndNoBias = new Perceptron(2, 0.003, false);
        binAndNoBias.train(binaryInputs, andLabels, 100000, false);
        binAndNoBias.printVerification(binaryInputs, andLabels, "Compuerta AND (sin bias)", false);
/**
        System.out.println("\n===== BINARIO: AND (con bias) =====");
        Perceptron binAndBias = new Perceptron(2, 0.01, false);
        binAndBias.train(binaryInputs, andLabels, 1000, true);
        binAndBias.printVerification(binaryInputs, andLabels, "Compuerta AND (con bias)", true);

        System.out.println("\n===== BINARIO: OR (sin bias) =====");
        Perceptron binOrNoBias = new Perceptron(2, 0.01, false);
        binOrNoBias.train(binaryInputs, orLabels, 1000, false);
        binOrNoBias.printVerification(binaryInputs, orLabels, "Compuerta OR (sin bias)", false);
        */
        System.out.println("\n===== BINARIO: OR (con bias) =====");
        Perceptron binaryOR = new Perceptron(2, 0.01, false);
        binaryOR.train(binaryInputs, orLabels, 1000, true);
        binaryOR.printVerification(binaryInputs, orLabels, "Compuerta OR", true);

        System.out.println("\n===== BIPOLAR: AND (sin bias) =====");
        Perceptron bipAndNoBias = new Perceptron(2, 0.003, true);
        bipAndNoBias.train(bipolarInputs, bipolarAndLabels, 100000, false);
        bipAndNoBias.printVerification(bipolarInputs, bipolarAndLabels, "Compuerta AND (sin bias)", false);
/**
        System.out.println("\n===== BIPOLAR: AND (con bias) =====");
        Perceptron bipAndBias = new Perceptron(2, 0.01, true);
        bipAndBias.train(bipolarInputs, bipolarAndLabels, 1000, true);
        bipAndBias.printVerification(bipolarInputs, bipolarAndLabels, "Compuerta AND (con bias)", true);

        System.out.println("\n===== BIPOLAR: OR (sin bias) =====");
        Perceptron bipOrNoBias = new Perceptron(2, 0.01, true);
        bipOrNoBias.train(bipolarInputs, bipolarOrLabels, 1000, false);
        bipOrNoBias.printVerification(bipolarInputs, bipolarOrLabels, "Compuerta OR (sin bias)", false);
*/
        System.out.println("\n===== BIPOLAR: OR (con bias) =====");
        Perceptron bipOrBias = new Perceptron(2, 0.01, true);
        bipOrBias.train(bipolarInputs, bipolarOrLabels, 1000, true);
        bipOrBias.printVerification(bipolarInputs, bipolarOrLabels, "Compuerta OR (con bias)", true);

    }

}