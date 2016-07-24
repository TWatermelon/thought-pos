package tw.thoughtpos;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PosApplication.class)
@WebAppConfiguration
public abstract class BaseIntegrationTest {
    protected MockMvc mockMvc;
}
