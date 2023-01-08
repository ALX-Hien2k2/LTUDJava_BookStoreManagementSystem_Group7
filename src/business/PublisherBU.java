package business;

import java.util.*;
import dataaccess.PublisherDA;
import pojo.PublisherPOJO;

public class PublisherBU {
    public PublisherPOJO getPublisher(int id) {
        PublisherDA da = new PublisherDA();
        return da.getPublisher(id);
    }

    public List<PublisherPOJO> getAll() {
        PublisherDA da = new PublisherDA();
        return da.getAll();
    }
    
     public List<PublisherPOJO> searchPublisher(String publisherName){
        PublisherDA da = new PublisherDA();
        return da.searchPublisher(publisherName);
    }
    public int insertPublisher(PublisherPOJO newPublisher){
        PublisherDA da = new PublisherDA();
        return da.insertPublisher(newPublisher);
    }
}