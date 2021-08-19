from PySide6.QtGui import QIcon
from PySide6.QtWidgets import QMenu, QMessageBox, QSystemTrayIcon

from config import APP_NAME


class TrayManager(object):

    def __init__(self, app):
        icon = QIcon(":/images/app.png")
        icon.setIsMask(True)
        self._tray_icon = QSystemTrayIcon(icon, app)
        self._tray_icon.setToolTip(APP_NAME)

        main_menu = QMenu(APP_NAME)
        quit_action = main_menu.addAction("Quit")
        quit_action.triggered.connect(app.quit)
        self._tray_icon.setContextMenu(main_menu)
        self._tray_icon.show()

    @staticmethod
    def init(app):
        if not QSystemTrayIcon.isSystemTrayAvailable():
            QMessageBox.critical(
                None, APP_NAME, "System tray is not supported, app will exit"
            )
            return None
        if not True:
            QMessageBox.critical(
                None, APP_NAME, "BLE is not supported, app will exit"
            )
            return None
        return TrayManager(app)
