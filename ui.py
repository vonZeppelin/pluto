import resources # noqa
import sys

from PySide6.QtGui import QIcon
from PySide6.QtWidgets import QApplication, QMenu, QMessageBox, QSystemTrayIcon

from config import APP_NAME

from __feature__ import snake_case # noqa


class TrayManager(object):

    def __init__(self, app):
        self.on_icon = QIcon(":/images/app_on.png")
        self.off_icon = QIcon(":/images/app_off.png")
        self.on_icon.set_is_mask(True)
        self.off_icon.set_is_mask(True)

        self.tray_icon = QSystemTrayIcon(self.off_icon, app)
        self.tray_icon.set_context_menu(self._build_menu(app))
        self.tray_icon.set_tool_tip(APP_NAME)

    def show_icon(self):
        self.tray_icon.show()

    def _build_menu(self, app):
        main_menu = QMenu(APP_NAME)

        enable_action = main_menu.add_action("Enable")
        enable_action.set_checkable(True)
        enable_action.set_checked(False)
        enable_action.triggered.connect(self._on_off_handler)

        main_menu.add_separator()

        main_menu.add_action("Preferences...")
        main_menu.add_action("About...")

        main_menu.add_separator()

        quit_action = main_menu.add_action("Quit")
        quit_action.triggered.connect(app.quit)

        return main_menu

    def _on_off_handler(self, checked):
        self.tray_icon.set_icon(
            self.on_icon if checked else self.off_icon
        )


def init_ui():
    app = QApplication(sys.argv)
    app.set_application_name(APP_NAME)
    app.set_application_display_name(APP_NAME)

    if not QSystemTrayIcon.is_system_tray_available():
        QMessageBox.critical(
            None, APP_NAME, "System tray is not supported, app will exit"
        )
        return 1

    tray_manager = TrayManager(app)
    tray_manager.show_icon()

    return app.exec()
