package btlock

import btlock.ui.*
import mu.*
import tornadofx.*

import javafx.application.*
import javafx.event.*
import javafx.scene.control.*
import javafx.stage.*
import java.util.concurrent.atomic.*

private val logger = KotlinLogging.logger {}

class BTLock : App(SettingsView::class) {

    override fun start(stage: Stage) {
        super.start(stage)

        trayicon(resources.stream("lock.png"), tooltip = "BT Lock") {
            menu("") {
                checkboxItem("Enable", state = true) {
                    setOnItem {
                        println(it)
                    }
                }
                item("Settings...") {
                    val isDialogShown = AtomicBoolean(false)
                    setOnAction(fxThread = true) {
                        if (isDialogShown.compareAndSet(false, true)) {
                            Alert(Alert.AlertType.NONE, "", ButtonType.CLOSE)
                                .apply {
                                    val view = find(SettingsView::class)
                                    dialogPane.content = view.root
                                    onShown = EventHandler {
                                        view.startDiscovery()
                                    }
                                    onHidden = EventHandler {
                                        view.stopDiscovery()
                                        isDialogShown.set(false)
                                    }
                                }
                                .show()
                        }
                    }
                }
                addSeparator()
                item("Exit") {
                    setOnAction {
                        Platform.exit()
                    }
                }
            }
        }
    }

    override fun onBeforeShow(view: UIComponent) = logger.info("Application started...")

    override fun shouldShowPrimaryStage() = false

}

fun main(args: Array<String>) = launch<BTLock>(args)
