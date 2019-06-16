package btlock

import btlock.ui.*
import mu.*
import tornadofx.*

import java.awt.Toolkit
import java.awt.event.*
import javafx.application.Platform
import javafx.stage.*

private val logger = KotlinLogging.logger {}

class BTLock : App(MainView::class) {

    override fun start(stage: Stage) {
        super.start(stage)

        stage.initStyle(StageStyle.UNDECORATED)
        stage.isAlwaysOnTop = true

        trayicon(resources.stream("lock.png"), tooltip = "BT Lock") {
            addMouseListener(object: MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    Platform.runLater {
                        if (stage.isShowing) {
                            stage.hide()
                        } else {
                            stage.sizeToScene()
                            stage.show()
                        }
                    }
                }
            })
        }
    }

    override fun onBeforeShow(view: UIComponent) = logger.info("Application started...")

    override fun shouldShowPrimaryStage() = false
}

fun main(args: Array<String>) {
    // https://bugs.openjdk.java.net/browse/JDK-8092032
    // https://bugs.openjdk.java.net/browse/JDK-8093206
    // must start as an AWT app first to be able to hide Dock icon
    Toolkit.getDefaultToolkit()

    launch<BTLock>(args)
}
