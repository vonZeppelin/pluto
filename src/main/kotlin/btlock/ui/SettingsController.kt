package btlock.ui

import btlock.objc.*
import btlock.objc.Frameworks.CORE_BLUETOOTH
import com.apple.jobjc.corebluetooth.*
import mu.*
import tornadofx.*

private val logger = KotlinLogging.logger {}

class SettingsController : Controller() {

    private var cbManager: CBCentralManager? = null

    fun doConnect() = println("Hello")

    fun startDiscovery() {
        val delegate = ManagerDelegateClass().newID<ManagerDelegate>()
        cbManager = CORE_BLUETOOTH.CBCentralManager()
                                  .alloc<CBCentralManager>()
                                  .initWithDelegate_queue(delegate, null)

        logger.info("Discovery of BLE devices started...")
    }

    fun stopDiscovery() {
        cbManager?.release()
    }

}
