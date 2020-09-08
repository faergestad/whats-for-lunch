package com.itera.faergestad.whatsforlunch.util;

import com.google.common.collect.Lists;
import com.itera.faergestad.whatsforlunch.api.domain.Dish;
import com.itera.faergestad.whatsforlunch.api.domain.Lunch;
import com.itera.faergestad.whatsforlunch.api.domain.slack.Block;
import com.itera.faergestad.whatsforlunch.api.domain.slack.HeaderText;
import com.itera.faergestad.whatsforlunch.api.domain.slack.SlackResponse;
import com.itera.faergestad.whatsforlunch.api.domain.slack.Text;

import java.util.List;

public class SlackMapper {

    private static final String HEADER = "header";
    private static final String SECTION = "section";
    private static final String MRKDWN = "mrkdwn";
    private static final String PLAIN_TXT = "plain_text";
    private static final String DIVIDER = "divider";

    public static SlackResponse convertToSlackResponse(Lunch lunch) {
        List<Block> blocks = Lists.newArrayList();
        blocks.add(createGreeting());
        blocks.add(createDivider());
        blocks.add(createBlockFromDishes(lunch.getDishes()));

        SlackResponse response = new SlackResponse();
        response.setBlocks(blocks);
        return response;
    }

    private static Block createGreeting() {
        HeaderText greetingTxt = new HeaderText();
        greetingTxt.setType(PLAIN_TXT);
        greetingTxt.setText("Her er dagens lunsj :point_down::skin-tone-3:");

        Block greeting = new Block();
        greeting.setType(HEADER);
        greeting.setText(greetingTxt);
        return greeting;
    }

    private static Block createDivider() {
        Block divider = new Block();
        divider.setType(DIVIDER);
        return divider;
    }

    private static Block createBlockFromDishes(List<Dish> dishes) {
        StringBuilder dishTxt = new StringBuilder();
        for (Dish dish : dishes)
            dishTxt.append("• ").append(dish.getName()).append(" \n");

        Text dishesTxt = new Text();
        dishesTxt.setType(MRKDWN);
        dishesTxt.setText(dishTxt.toString());

        Block doneDishes = new Block();
        doneDishes.setType(SECTION);
        doneDishes.setText(dishesTxt);
        return doneDishes;
    }

}
