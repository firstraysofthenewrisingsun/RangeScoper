package com.bcl.rangescoper;

public class UnitStatus extends Savable{

    private static final long serialVersionUID = 5461954460263577064L;
    boolean isImperial;

    public UnitStatus() {
    }

    public UnitStatus(boolean z) {
        this.isImperial = z;
    }

    public boolean getIsImperial() {
        return this.isImperial;
    }

    public void setUnitsImperial() {
        this.isImperial = true;
    }

    public void setUnitsMetric() {
        this.isImperial = false;
    }
}
