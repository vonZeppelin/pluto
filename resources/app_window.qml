import QtQuick 2.12
import QtQuick.Controls 2.12
import QtQuick.Controls.Material 2.12
import QtQuick.Layouts 1.12

Page {
    Material.theme: Material.System

    height: 200
    width: 320

    Menu {
        id: mainMenu
        MenuItem {
            text: "TBD"
        }
        MenuSeparator {}
        MenuItem {
            text: "Quit"
            onClicked: Qt.quit()
        }
    }

    header: ToolBar {
        RowLayout {
            anchors.fill: parent
            ToolButton {
                text: qsTr("‹")
                onClicked: stack.pop()
                visible: stack.depth > 1
            }
            Label {
                text: "Pluto"
                horizontalAlignment: Qt.AlignHCenter
                Layout.fillWidth: true
            }
            ToolButton {
                text: qsTr("⋮")
                onClicked: mainMenu.popup()
            }
        }
    }

     StackView {
        id: stack
        anchors.fill: parent

        initialItem: Page {
            Label {
                anchors.fill: parent
                text: "Text"
            }
        }
    }
}
