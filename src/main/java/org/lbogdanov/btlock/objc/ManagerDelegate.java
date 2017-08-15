package org.lbogdanov.btlock.objc;

import static org.lbogdanov.btlock.App.BUS;
import static org.lbogdanov.btlock.objc.Frameworks.CORE_BLUETOOTH;

import org.lbogdanov.btlock.events.PeripheralDiscovered;

import com.apple.jobjc.JObjCRuntime;
import com.apple.jobjc.corebluetooth.CBCentralManager;
import com.apple.jobjc.corebluetooth.CBPeripheral;
import com.apple.jobjc.foundation.NSDictionary;
import com.apple.jobjc.foundation.NSNumber;
import com.apple.jobjc.foundation.NSObject;

public class ManagerDelegate extends NSObject {
    public ManagerDelegate(long objPtr, JObjCRuntime runtime) {
        super(objPtr, runtime);
    }

    public void centralManagerDidUpdateState(CBCentralManager manager) {
        if (manager.state() == CORE_BLUETOOTH.CBCentralManagerStatePoweredOn()) {
            manager.scanForPeripheralsWithServices_options(null, null);
        }
    }

    public void centralManager_didDiscoverPeripheral_advertisementData_RSSI(CBCentralManager manager,
                                                                            CBPeripheral peripheral,
                                                                            NSDictionary data,
                                                                            NSNumber rssi) {
        BUS.post(new PeripheralDiscovered(peripheral));
    }
}
