package com.bcl.rangescoper;

import java.io.Serializable;

public class Savable implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean active = false;
    private String name = "";

    public Savable() {
    }

    public Savable(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setActiveStatus(boolean z) {
        if (z) {
            this.active = true;
        } else {
            this.active = false;
        }
    }

    public boolean getActiveStatus() {
        return this.active;
    }
}
