package search;

import log.Log;
import model.Identity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import services.ExcelFile;
import services.impl.ExcelFileImpl;

import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReadFiles {

    public static Set<Identity> readValues(String File) throws FileNotFoundException {
        Set<Identity> identity = new LinkedHashSet<>();

        try {
            ExcelFile excelFiles = new ExcelFileImpl();
            Sheet sheet = excelFiles.readExcelFile(File);

            getValueFromSheet(sheet, identity);
        } catch (NullPointerException e) {
            Log.printError("Arquivo nao encontrado !!!");
            throw new FileNotFoundException("Arquivo nao encontrado !!!");
        }

        return identity;
    }

    private static void getValueFromSheet(Sheet sheet, Set<Identity> identity) {
        for (Row row : sheet) {
            Cell cn = row.getCell(0);
            Cell gidNumber = row.getCell(1);
            Cell cep = row.getCell(2);

            if (cn != null && !cn.toString().isEmpty())
                identity.add(
                        new Identity.Builder(cn.toString(), gidNumber.toString())
                                .setST(cep.toString())
                                .build()
                );
            else break;
        }
    }
}
