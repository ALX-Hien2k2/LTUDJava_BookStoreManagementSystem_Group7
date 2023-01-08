package business;

import dataaccess.PromotionDA;
import pojo.PromotionPOJO;
import java.util.*;

public class PromotionBU {
    public List<PromotionPOJO> getAllPromotion(){
        PromotionDA da = new PromotionDA();
        return da.getAllPromotion();
    }
    public List<PromotionPOJO> getAllDisabledPromotion(){
        PromotionDA da = new PromotionDA();
        return da.getAllDisabledPromotion();
    }
    public List<PromotionPOJO> getPastPromotion(){
        PromotionDA da = new PromotionDA();
        return da.getPastPromotion();
    }
    public List<PromotionPOJO> getCurrentPromotion(){
        PromotionDA da = new PromotionDA();
        return da.getCurrentPromotion();
    }
    public List<PromotionPOJO> getUpcomingPromotion(){
        PromotionDA da = new PromotionDA();
        return da.getUpcomingPromotion();
    }
    public List<PromotionPOJO> searchPromotion(String name){
        PromotionDA da = new PromotionDA();
        return da.searchPromotion(name);
    }
    public List<PromotionPOJO> searchDisabledPromotion(String name){
        PromotionDA da = new PromotionDA();
        return da.searchDisabledPromotion(name);
    }
//    public int insertPromotion(PromotionPOJO newPromotion){
//        PromotionDA da = new PromotionDA();
//        return da.insertPromotion(newCategory);
//    }
    public int updatePromotionInfo(PromotionPOJO updatePromotion){
         PromotionDA da = new PromotionDA();
         return da.updatePromotionInfo(updatePromotion);
    }
    public int updatePromotionDate(PromotionPOJO updatePromotionDate){
         PromotionDA da = new PromotionDA();
         return da.updatePromotionDate(updatePromotionDate);
    }

    public String openPromotion(int promotion_id){
        PromotionDA da = new PromotionDA();
        return da.openPromotion(promotion_id);
    }
    public String closePromotion(int promotion_id){
        PromotionDA da = new PromotionDA();
        return da.closePromotion(promotion_id);
    }
    public int disablePromotion(int id){
         PromotionDA da = new PromotionDA();
         return da.disablePromotion(id);
    }
    public int enablePromotion(int id){
         PromotionDA da = new PromotionDA();
         return da.enablePromotion(id);
    }
}
