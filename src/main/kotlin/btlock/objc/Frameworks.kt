package btlock.objc;

import com.apple.jobjc.*
import com.apple.jobjc.corebluetooth.*
import com.apple.jobjc.corefoundation.*
import com.apple.jobjc.foundation.*

object Frameworks {

    val JOBJC: JObjCRuntime
    val FOUNDATION: FoundationFramework
    val CORE_FOUNDATION: CoreFoundationFramework
    val CORE_BLUETOOTH:  CoreBluetoothFramework

    val objcNumbers: Utils.Numbers
    val objcStrings: Utils.Strings

    init {
        val jobjc = JObjC.getInstance()
        FOUNDATION = jobjc.Foundation()
        CORE_FOUNDATION = jobjc.CoreFoundation()
        CORE_BLUETOOTH = jobjc.CoreBluetooth()
        JOBJC = JObjCRuntime.getInstance()

        val utils = Utils.get()
        objcNumbers = utils.numbers()
        objcStrings = utils.strings()

        JOBJC.registerUserClass(ManagerDelegate::class.java, ManagerDelegateClass::class.java)
    }

}
