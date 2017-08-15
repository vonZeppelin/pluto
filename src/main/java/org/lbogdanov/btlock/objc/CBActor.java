package org.lbogdanov.btlock.objc;

import static org.lbogdanov.btlock.App.BUS;
import static org.lbogdanov.btlock.objc.Frameworks.CORE_BLUETOOTH;

import org.lbogdanov.btlock.events.StartDiscovery;

import com.apple.jobjc.corebluetooth.CBCentralManager;
import com.squareup.otto.Subscribe;

public class CBActor {
    private final CBCentralManager manager;
    private final ManagerDelegate delegate = new ManagerDelegateClass().newID();

    public CBActor() {
        manager = CORE_BLUETOOTH.CBCentralManager().alloc();

        BUS.register(this);
    }

    @Subscribe public void startDiscovery(StartDiscovery event) {
        manager.initWithDelegate_queue(delegate, null);
    }
}
