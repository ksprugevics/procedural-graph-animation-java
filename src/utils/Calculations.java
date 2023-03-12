package utils;

import components.Node;

import static gui.AnimationPanel.LINE_MAX_DISTANCE;
import static gui.AnimationPanel.LINE_MAX_WIDTH;
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

    // Easing function - https://easings.net/#easeInOutQuad
    public static float easeInOutQuad(float x) {
        if (x < 0.5) {
            return (float) 2 * x * x;
        }
        else {
            return (float) (1 - Math.pow(-2 * x + 2, 2) / 2);
        }
    }

    public static int calculateAlphaByDistance(float distance) {
        return Math.round(easeInOutQuad(1 - distance / LINE_MAX_DISTANCE) * 255);
    }

    public static float calculateStrokeThicknessByDistance(float distance) {
        return easeInOutQuad(1 - distance / LINE_MAX_DISTANCE) * LINE_MAX_WIDTH;
    }
}
