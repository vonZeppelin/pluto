import sys

from constants import APP_NAME
from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QMessageBox, QSystemTrayIcon


def tray_icon_activated(reason, app_window, tray_icon):
    if reason in {QSystemTrayIcon.Trigger, QSystemTrayIcon.DoubleClick}:
        app_window.show(tray_icon.geometry())


def init_tray_icon(app_window):
    if not QSystemTrayIcon.isSystemTrayAvailable():
        QMessageBox.critical(
            None, APP_NAME, 'System tray is not supported, app will exit'
        )
        sys.exit(1)

    icon = QIcon(':/images/app.png')
    icon.setIsMask(True)

    tray_icon = QSystemTrayIcon(icon, app_window)
    tray_icon.activated.connect(
        lambda reason: tray_icon_activated(reason, app_window, tray_icon)
    )
    tray_icon.setToolTip(APP_NAME)
    return tray_icon
