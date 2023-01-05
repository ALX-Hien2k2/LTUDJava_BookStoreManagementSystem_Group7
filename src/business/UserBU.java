package business;

import dataaccess.UserDA;
import pojo.UserPOJO;
import java.util.*;

public class UserBU {
    public List<UserPOJO> getAllAccount(){
        UserDA da = new UserDA();
        return da.getAllAccount();
    }
    public List<UserPOJO> searchAccount(String name){
        UserDA da = new UserDA();
        return da.searchAccount(name);
    }
    public int insertAccount(UserPOJO newAccount){
        UserDA da = new UserDA();
        return da.insertAccount(newAccount);
    }
    public String enableAccount(int account_id){
        UserDA da = new UserDA();
        return da.enableAccount(account_id);
    }
    public String disableAccount(int account_id){
        UserDA da = new UserDA();
        return da.disableAccount(account_id);
    }
    public String resetPassword(int account_id){
        UserDA da = new UserDA();
        return da.resetPassword(account_id);
    }
    public int updateAccountInfo(UserPOJO updateAccount){
        UserDA da = new UserDA();
        return da.updateAccountInfo(updateAccount);
    }
}
