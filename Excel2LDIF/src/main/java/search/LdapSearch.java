package search;

import Config.LdapConfing;
import log.Log;
import model.Identity;
import services.LdifFile;
import services.impl.LdifFileImpl;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

public class LdapSearch {

    public static void searchLdap(Set<Identity> datasFromFile) throws NamingException, IOException {

        String searchFilter = null;
        DirContext context = null;

        String ldapBaseDN = "ou=users,dc=example,dc=org";
        Hashtable<String, String> env = LdapConfing.setEnv();

        for (Identity valuesFromFile : datasFromFile) {
            String getCnValue = valuesFromFile.getCN();

            searchFilter = "(cn=*" + getCnValue + "*)";
            String[] searchAttributes = {"sn"};

            try {
                context = new InitialDirContext(env);

                SearchControls searchControls = LdapConfing.getSearchControls(searchAttributes);

                NamingEnumeration<SearchResult> results = context.search(ldapBaseDN, searchFilter, searchControls);

                getValuesFromSearch(valuesFromFile, results);

                context.close();
            } catch (NamingException e) {
                throw new NamingException("Falha ao conectar ao servidor LDAP: " + e.getMessage());
            }
        }

        Log.printInfo("Arquivo LDIF esta sendo montado e em assim que o programa finalizar coretamente, o arquivo estara pronto...");

        LdifFile arquivoLdif = new LdifFileImpl();
        arquivoLdif.createLdifFile("C:\\Projects\\Projeto_Codigos_Ajuda\\Projeto_Codigos_Ajuda\\src\\main\\resources\\ldifFiles\\", datasFromFile);
    }

    private static void getValuesFromSearch(Identity dataToSearch, NamingEnumeration<SearchResult> results) throws NamingException {
        while (results.hasMore()) {
            SearchResult searchResult = results.next();
            Attributes attrs = searchResult.getAttributes();
            NamingEnumeration<?> attributesToIterate = attrs.getAll();

            setSnValuesForIdentity(dataToSearch, attributesToIterate);
        }
    }

    private static void setSnValuesForIdentity(Identity dataToSearch, NamingEnumeration<?> attributesToIterate) throws NamingException {
        while (attributesToIterate.hasMore()) {
            Attribute attribute = (Attribute) attributesToIterate.next();
            NamingEnumeration<?> values = attribute.getAll();

            while (values.hasMore()) {
                Object snValue = values.next();
                dataToSearch.setSN(snValue.toString());
            }
        }
    }
}