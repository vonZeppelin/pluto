import os
import resources
import sys

from PyQt5.QtWidgets import QApplication

from app_window import AppWindow
from constants import APP_NAME
from tray import init_tray_icon


def main():
    QApplication.setQuitOnLastWindowClosed(False)

    app = QApplication(sys.argv)
    app.setApplicationName(APP_NAME)
    app.setApplicationDisplayName(APP_NAME)

    app_window = AppWindow()
    tray_icon = init_tray_icon(app_window)
    tray_icon.show()

    return app.exec_()


if __name__ == '__main__':
    # workaround for missing PyQT5.QQuickStyle class
    os.environ['QT_QUICK_CONTROLS_STYLE'] = 'material'
    sys.exit(main())
