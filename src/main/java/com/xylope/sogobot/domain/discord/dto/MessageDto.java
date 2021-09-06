package com.xylope.sogobot.domain.discord.dto;

import com.xylope.sogobot.domain.discord.enum_type.MessageLevel;
import com.xylope.sogobot.domain.discord.exception.WrongMessageFormatException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class MessageDto {
    @Getter
    private  String title;
    private final List<SectionDto> sections;
    @Getter
    private final MessageLevel level;
    @Getter
    private final String author;
    @Getter
    private final String footer;

    @Builder
    public MessageDto(String title, MessageLevel level, String author, String footer, SectionDto... sections) {
        this.sections = Arrays.asList(sections.clone());
        this.author = author;
        this.title = title;
        this.level = level;
        this.footer = footer;
    }

    public MessageDto(String message) {
        this.title = message;
        this.sections = null;
        this.author = "";
        this.level = MessageLevel.INFO;
        this.footer = "";
    }

    public void addSections(SectionDto dto) {
        sections.add(dto);
    }

    public void addSections(SectionDto... dto) {
        sections.addAll(Arrays.stream(dto).collect(Collectors.toList()));
    }

    public String getRawMessage() {
        if(this.equals(new MessageDto(title)))
            return title;
        throw new WrongMessageFormatException("message isn't raw message!");
    }

    public MessageEmbed getEmbedMessage() {
        if(!this.equals(new MessageDto(title))) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(title);
            sections.forEach(section -> builder.addField(section.getTitle(), section.getContent(), section.isInline()));
            builder.setAuthor(author);
            builder.setFooter(footer);
            builder.setColor(new Color(41, 112, 234));
        } throw new WrongMessageFormatException("message is raw message!");
    }
}
