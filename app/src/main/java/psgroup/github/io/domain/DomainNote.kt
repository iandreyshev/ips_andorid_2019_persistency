package psgroup.github.io.domain

class DomainNote(
    val title: String,
    val description: String,
    val dateInMillis: Long,
    val listener: IListener? = null
) {

    interface IListener {
        fun onOpen()
    }

}
