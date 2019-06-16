import QtGraphicalEffects 1.12
import QtQuick 2.12
import QtQuick.Controls 2.12
import QtQuick.Controls.Material 2.12

Pane {
    Material.theme: Material.System
    width: 640
    height: 480

    Column {
        anchors.fill: parent
        anchors.top: parent.top
        anchors.centerIn: parent

        RadioButton { text: qsTr("Small") }
        RadioButton { text: qsTr("Medium") }
        RadioButton { text: qsTr("Large") }
    }
}
