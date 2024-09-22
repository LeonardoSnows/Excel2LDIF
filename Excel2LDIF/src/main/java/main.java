import log.Log;
import model.Identity;
import search.LdapSearch;
import search.ReadFiles;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Set;

public class main {
    public static void main(String[] args) throws NamingException, IOException {

        Log.printInfo("Lendo arquivo com dados a ser procurados...");
        Set<Identity> datasFromFile = ReadFiles.readValues("C:/Users/Leonardo/OneDrive/Teste_Search_LDAP.xlsx");

        Log.printInfo("Arquivo lido com sucesso!!");
        LdapSearch.searchLdap(datasFromFile);
    }
}
