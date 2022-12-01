package com.bcl.rangescoper;

public class Scope extends Savable{

    private static final long serialVersionUID = 1;
    private boolean clockwiseOffsetsLeft;
    private boolean clockwiseOffsetsUp;
    private double distanceForAdjust;
    private double offsetPerClick;

    public Scope() {
        this.offsetPerClick = 0.0d;
        this.distanceForAdjust = 0.0d;
        this.clockwiseOffsetsLeft = false;
        this.clockwiseOffsetsUp = false;
    }

    public Scope(String str, double d, double d2, boolean z, boolean z2) {
        super(str);
        this.offsetPerClick = d;
        this.distanceForAdjust = d2;
        this.clockwiseOffsetsLeft = z;
        this.clockwiseOffsetsUp = z2;
    }

    public void setOffsetPerClick(double d) {
        this.offsetPerClick = d;
    }

    public double getOffsetPerClick() {
        return this.offsetPerClick;
    }

    public void setDistanceForAdjust(double d) {
        this.distanceForAdjust = d;
    }

    public double getDistanceForAdjust() {
        return this.distanceForAdjust;
    }

    public boolean getClockwiseOffsetsLeft() {
        return this.clockwiseOffsetsLeft;
    }

    public boolean getClockwiseOffsetsUp() {
        return this.clockwiseOffsetsUp;
    }

    public boolean equals(Scope scope) {
        return scope.getName().equals(getName()) && scope.offsetPerClick == this.offsetPerClick && scope.distanceForAdjust == this.distanceForAdjust && scope.clockwiseOffsetsLeft == this.clockwiseOffsetsLeft && scope.clockwiseOffsetsUp == this.clockwiseOffsetsUp;
    }
}
