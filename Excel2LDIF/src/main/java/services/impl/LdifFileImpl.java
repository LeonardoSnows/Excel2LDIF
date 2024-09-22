package services.impl;

import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldif.LDIFWriter;
import model.Identity;
import services.LdifFile;

import java.io.IOException;
import java.util.Set;

public class LdifFileImpl implements LdifFile {
    @Override
    public void createLdifFile(String pathFile, Set<Identity> ldapValues) throws IOException {
        try (LDIFWriter ldifWriter = new LDIFWriter(pathFile + "/testeLdaps.ldif")) {
            String dnIdentity = null;
            for (Identity values : ldapValues) {
                dnIdentity = "cn=" + values.getCN() + ",ou=users,dc=example,dc=org";

                Entry entry = getEntry(dnIdentity, values);
                ldifWriter.writeEntry(entry);
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private static Entry getEntry(String dnIdentity, Identity values) {
        ApiSearchImpl cepApi = new ApiSearchImpl();

        Entry entry = new Entry(dnIdentity);
        entry.addAttribute("changetype", "modify");
        entry.addAttribute("add", "st");
        entry.addAttribute("add", "sn");
        entry.addAttribute("st", cepApi.requestAPI(values.getST()));
        entry.addAttribute("sn", values.getSN());
        return entry;
    }

}

