package components;

import java.util.Arrays;

import static gui.AnimationPanel.NODE_SIZE;
import static gui.AnimationPanel.PADDING;
import static gui.AnimationPanel.WINDOW_HEIGHT;
import static gui.AnimationPanel.WINDOW_WIDTH;
import static utils.Calculations.calculateInverseLineEquation;
import static utils.Calculations.calculateLineEquation;
import static utils.Calculations.generateRandomLineEquationThroughPoint;
import static utils.RandomGeneration.randomFloat;


public class Node {

    private float xPos;
    private float yPos;

    private final float velocity;
    private final float[] lineEquation;

    public Node() {
        xPos = randomFloat(0f, WINDOW_WIDTH);
        yPos = randomFloat(0f, WINDOW_HEIGHT);
        velocity = randomFloat(-2f, 2f);
        lineEquation = generateRandomLineEquationThroughPoint(xPos, yPos);
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public float getVelocity() {
        return velocity;
    }

    public float[] getLineEquation() {
        return lineEquation;
    }

    public void move() {
        xPos = xPos + velocity;
        yPos = calculateLineEquation(xPos, lineEquation);
        if (checkXOutOfBounds()) {
            yPos = calculateLineEquation(xPos, lineEquation);
        } else if (checkYOutOfBounds()) {
            xPos = calculateInverseLineEquation(yPos, lineEquation);
        }
    }

    private boolean checkXOutOfBounds() {
        if (xPos < -NODE_SIZE -PADDING) {
            xPos = WINDOW_WIDTH + PADDING;
            return true;
        } else if (xPos > WINDOW_WIDTH + NODE_SIZE + PADDING) {
            xPos = -PADDING;
            return true;
        }
        return false;
    }

    private boolean checkYOutOfBounds() {
        if (yPos < -NODE_SIZE -PADDING) {
            yPos = WINDOW_HEIGHT + PADDING;
            return true;
        } else if (yPos > WINDOW_HEIGHT + NODE_SIZE + PADDING) {
            yPos = -PADDING;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Node{xPos=" + xPos + ", yPos=" + yPos + ", velocity=" + velocity +
                ", lineEquation=" + Arrays.toString(lineEquation) + "}";
    }
}
