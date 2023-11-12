package christmas.ui.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ParseOrderTest {

    //- enum에서 name이 일치할 때 그냥 enum을 반환하는 것은 괜찮은 걸까?
    @Test
    @DisplayName("구분된 문자 확인")
    void splitter() {
        // given
        ParseOrder parseOrder = new ParseOrder();

        // when
        List<String[]> splitter = parseOrder.parseOrder("해산물파스타-2,레드와인-1,초코케이크-1");

        // then
        List<String[]> stringArray = Arrays.asList(
                new String[]{"해산물파스타", "2"},
                new String[]{"레드와인", "1"},
                new String[]{"초코케이크", "1"}
        );
        assertThat(splitter).usingRecursiveComparison().isEqualTo(stringArray);
    }


}
