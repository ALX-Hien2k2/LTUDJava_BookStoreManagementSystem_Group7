package business;

import dataaccess.CategoryDA;
import pojo.CategoryPOJO;
import java.util.*;

public class CategoryBU {
    public List<CategoryPOJO> getAllCategory(){
        CategoryDA da = new CategoryDA();
        return da.getAllCategory();
    }
    public List<CategoryPOJO> getAllDisabledCategory(){
        CategoryDA da = new CategoryDA();
        return da.getAllDisabledCategory();
    }
    public List<CategoryPOJO> searchCategory(String name){
        CategoryDA da = new CategoryDA();
        return da.searchCategory(name);
    }
    public List<CategoryPOJO> searchDisabledCategory(String name){
        CategoryDA da = new CategoryDA();
        return da.searchDisabledCategory(name);
    }
    public int insertCategory(CategoryPOJO newCategory){
        CategoryDA da = new CategoryDA();
        return da.insertCategory(newCategory);
    }
    public int updateCategoryInfo(CategoryPOJO updateCategory){
         CategoryDA da = new CategoryDA();
         return da.updateCategoryInfo(updateCategory);
    }
    
    public int disableCategory(int id){
         CategoryDA da = new CategoryDA();
         return da.disableCategory(id);
    }
    
    public int enableCategory(int id){
         CategoryDA da = new CategoryDA();
         return da.enableCategory(id);
    }
}
