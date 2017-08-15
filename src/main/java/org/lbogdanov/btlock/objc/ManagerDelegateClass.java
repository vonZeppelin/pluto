package org.lbogdanov.btlock.objc;

import static org.lbogdanov.btlock.objc.Frameworks.JOBJC;

import com.apple.jobjc.foundation.NSObjectClass;

public class ManagerDelegateClass extends NSObjectClass {
    public ManagerDelegateClass() {
        super(JOBJC);
    }
}
