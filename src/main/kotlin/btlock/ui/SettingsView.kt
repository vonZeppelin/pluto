package btlock.ui

import btlock.events.*
import org.tbee.javafx.scene.layout.*
import tornadofx.*

import javafx.scene.control.*

class SettingsView : View() {

    override val root: MigPane by fxml()
    private val controller: SettingsController by inject()

    private val connectButton: Button by fxid()
    private val devicesList: ListView<Any> by fxid()

    init {
        connectButton.action(controller::doConnect)
        connectButton.disableWhen {
            devicesList.selectionModel.selectedItemProperty().isNull
        }
    }

    fun startDiscovery() {
        subscribe<PeripheralDiscovered> {
            devicesList.items.add(it)
        }
        controller.startDiscovery()
    }

    fun stopDiscovery() {
        controller.stopDiscovery()
        // TODO unsubscribe<PeripheralDiscovered>
    }

}
