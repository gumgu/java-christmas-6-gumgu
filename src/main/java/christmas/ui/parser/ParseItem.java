package christmas.ui.parser;

import christmas.domain.Dish;
import christmas.ui.utilObject.Number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseItem {

    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_HYPHEN = "-";

    //todo: 함수 쪼개기
    public static List<String[]> parseItem(String inputValue) {
        List<String[]> eachItems = new ArrayList<>();

        String[] items = inputValue.split(DELIMITER_COMMA);

        for (String parts : items) {
            String[] split = parts.split(DELIMITER_HYPHEN);
            applyTrim(split);

            Dish.validateDishName(split[0]); // 요리 검증
            new Number(split[1]); // 숫자 검증

            eachItems.add(split);
        }

        return eachItems;
    }

    private static String[] applyTrim(String[] split) {
        return Arrays.stream(split)
                .map(String::trim)
                .toArray(String[]::new);
    }

}
