package lt.tlistas.loginn.mobile.android.plugin

import org.junit.Assert.assertNotNull
import org.junit.Test

class PropertyLoaderTest {

    @Test
    fun load() {
        assertNotNull(PropertyLoader().load("config.properties"))
    }
}