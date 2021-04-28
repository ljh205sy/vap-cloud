package com.central.generator.utils;

import com.vrv.vap.generator.utils.GenUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * GenUtils测试用例
 *
 * @author wh1107066
 * @date 2019/5/10
 */
@SpringBootTest
public class GenUtilsTest {
    @Test
    public void testTableToJava() {
        String javaName = GenUtils.tableToJava("t_event_message", "t_");
        Assertions.assertThat(javaName).isEqualTo("EventMessage");
    }
}
