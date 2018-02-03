package btlock

import btlock.ui.*
import org.slf4j.*
import tornadofx.*

import java.awt.*
import javafx.application.*

/**
 * 
 */
class BTLock : App(SettingsView::class) {
    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        trayicon(icon = resources.stream("/lock.png"), tooltip = "BT Lock") {
            menu("") {
                add(CheckboxMenuItem("Enable", true)).setOnAction {}
                item("Settings...") {
                    setOnAction {}
                }
                addSeparator()
                item("Exit") {
                    setOnAction(fxThread = true) {
                        Platform.exit()
                    }
                }
            }
        }
    }

    override fun onBeforeShow(view: UIComponent) {
        logger.info("Application started...")
    }
}

fun main(args: Array<String>) {
    launch<BTLock>(args)
}
