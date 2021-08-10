package test.dev.withings.presentation

open class Event<out T>(private val content: T) {

    override fun equals(other: Any?): Boolean {
        return this.peekContent() == (other as? Event<*>)?.peekContent()
    }

    override fun toString(): String {
        return "Event($content)-handled : $hasBeenHandled"
    }

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

fun <T> T.toEvent(): Event<T> = Event(this)