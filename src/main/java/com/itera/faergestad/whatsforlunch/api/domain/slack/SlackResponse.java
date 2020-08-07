package com.itera.faergestad.whatsforlunch.api.domain.slack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlackResponse {

    List<Block> blocks;

}