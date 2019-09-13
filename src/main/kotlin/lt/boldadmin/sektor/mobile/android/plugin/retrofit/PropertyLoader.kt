package lt.boldadmin.sektor.mobile.android.plugin.retrofit

import java.util.*

object PropertyLoader {
    internal fun load(path: String) = Properties().apply {
        PropertyLoader::class.java.classLoader.getResourceAsStream(path).use { it ->
            load(it)
        }
    }
}
