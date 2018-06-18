package lt.boldadmin.sektor.mobile.android.plugin

import lt.boldadmin.sektor.mobile.android.plugin.PropertyLoader
import org.junit.Assert.assertNotNull
import org.junit.Test

class PropertyLoaderTest {

    @Test
    fun load() {
        assertNotNull(PropertyLoader().load("config.properties"))
    }
}