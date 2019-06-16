package btlock.ui

import btlock.events.*
import tornadofx.*

import javafx.scene.control.*
import javafx.scene.layout.BorderPane

class MainView : View() {

    override val root: BorderPane by fxml()
    private val controller: MainController by inject()

    private val connectButton: Button by fxid()
    private val devicesList: ListView<Any> by fxid()

    init {
        connectButton.action(controller::doConnect)
        connectButton.disableWhen {
            devicesList.selectionModel.selectedItemProperty().isNull
        }
        startDiscovery()
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
