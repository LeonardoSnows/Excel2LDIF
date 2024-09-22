import Config.LdapConfing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapSearchTest {
    private static Hashtable<String, String> env;

    @BeforeAll
    static void setEvn() {
        env = LdapConfing.setEnv();
    }

    @Test
    void testConnectionLdap() {
        DirContext ctx;

        try {
            ctx = new InitialDirContext(env);
            Assertions.assertNotNull(ctx);

            if (ctx != null) ctx.close();
        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
