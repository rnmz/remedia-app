package dev.runo.search.domain.model

data class Title(
    val id: Int,
    val image: ByteArray,
    val name: String,
    val rating: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Title

        if (id != other.id) return false
        if (rating != other.rating) return false
        if (!image.contentEquals(other.image)) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + rating.hashCode()
        result = 31 * result + image.contentHashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}