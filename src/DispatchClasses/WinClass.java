package DispatchClasses;

import java.io.Serializable;

public class WinClass implements Serializable {
    String condition;
    public WinClass(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }
}
