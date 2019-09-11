package vinkenstein

import geb.Browser
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
@ActiveProfiles("test")
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class GebTest {
    @LocalServerPort
    private int port;

    @Test void assessOnce() {
        Browser.drive {
            go "http://localhost:"+port+"/index.html"
            assert title == 'Vinkenstein'
        }
    }

}
