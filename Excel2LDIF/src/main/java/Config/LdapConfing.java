package Config;

import log.Log;

import javax.naming.Context;
import javax.naming.directory.SearchControls;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Properties;

public class LdapConfing {

    public static String loadPropertiesFiles(String variablePropertiesName) {
        try {
            Properties fileProperties = new Properties();
            fileProperties.load(Files.newInputStream(Paths.get("C:\\Projects\\Projeto_Codigos_Ajuda\\Projeto_Codigos_Ajuda\\src\\main\\resources\\application.properties")));
            return fileProperties.getProperty(variablePropertiesName);
        } catch (IOException e) {
            Log.printError(e.getMessage());
        }
        return variablePropertiesName;
    }

    public static Hashtable<String, String> setEnv() {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, loadPropertiesFiles("ldapURL"));
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, loadPropertiesFiles("ldapUsernameCN"));
        env.put(Context.SECURITY_CREDENTIALS, loadPropertiesFiles("ldapPassword"));
        return env;
    }

    public static SearchControls getSearchControls(String[] searchAttributes) {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(searchAttributes);
        return searchControls;
    }
}
