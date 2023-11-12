package christmas.domain;

import christmas.ui.utilObject.Number;

public class VisitDate {

    private static final String VISIT_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final Integer FIRST_DAY_OF_DECEMBER = 1;
    private static final Integer LAST_DAY_OF_DECEMBER = 30;


    private final Integer visitDay;

    public VisitDate(String inputValue) {
        Integer number = validateNumber(inputValue);
        validateRange(number);
        visitDay = number;
    }

    private Integer validateNumber(String inputValue) {
        Number number = new Number(inputValue);
        return number.getNumericValue();
    }

    private void validateRange(Integer number) {
        if (number < FIRST_DAY_OF_DECEMBER || number > LAST_DAY_OF_DECEMBER) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
        }
    }
}
