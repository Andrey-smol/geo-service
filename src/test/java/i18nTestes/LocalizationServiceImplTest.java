package i18nTestes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;


public class LocalizationServiceImplTest {
    private final LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"RUSSIA"})
    public void testLocaleRussia(Country country) {
        String expected = "Добро пожаловать";
        String result = localizationService.locale(country);

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"GERMANY", "USA", "BRAZIL"})
    public void testLocale(Country country) {
        String expected = "Welcome";
        String result = localizationService.locale(country);

        Assertions.assertEquals(expected, result);
    }
}
