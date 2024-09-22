package services;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileNotFoundException;

public interface ExcelFile {

    public Sheet readExcelFile(String pathFile) throws FileNotFoundException;
}
