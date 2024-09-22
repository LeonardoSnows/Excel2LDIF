package services;

import model.Identity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface LdifFile {

    public void createLdifFile(String pathFile, Set<Identity> ldapValues) throws IOException;
}
