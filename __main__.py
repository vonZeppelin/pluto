import resources # noqa
import sys

from PySide6.QtQuickControls2 import QQuickStyle
from PySide6.QtWidgets import QApplication

from config import APP_NAME
from tray import TrayManager


def main():
    QApplication.setQuitOnLastWindowClosed(False)

    app = QApplication(sys.argv)
    app.setApplicationName(APP_NAME)
    app.setApplicationDisplayName(APP_NAME)

    tray_manager = TrayManager.init(app)
    if tray_manager:
        return app.exec()
    else:
        return 1


if __name__ == "__main__":
    # os.environ["QT_QUICK_CONTROLS_MATERIAL_VARIANT"] = "Dense"
    QQuickStyle.setStyle("Material")
    sys.exit(main())
