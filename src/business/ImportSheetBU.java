package business;

import java.util.*;
import dataaccess.ImportSheetDA;
import pojo.ImportSheetPOJO;
import pojo.DetailImportSheetPOJO;

public class ImportSheetBU {
    public List<ImportSheetPOJO> getAllImportSheet() {
        ImportSheetDA da = new ImportSheetDA();
        return da.getAllImportSheet();
    }

    public List<DetailImportSheetPOJO> getDetailImportSheet(int id) {
        ImportSheetDA da = new ImportSheetDA();
        return da.getDetailImportSheet(id);
    }
}
