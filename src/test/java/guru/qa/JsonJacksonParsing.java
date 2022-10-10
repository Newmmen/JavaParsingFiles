package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.domain.Plane;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonJacksonParsing {
    @Test
    void JsonTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassLoader classLoader = FileParseTest.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("plane.json");
            Plane plane = objectMapper.readValue(new InputStreamReader(is), Plane.class);
            assertThat(plane.getModel()).isEqualTo("Boeing 737-800");
            assertThat(plane.getLegalFlightAreas()).contains(new String[]{"USA", "Europe", "Asia"});
            assertThat(plane.getPassport().getCreatedBy()).isEqualTo("USA");
            assertThat(plane.getPassport().getSerialNumber().toString()).isEqualTo("78656985");
            assertThat(plane.isCertificated()).isEqualTo(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

