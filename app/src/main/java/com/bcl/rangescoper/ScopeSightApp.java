package com.bcl.rangescoper;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ScopeSightApp extends Application {

    float deviceHeight;
    float deviceWidth;
    String horizontalRotation;

    /* renamed from: r */
    Result f27r;
    ArrayList<Savable> savables = new ArrayList<>();

    /* renamed from: t */
    Target f28t;
    String verticalRotation;

    public void onCreate() {
        super.onCreate();

        this.f28t = new Target("Target One");
        loadSavables();
        if (this.savables.size() != 0) {
            boolean z = false;
            Iterator<Savable> it = this.savables.iterator();
            while (it.hasNext()) {
                if (it.next() instanceof UnitStatus) {
                    z = true;
                }
            }
            if (!z) {
                this.savables.add(new UnitStatus(true));
                return;
            }
            return;
        }
        this.savables.add(new UnitStatus(true));
    }

    public void setDeviceHeight(float f) {
        this.deviceHeight = f;
    }

    public float getDeviceHeight() {
        return this.deviceHeight;
    }

    public void setDeviceWidth(float f) {
        this.deviceWidth = f;
    }

    public float getDeviceWidth() {
        return this.deviceWidth;
    }

    public void setTarget(Target target) {
        this.f28t = target;
    }

    public Target getTarget() {
        return this.f28t;
    }

    public Scope getActiveScope() {
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if ((next instanceof Scope) && next.getActiveStatus()) {
                return (Scope) next;
            }
        }
        return new Scope();
    }

    public void setActiveScope(String str) {
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if (next instanceof Scope) {
                if (next.getName().equals(str)) {
                    next.setActiveStatus(true);
                } else {
                    next.setActiveStatus(false);
                }
            }
        }
    }

    public Range getActiveRange() {
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if ((next instanceof Range) && next.getActiveStatus()) {
                return (Range) next;
            }
        }
        return new Range();
    }

    public void setActiveRange(String str) {
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if (next instanceof Range) {
                if (next.getName().equals(str)) {
                    next.setActiveStatus(true);
                } else {
                    next.setActiveStatus(false);
                }
            }
        }
    }

    public ArrayList<Range> getRanges() {
        ArrayList<Range> arrayList = new ArrayList<>();
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if (next instanceof Range) {
                arrayList.add((Range) next);
            }
        }
        return arrayList;
    }

    public ArrayList<Scope> getScopes() {
        ArrayList<Scope> arrayList = new ArrayList<>();
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if (next instanceof Scope) {
                arrayList.add((Scope) next);
            }
        }
        return arrayList;
    }

    public void setUnitsMetric() {
        convertSavablesToMetric();
    }

    public void calculate() {
        int i;
        int i2;
        Scope activeScope = getActiveScope();
        Range activeRange = getActiveRange();
        ArrayList<Hit> hits = this.f28t.getHits();
        float pixelDiameter = this.f28t.getPixelDiameter() / 2.0f;
        Iterator<Hit> it = hits.iterator();
        float f = 0.0f;
        float f2 = 0.0f;
        while (it.hasNext()) {
            Hit next = it.next();
            f += next.getX();
            f2 += next.getY();
        }
        float size = f / ((float) hits.size());
        float size2 = f2 / ((float) hits.size());
        if (size2 < pixelDiameter && activeScope.getClockwiseOffsetsUp()) {
            this.verticalRotation = "counter-clockwise";
        } else if (size2 > pixelDiameter && activeScope.getClockwiseOffsetsUp()) {
            this.verticalRotation = "clockwise";
        } else if (size2 <= pixelDiameter || activeScope.getClockwiseOffsetsUp()) {
            this.verticalRotation = "clockwise";
        } else {
            this.verticalRotation = "counter-clockwise";
        }
        if (size > pixelDiameter && activeScope.getClockwiseOffsetsLeft()) {
            this.horizontalRotation = "clockwise";
        } else if (size < pixelDiameter && activeScope.getClockwiseOffsetsLeft()) {
            this.horizontalRotation = "counter-clockwise";
        } else if (size >= pixelDiameter || activeScope.getClockwiseOffsetsLeft()) {
            this.horizontalRotation = "counter-clockwise";
        } else {
            this.horizontalRotation = "clockwise";
        }
        float abs = Math.abs((size - pixelDiameter) / this.f28t.getPixelDiameter());
        float abs2 = Math.abs((size2 - pixelDiameter) / this.f28t.getPixelDiameter());
        if (!isAppImperial()) {
            float targetDiameter = abs * activeRange.getTargetDiameter();
            float targetDiameter2 = abs2 * activeRange.getTargetDiameter();
            double offsetPerClick = activeScope.getOffsetPerClick();
            double distanceToTarget = (double) activeRange.getDistanceToTarget();
            double distanceForAdjust = activeScope.getDistanceForAdjust();
            Double.isNaN(distanceToTarget);
            double d = offsetPerClick * (distanceToTarget / distanceForAdjust);
            double d2 = (double) targetDiameter;
            Double.isNaN(d2);
            i2 = (int) (d2 / d);
            double d3 = (double) targetDiameter2;
            Double.isNaN(d3);
            i = (int) (d3 / d);
        } else {
            float targetDiameter3 = abs * activeRange.getTargetDiameter();
            float targetDiameter4 = abs2 * activeRange.getTargetDiameter();
            double offsetPerClick2 = activeScope.getOffsetPerClick();
            double distanceToTarget2 = (double) (activeRange.getDistanceToTarget() / 3.0f);
            double distanceForAdjust2 = activeScope.getDistanceForAdjust();
            Double.isNaN(distanceToTarget2);
            double d4 = offsetPerClick2 * (distanceToTarget2 / distanceForAdjust2);
            double d5 = (double) targetDiameter3;
            Double.isNaN(d5);
            i2 = (int) (d5 / d4);
            double d6 = (double) targetDiameter4;
            Double.isNaN(d6);
            i = (int) (d6 / d4);
        }
        this.f27r = new Result(i2, i, this.horizontalRotation, this.verticalRotation);
    }

    public Result getResult() {
        return this.f27r;
    }

    public void updateSavables(Savable savable) {
        boolean z;
        boolean z2 = savable instanceof Range;
        boolean z3 = true;
        if (z2) {
            Range range = (Range) savable;
            Iterator<Savable> it = this.savables.iterator();
            while (it.hasNext()) {
                Savable next = it.next();
                if (next instanceof Range) {
                    Range range2 = (Range) next;
                    if (range.equals(range2)) {
                        this.savables.remove(next);
                    } else if (range.getName().equals(range2.getName())) {
                        this.savables.remove(next);
                        this.savables.add(savable);
                        setActiveRange(next.getName());
                    }
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (savable instanceof Scope) {
            Scope scope = (Scope) savable;
            Iterator<Savable> it2 = this.savables.iterator();
            while (it2.hasNext()) {
                Savable next2 = it2.next();
                if (next2 instanceof Scope) {
                    Scope scope2 = (Scope) next2;
                    if (scope.equals(scope2)) {
                        this.savables.remove(next2);
                        break;
                    } else if (scope.getName().equals(scope2.getName())) {
                        this.savables.remove(next2);
                        this.savables.add(savable);
                        setActiveScope(next2.getName());
                        break;
                    }
                }
            }
        }
        z3 = z;
        if (!z3) {
            this.savables.add(savable);
            if (z2) {
                setActiveRange(savable.getName());
            } else {
                setActiveScope(savable.getName());
            }
        }
        ensurePopulated();
        ensureActives();
        writeSavables();
        loadSavables();
    }

    public void ensurePopulated() {
        ArrayList<Range> ranges = getRanges();
        if (ranges.size() == 0) {
            this.savables.add(new Range(0.0f, 0.0f, "Add a Range"));
            setActiveRange("Add a Range");
        }
        if (ranges.size() > 1) {
            Range range = new Range(0.0f, 0.0f, "Add a Range");
            Iterator<Savable> it = this.savables.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Savable next = it.next();
                if (next instanceof Range) {
                    Range range2 = (Range) next;
                    if (range2.equals(range)) {
                        this.savables.remove(range2);
                        break;
                    }
                }
            }
        }
        ArrayList<Scope> scopes = getScopes();
        if (scopes.size() == 0) {
            this.savables.add(new Scope("Add a Scope", 0.0d, 0.0d, true, true));
        }
        if (scopes.size() > 1) {
            Scope scope = new Scope("Add a Scope", 0.0d, 0.0d, true, true);
            Iterator<Savable> it2 = this.savables.iterator();
            while (it2.hasNext()) {
                Savable next2 = it2.next();
                if (next2 instanceof Scope) {
                    Scope scope2 = (Scope) next2;
                    if (scope2.equals(scope)) {
                        this.savables.remove(scope2);
                        return;
                    }
                }
            }
        }
    }

    private void ensureActives() {
        Iterator<Savable> it = this.savables.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            Savable next = it.next();
            if (next.getActiveStatus()) {
                if (next instanceof Scope) {
                    z = true;
                } else {
                    z2 = true;
                }
            }
        }
        if (!z) {
            Iterator<Savable> it2 = this.savables.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Savable next2 = it2.next();
                if (next2 instanceof Scope) {
                    next2.setActiveStatus(true);
                    break;
                }
            }
        }
        if (!z2) {
            Iterator<Savable> it3 = this.savables.iterator();
            while (it3.hasNext()) {
                Savable next3 = it3.next();
                if (next3 instanceof Range) {
                    next3.setActiveStatus(true);
                    return;
                }
            }
        }
    }

    private void writeSavables() {
        try {
            new File(Environment.getExternalStorageDirectory(), "savables.ser").delete();
            FileOutputStream openFileOutput = getApplicationContext().openFileOutput("savables.ser", 0);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput);
            objectOutputStream.writeObject(this.savables);
            objectOutputStream.flush();
            objectOutputStream.close();
            openFileOutput.close();
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                e.printStackTrace();
            }
        }
    }

    private void loadSavables() {
        this.savables.clear();
        try {
            FileInputStream openFileInput = openFileInput("savables.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput);
            this.savables = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            openFileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public void convertSavablesToMetric() {
        Iterator<Savable> it = this.savables.iterator();
        while (it.hasNext()) {
            Savable next = it.next();
            if (next instanceof Range) {
                Range range = (Range) next;
                range.setDistanceToTarget(range.getDistanceToTarget() * 0.3048f);
                range.setTargetDiameter(range.getTargetDiameter() * 2.54f);
            } else if (next instanceof UnitStatus) {
                ((UnitStatus) next).setUnitsMetric();
            } else {
                Scope scope = (Scope) next;
                scope.setOffsetPerClick(scope.getOffsetPerClick() * 2.54d);
                scope.setDistanceForAdjust(scope.getDistanceForAdjust() * 0.9144d);
            }
        }
        writeSavables();
        loadSavables();
    }
}
