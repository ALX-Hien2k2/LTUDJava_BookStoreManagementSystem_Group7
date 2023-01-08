/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import dataaccess.ImportBookDA;
import java.util.List;
import pojo.BookImportPOJO;
import pojo.ImportDetails;
/**
 *
 * @author TIN
 */
public class ImportBookBU {
    public List<BookImportPOJO> getAll(){
        ImportBookDA da = new ImportBookDA();
        return da.getAll();
    }
    public List<ImportDetails> getDetails(){
        ImportBookDA da = new ImportBookDA();
        return da.getDetails();
    }
    public boolean add_import_book(int id, String day, int staff_id, double total){
        ImportBookDA da = new ImportBookDA();
        return da.add_import(id, day, staff_id, total);
    }
    public boolean add_import_detail(int id, int book_id, int quant,double price, double total){
        ImportBookDA da = new ImportBookDA();
        return da.add_import_details(id, book_id, quant, price, total);
    }
}
