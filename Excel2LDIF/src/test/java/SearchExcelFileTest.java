import model.Identity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import search.ReadFiles;

import java.io.FileNotFoundException;
import java.util.Set;

public class SearchExcelFileTest {
    private static Set<Identity> identities = null;
    private static final String PATH_FILE = "C:/Users/Leonardo/OneDrive/Teste_Search_LDAP.xlsx";

    @BeforeAll
    static void readFileBeforeAll() throws FileNotFoundException {
        identities = ReadFiles.readValues(PATH_FILE);
        Assertions.assertNotNull(identities);
        Assertions.assertFalse(identities.isEmpty());
    }


    @Test
    void isListValuesValid() {
        identities.forEach(values -> {
            Assertions.assertNull(values.getSN());
            Assertions.assertNotNull(values.getCN());
            Assertions.assertNotNull(values.getGID_NUMBER());
            Assertions.assertNotNull(values.getST());
        });
    }

    @Test
    void isNotValidPathFile() {
        final String WRONG_PATH_FILE = "C:/Users/Leonardo/OneDrive/Teste_SeAAAarch_LDAP.xlsx";

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            ReadFiles.readValues(WRONG_PATH_FILE);
        }, "Espera-se que lance uma excecao");
    }


}
