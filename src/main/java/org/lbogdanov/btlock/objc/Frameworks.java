package org.lbogdanov.btlock.objc;


import com.apple.jobjc.JObjC;
import com.apple.jobjc.JObjCRuntime;
import com.apple.jobjc.corebluetooth.CoreBluetoothFramework;
import com.apple.jobjc.corefoundation.CoreFoundationFramework;
import com.apple.jobjc.foundation.FoundationFramework;

public class Frameworks {
    public static final JObjCRuntime JOBJC;
    public static final FoundationFramework FOUNDATION;
    public static final CoreFoundationFramework CORE_FOUNDATION;
    public static final CoreBluetoothFramework CORE_BLUETOOTH;

    static {
        JObjC jobjc = JObjC.getInstance();
        FOUNDATION = jobjc.Foundation();
        CORE_FOUNDATION = jobjc.CoreFoundation();
        CORE_BLUETOOTH = jobjc.CoreBluetooth();
        JOBJC = JObjCRuntime.getInstance();

        JOBJC.registerUserClass(ManagerDelegate.class, ManagerDelegateClass.class);
    }
}
