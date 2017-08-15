package org.lbogdanov.btlock.events;

import com.apple.jobjc.corebluetooth.CBPeripheral;

public class PeripheralDiscovered {
    public final CBPeripheral peripheral;

    public PeripheralDiscovered(CBPeripheral peripheral) {
        this.peripheral = peripheral;
    }
}
