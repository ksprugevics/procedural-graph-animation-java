package components;

import java.util.Arrays;

import static gui.panels.AnimationPanel.NODE_SIZE;
import static gui.panels.AnimationPanel.PADDING;
import static gui.panels.AnimationPanel.WINDOW_HEIGHT;
import static gui.panels.AnimationPanel.WINDOW_WIDTH;
import static utils.Calculations.calculateInverseLineEquation;
import static utils.Calculations.calculateLineEquation;
import static utils.Calculations.generateRandomLineEquationThroughPoint;
import static utils.RandomGeneration.randomFloat;


public class Node {

    private float xPos;
    private float yPos;

    private float velocity;
    private float[] lineEquation;

    private boolean selected;

    public Node() {
        xPos = randomFloat(0f, WINDOW_WIDTH);
        yPos = randomFloat(0f, WINDOW_HEIGHT);
        velocity = randomFloat(-2f, 2f);
        lineEquation = generateRandomLineEquationThroughPoint(xPos, yPos);
        selected = false;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float[] getLineEquation() {
        return lineEquation;
    }

    public void setLineEquation(float[] lineEquation) {
        this.lineEquation = lineEquation;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
