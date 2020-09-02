package com.lottomatic.springbootweb.domain;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WebRestTest {

    @Test
    public void 롬북_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        WebRest webRest = new WebRest(name,amount);

        //then
        assertThat(webRest.getName()).isEqualTo(name);
        assertThat(webRest.getAmount()).isEqualTo(amount);
    }
}
