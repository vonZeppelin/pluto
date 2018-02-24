package btlock.objc

import btlock.events.*
import btlock.objc.Frameworks.CORE_BLUETOOTH
import btlock.objc.Frameworks.objcNumbers
import btlock.objc.Frameworks.objcStrings
import com.apple.jobjc.*
import com.apple.jobjc.corebluetooth.*
import com.apple.jobjc.foundation.*
import mu.*
import tornadofx.*

private val logger = KotlinLogging.logger {}

class ManagerDelegateClass : NSObjectClass(Frameworks.JOBJC)

class ManagerDelegate(ptr: Long, rt: JObjCRuntime) : NSObject(ptr, rt) {

    fun centralManagerDidUpdateState(manager: CBCentralManager) {
        if (manager.state() == CORE_BLUETOOTH.CBCentralManagerStatePoweredOn().toLong()) {
            manager.scanForPeripheralsWithServices_options(null, null)
        }
    }

    fun centralManager_didDiscoverPeripheral_advertisementData_RSSI(manager: CBCentralManager,
                                                                    peripheral: CBPeripheral,
                                                                    data: NSDictionary,
                                                                    rssi: NSNumber) {
        val id = objcStrings.javaString(peripheral.identifier().UUIDString())
        logger.info {
            "Peripheral discovered: id=$id, rssi=${objcNumbers.javaNumber(rssi)}"
        }
        FX.eventbus.fire(
            PeripheralDiscovered(id = id, name = objcStrings.javaString(peripheral.name()))
        )
    }

    fun centralManager_didDisconnectPeripheral_error(manager: CBCentralManager,
                                                     peripheral: CBPeripheral,
                                                     error: NSError) {
        logger.info {
            "Peripheral disconnected: id=${objcStrings.javaString(peripheral.identifier().UUIDString())}"
        }
    }

}
