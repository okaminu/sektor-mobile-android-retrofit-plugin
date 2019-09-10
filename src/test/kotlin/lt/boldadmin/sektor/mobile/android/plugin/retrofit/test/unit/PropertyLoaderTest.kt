package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit

import lt.boldadmin.sektor.mobile.android.plugin.retrofit.PropertyLoader
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class PropertyLoaderTest {

    @Test
    fun load() {
        assertNotNull(PropertyLoader.load("config.properties"))
    }
}
