package com.bcl.rangescoper;

public class Range extends Savable{

    private static final long serialVersionUID = -5369995678678522030L;
    private float distanceToTarget;
    private float targetDiameter;

    public Range() {
        this.distanceToTarget = 0.0f;
        this.targetDiameter = 0.0f;
    }

    public Range(float f, float f2, String str) {
        super(str);
        this.distanceToTarget = f;
        this.targetDiameter = f2;
    }

    public float getDistanceToTarget() {
        return this.distanceToTarget;
    }

    public void setDistanceToTarget(float f) {
        this.distanceToTarget = f;
    }

    public float getTargetDiameter() {
        return this.targetDiameter;
    }

    public void setTargetDiameter(float f) {
        this.targetDiameter = f;
    }

    public boolean equals(Range range) {
        return range.getName().equals(getName()) && range.getDistanceToTarget() == this.distanceToTarget && range.getTargetDiameter() == this.targetDiameter;
    }
}
