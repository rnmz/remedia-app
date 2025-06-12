package dev.runo.core.network.title

enum class TitleType(val type: Int) {
    ALL(0),
    MANGA(1024),
    MANHWA(2048),
    MANHUA(3072),
    COMICS(4096)
}

@Suppress("KotlinConstantConditions")
fun Int.toTitleType(): TitleType = TitleType.entries.find { it.type == this } ?: TitleType.ALL