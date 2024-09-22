package services.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import services.ExcelFile;

import java.io.*;

public class ExcelFileImpl implements ExcelFile {

    @Override
    public Sheet readExcelFile(String pathFile) throws FileNotFoundException {
        Sheet sheet = null;
        Workbook wb = null;

        try (BufferedInputStream fis =
                     new BufferedInputStream(
                             new FileInputStream(new File(pathFile)))) {
            wb = WorkbookFactory.create(fis);
            sheet = wb.getSheetAt(0);
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        return sheet;
    }
}
