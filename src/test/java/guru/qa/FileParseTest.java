package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {
    String zipFile = "archive.zip";
    String pdfFile = "archive/sample.pdf";
    String csvFile = "archive/addresse.csv";
    String xlsFile = "archive/file_example_XLS_10.xls";

    ClassLoader classLoader = FileParseTest.class.getClassLoader();
    InputStream is = classLoader.getResourceAsStream(zipFile);
    ZipInputStream zis = new ZipInputStream(is);
    ZipEntry entry;


    @Test
    void checkPDFContentFromZip() throws Exception {
        ZipFile zfile = new ZipFile(new File("src/test/resources/" + zipFile));
        while (!(entry = zis.getNextEntry()).getName().equals(pdfFile)) {
        }
        try (InputStream stream = zfile.getInputStream(entry)) {
            PDF pdf = new PDF(stream);
            assertThat(pdf.creator).isEqualTo("Rave (http://www.nevrona.com/rave)");
        }
    }

    @Test
    void checkCSVContentFromZip() throws Exception {
        ZipFile zfile = new ZipFile(new File("src/test/resources/" + zipFile));
        while (!(entry = zis.getNextEntry()).getName().equals(csvFile)) {
        }
        try (InputStream stream = zfile.getInputStream(entry)) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(stream, UTF_8));
            List<String[]> csv = csvReader.readAll();
            assertThat(csv).contains(
                    new String[]{"John", "Doe", "120 jefferson st.", "Riverside", " NJ", " 08075"},
                    new String[]{"Jack", "Soe", "1260 defferssn st.", "Riverside", " NJ", " 98875"}
            );
        }
    }

    @Test
    void checkXLSContentFromZip() throws Exception {
        ZipFile zfile = new ZipFile(new File("src/test/resources/" + zipFile));
        while (!(entry = zis.getNextEntry()).getName().equals(xlsFile)) {
        }
        try (InputStream stream = zfile.getInputStream(entry)) {
            XLS xls = new XLS(stream);
            assertThat(
                    (xls.excel)
                            .getSheetAt(0)
                            .getRow(1)
                            .getCell(1)
                            .getStringCellValue())
                    .isEqualTo("Dulce");

        }
    }
}
