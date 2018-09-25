package btlock.objc;

import com.apple.jobjc.*
import com.apple.jobjc.corebluetooth.*
import com.apple.jobjc.corefoundation.*
import com.apple.jobjc.foundation.*

object Frameworks {

    val CoreBluetooth:  CoreBluetoothFramework
    val CoreFoundation: CoreFoundationFramework
    val Foundation: FoundationFramework
    val objcNumbers: Utils.Numbers
    val objcRuntime: JObjCRuntime
    val objcStrings: Utils.Strings

    init {
        val jobjc = JObjC.getInstance()
        Foundation = jobjc.Foundation()
        CoreFoundation = jobjc.CoreFoundation()
        CoreBluetooth = jobjc.CoreBluetooth()
        objcRuntime = JObjCRuntime.getInstance()

        val utils = Utils.get()
        objcNumbers = utils.numbers()
        objcStrings = utils.strings()

        objcRuntime.registerUserClass(ManagerDelegate::class.java, ManagerDelegateClass::class.java)
    }

}
