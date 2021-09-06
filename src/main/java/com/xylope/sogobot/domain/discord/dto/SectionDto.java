package com.xylope.sogobot.domain.discord.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class SectionDto {
    private final String title;
    private final String content;
    private final boolean inline;
}
