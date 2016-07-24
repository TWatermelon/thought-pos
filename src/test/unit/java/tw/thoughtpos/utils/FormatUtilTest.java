package tw.thoughtpos.utils;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FormatUtilTest {
    @Test
    public void should_return_two_effective_given_double_num() {
        assertThat(FormatUtil.format(20.000), is("20.00"));
    }
}
