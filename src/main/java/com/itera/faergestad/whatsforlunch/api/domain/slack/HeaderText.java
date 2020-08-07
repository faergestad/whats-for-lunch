package com.itera.faergestad.whatsforlunch.api.domain.slack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeaderText extends Text {

    private boolean emoji = true;

}
