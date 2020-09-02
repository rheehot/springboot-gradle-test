package com.lottomatic.springbootweb.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WebRest {
    private final String name;
    private final int amount;
}
