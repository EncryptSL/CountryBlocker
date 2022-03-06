
import com.maxmind.geoip2.DatabaseReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.net.InetAddress


class CountryDetectionTest {

    private fun getDatabase(): DatabaseReader {
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource("GeoLite2-Country.mmdb").file)
        return DatabaseReader.Builder(file).build()
    }

    @Test
    @DisplayName("CountryTest")
    fun test() {
        Assertions.assertEquals("RU", getDatabase().country(InetAddress.getByName("194.135.230.86")).country.isoCode)
    }



}