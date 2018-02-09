package lt.tlistas.loginn.mobile.android.plugin

import org.junit.Test

import org.junit.Assert.*

class PropertyLoaderTest {

    @Test
    fun load() {
        assertNotNull(PropertyLoader().load("config.properties"))
    }
}