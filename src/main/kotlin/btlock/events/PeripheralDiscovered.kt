package btlock.events

import tornadofx.*

import java.util.*

data class PeripheralDiscovered(val id: UUID, val name: String?) : FXEvent() {

    constructor(id: String, name : String?) : this(UUID.fromString(id), name)

}
