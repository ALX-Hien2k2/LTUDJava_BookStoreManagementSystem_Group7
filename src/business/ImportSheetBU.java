package business;

import java.util.*;
import dataaccess.ImportSheetDA;
import pojo.ImportSheetPOJO;

public class ImportSheetBU {
    public List<ImportSheetPOJO> getAllImportSheet() {
        ImportSheetDA da = new ImportSheetDA();
        return da.getAllImportSheet();
    }
}
