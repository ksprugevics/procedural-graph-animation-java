package utils;

import components.Node;

import static gui.AnimationPanel.WINDOW_HEIGHT;
import static gui.AnimationPanel.WINDOW_WIDTH;
import static utils.RandomGeneration.randomFloat;

public class Calculations {

    public static float[] generateRandomLineEquationThroughPoint(float xCoord, float yCoord) {
        float x2 = randomFloat(0f, WINDOW_WIDTH);
        float y2 = randomFloat(0f, WINDOW_HEIGHT);
        float a = (y2 - yCoord) / (x2 - xCoord);
        float b = yCoord - a * xCoord;
        return new float[]{a, b};
    }

    public static float euclideanDistanceBetweenNodes(Node node1, Node node2) {
        return (float) Math.sqrt(Math.pow(node1.getxPos() - node2.getxPos(), 2) +
                Math.pow(node1.getyPos() - node2.getyPos(), 2));
    }

    public static float calculateLineEquation(float xVal, float[] lineEquation){
        return lineEquation[0] * xVal + lineEquation[1];
    }

    public static float calculateInverseLineEquation(float yVal, float[] lineEquation){
        return (yVal - lineEquation[1]) / lineEquation[0];
    }
}
