from PyQt5.QtCore import Qt, QUrl
from PyQt5.QtQuick import QQuickView


class AppWindow(QQuickView):

    def __init__(self):
        super().__init__(QUrl('resources/app_window.qml')) # QUrl('qrc:/app_window.qml')

        self.setFlags(Qt.FramelessWindowHint | Qt.WindowStaysOnTopHint)

    def show(self, relative_to=None):
        if relative_to:
            pass

        super().show()
