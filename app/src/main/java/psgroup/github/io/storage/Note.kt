package psgroup.github.io.storage

data class Note(
        val id: Long,
        val title: String,
        val description: String,
        val creationDateInMillis: Long
)
