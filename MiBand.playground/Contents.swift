//: Playground - noun: a place where people can play

import CoreBluetooth
import PlaygroundSupport

PlaygroundPage.current.needsIndefiniteExecution = true

class Delegate : NSObject, CBCentralManagerDelegate, CBPeripheralDelegate {
    var connectingPeripherial : CBPeripheral!

    func centralManagerDidUpdateState(_ central: CBCentralManager) {
        if (central.state == .poweredOn) {
            central.scanForPeripherals(withServices: nil, options: nil)
        }
    }

    func centralManager(_ central: CBCentralManager, didDiscover peripheral: CBPeripheral, advertisementData: [String : Any], rssi RSSI: NSNumber) {
        if let name = peripheral.name {
            if (name.contains("Band")) {
                connectingPeripherial = peripheral
                central.connect(connectingPeripherial, options: nil)
                central.stopScan()
            }
        }
    }

    func centralManager(_ central: CBCentralManager, didConnect peripheral: CBPeripheral) {
        connectingPeripherial.delegate = self;
        peripheral.discoverServices(nil)
    }

    func peripheral(_ peripheral: CBPeripheral, didDiscoverServices error: Error?) {
        for s in peripheral.services! {
            print(s.characteristics!)
        }
    }
    
    func centralManager(_ central: CBCentralManager, didDisconnectPeripheral peripheral: CBPeripheral, error: Error?) {
        connectingPeripherial = nil
        print("Disconnected")
    }

    func peripheralDidUpdateRSSI(_ peripheral: CBPeripheral, error: Error?) {
        print(peripheral.rssi!)
    }

    func requester(timer: Timer) {
        self.connectingPeripherial.readRSSI()
    }
}

let delegate = Delegate()
let manager = CBCentralManager(delegate: delegate, queue: nil)
