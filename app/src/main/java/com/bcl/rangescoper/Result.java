package com.bcl.rangescoper;

public class Result {

    private int elevationClicks;
    private String elevationRotation;
    private int windageClicks;
    private String windageRotation;

    public Result() {
    }

    public Result(int i, int i2, String str, String str2) {
        this.windageClicks = i;
        this.elevationClicks = i2;
        this.windageRotation = str;
        this.elevationRotation = str2;
    }

    public int getWindageClicks() {
        return this.windageClicks;
    }

    public int getElevationClicks() {
        return this.elevationClicks;
    }

    public String getWindageRotationDirection() {
        return this.windageRotation;
    }

    public String getElevationRotationDirection() {
        return this.elevationRotation;
    }
}
