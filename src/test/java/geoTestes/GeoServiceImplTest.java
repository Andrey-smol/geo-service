package geoTestes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    private static final String LOCALHOST = "127.0.0.1";
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    private final GeoService geoService = new GeoServiceImpl();

    public static Stream<Arguments> testByIp() {
        return Stream.of(
                Arguments.arguments(LOCALHOST, new Location(null, null, null, 0)),
                Arguments.arguments(MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.arguments(NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.arguments("96.", new Location("New York", Country.USA, null, 0)),
                Arguments.arguments("172.", new Location("Moscow", Country.RUSSIA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testByIp(String ip, Location expected) {
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
        Assertions.assertEquals(expected.getCity(), result.getCity());
        Assertions.assertEquals(expected.getStreet(), result.getStreet());
        Assertions.assertEquals(expected.getBuiling(), result.getBuiling());
    }

    @Test
    public void testByIpReturnNull() {
        Location result = geoService.byIp("98.");

        Assertions.assertNull(result);
    }

    @Test
    public void testByCoordinates() {
        Class<RuntimeException> expected = RuntimeException.class;
        Executable executable = () -> geoService.byCoordinates(0, 0);

        Assertions.assertThrowsExactly(expected, executable);
    }
}
