package dkgh.websocketmessenger.configurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class WebSocketConfigurationTest {

    @Test
    @DisplayName("WebSocketConfiguration Bean 등록 테스트")
    public void getBean() {
        //given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebSocketConfiguration.class);

        //when
        WebSocketConfiguration bean = context.getBean(WebSocketConfiguration.class);

        //then
        assertThat(bean).isInstanceOf(WebSocketConfiguration.class);

    }

}