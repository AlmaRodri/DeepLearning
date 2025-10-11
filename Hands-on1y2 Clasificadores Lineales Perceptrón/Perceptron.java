import java.util.Random;

/**
 * Implementa un clasificador Perceptrón que puede funcionar con:
 * - Entradas y salidas binarias (0,1)
 * - Entradas y salidas bipolares (-1,1)
 * Puede usar o no bias, según configuración.
 */
public class Perceptron {
    private double[] weights;
    private double bias;
    private final double learningRate;
    private final boolean bipolar; // true = bipolar, false = binario

    /**
     * Constructor para el Perceptrón.
     * @param numberOfInputs Número de señales de entrada.
     * @param learningRate Velocidad de aprendizaje.
     * @param bipolar Si el perceptrón usará entradas/salidas bipolares.
     */
    public Perceptron(int numberOfInputs, double learningRate, boolean bipolar) {
        this.learningRate = learningRate;
        this.bipolar = bipolar;
        this.weights = new double[numberOfInputs];
        Random random = new Random();

        for (int i = 0; i < numberOfInputs; i++) {
            this.weights[i] = random.nextDouble() * 2 - 1; // [-1, 1]
        }
        this.bias = random.nextDouble() * 2 - 1;
    }

    // --- Funciones de Activación ---

    private int stepFunction(double weightedSum) {
        return (weightedSum >= 0) ? 1 : 0;
    }

    private int signFunction(double weightedSum) {
        return (weightedSum >= 0) ? 1 : -1;
    }

    // --- Predicción según tipo ---
    public int predict(double[] inputs, boolean useBias) {
        double weightedSum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            weightedSum += weights[i] * inputs[i];
        }
        if (useBias) weightedSum += bias;

        return (bipolar) ? signFunction(weightedSum) : stepFunction(weightedSum);
    }

    // --- Entrenamiento ---
    public void train(double[][] trainingInputs, int[] labels, int epochs, boolean useBias) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            int totalError = 0;
            for (int i = 0; i < trainingInputs.length; i++) {
                int prediction = predict(trainingInputs[i], useBias);
                int error = labels[i] - prediction;

                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * trainingInputs[i][j];
                }
                if (useBias) bias += learningRate * error;

                totalError += Math.abs(error);
            }

            if (totalError == 0) {
                System.out.println("Convergencia alcanzada en la época " + (epoch + 1));
                return;
            }
        }
        System.out.println("El modelo no convergió después de " + epochs + " épocas.");
    }

    // --- Verificación de resultados ---
    public void printVerification(double[][] inputs, int[] labels, String gateName, boolean useBias) {
        System.out.println("\n🔍 VERIFICACIÓN PARA: " + gateName + (bipolar ? " [Bipolar]" : " [Binario]"));

        // Imprimir pesos
        System.out.print("Pesos: ");
        for (int k = 0; k < weights.length; k++) {
            System.out.printf("w%d = %.4f  ", k + 1, weights[k]);
        }
        if (useBias) System.out.printf(" bias = %.4f", bias);
        System.out.println("\n------------------------------------------");

        // Por cada patrón
        for (int i = 0; i < inputs.length; i++) {
            // Representación de la entrada
            StringBuilder inputSB = new StringBuilder("[");
            for (int j = 0; j < inputs[i].length; j++) {
                inputSB.append(String.format("%.0f", inputs[i][j]));
                if (j < inputs[i].length - 1) inputSB.append(", ");
            }
            inputSB.append("]");

            double weightedSum = 0.0;
            StringBuilder calc = new StringBuilder();

            // términos wi * xi
            for (int j = 0; j < weights.length; j++) {
                double term = weights[j] * inputs[i][j];
                weightedSum += term;
                calc.append(String.format("(%.4f * %.0f = %.4f)", weights[j], inputs[i][j], term));
                if (j < weights.length - 1) calc.append(" + ");
            }

            // bias si aplica
            if (useBias) {
                weightedSum += bias;
                calc.append(String.format(" + (bias = %.4f)", bias));
            }

            int activation = (bipolar) ? signFunction(weightedSum) : stepFunction(weightedSum);
            boolean coincide = (activation == labels[i]);

            // Impresión detallada
            System.out.printf("Patrón %s:\n", inputSB.toString());
            System.out.printf("  Cálculo: %s\n", calc.toString());
            System.out.printf("  Suma ponderada (z) = %.4f\n", weightedSum);
            if (bipolar) {
                System.out.printf("  Activación (sign) = %d   (Esperado: %d)\n", activation, labels[i]);
            } else {
                System.out.printf("  Activación (step) = %d   (Esperado: %d)\n", activation, labels[i]);
            }
            System.out.printf("  Resultado: %s\n\n", coincide ? " Coincide" : " No coincide");
        }
    }
}
