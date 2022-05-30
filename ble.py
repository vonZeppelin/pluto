from PySide6.QtBluetooth import *
from PySide6.QtCore import QByteArray

from config import APP_NAME

from __feature__ import snake_case


class TrayManager(object):

    @staticmethod
    def init(app):
        data = QLowEnergyAdvertisingData()
        data.set_discoverability(QLowEnergyAdvertisingData.DiscoverabilityGeneral)
        data.set_include_power_level(True)
        data.set_local_name(APP_NAME)
        data.set_services([QBluetoothUuid.ServiceClassUuid.HeartRate])

        charData = QLowEnergyCharacteristicData()
        charData.set_uuid(QBluetoothUuid.CharacteristicType.HeartRateMeasurement)
        charData.set_value(QByteArray(2, 0))
        charData.set_properties(QLowEnergyCharacteristic.Notify)
        clientConfig = QLowEnergyDescriptorData(QBluetoothUuid.DescriptorType.ClientCharacteristicConfiguration, QByteArray(2, 0))
        charData.add_descriptor(clientConfig)

        serviceData = QLowEnergyServiceData()
        serviceData.set_type(QLowEnergyServiceData.ServiceTypePrimary)
        serviceData.set_uuid(QBluetoothUuid.ServiceClassUuid.HeartRate)
        serviceData.add_characteristic(charData)

        ble = QLowEnergyController.create_peripheral(app)
        ble.add_service(serviceData)
        ble.start_advertising(QLowEnergyAdvertisingParameters(), data, data)
