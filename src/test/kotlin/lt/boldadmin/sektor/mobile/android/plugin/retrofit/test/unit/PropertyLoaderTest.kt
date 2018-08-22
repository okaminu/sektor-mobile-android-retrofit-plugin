package lt.boldadmin.sektor.mobile.android.plugin.retrofit.test.unit

import lt.boldadmin.sektor.mobile.android.plugin.retrofit.PropertyLoader
import org.junit.Assert.assertNotNull
import org.junit.Test

class PropertyLoaderTest {

    @Test
    fun load() {
        assertNotNull(PropertyLoader().load("config.properties"))
    }
}