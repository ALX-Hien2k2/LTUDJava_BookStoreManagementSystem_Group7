package business;

import java.util.*;
import dataaccess.PublisherDA;
import pojo.PublisherPOJO;

public class PublisherBU {

    public PublisherPOJO getPublisher(int id) {
        PublisherDA da = new PublisherDA();
        return da.getPublisher(id);
    }

    public List<PublisherPOJO> getAllPublisher() {
        PublisherDA da = new PublisherDA();
        return da.getAllPublisher();
    }

    public List<PublisherPOJO> getAllDisabledPublisher() {
        PublisherDA da = new PublisherDA();
        return da.getAllDisabledPublisher();
    }

    public List<PublisherPOJO> searchPublisher(String publisherName) {
        PublisherDA da = new PublisherDA();
        return da.searchPublisher(publisherName);
    }

    public List<PublisherPOJO> searchDisabledPublisher(String name) {
        PublisherDA da = new PublisherDA();
        return da.searchDisabledPublisher(name);
    }

    public int insertPublisher(PublisherPOJO newPublisher) {
        PublisherDA da = new PublisherDA();
        return da.insertPublisher(newPublisher);
    }

    public int updatePublisher(PublisherPOJO publisherI) {
        PublisherDA da = new PublisherDA();
        return da.updatePublisher(publisherI);
    }

    public int disablePublisher(int id) {
        PublisherDA da = new PublisherDA();
        return da.disablePublisher(id);
    }

    public int enablePublisher(int id) {
        PublisherDA da = new PublisherDA();
        return da.enablePublisher(id);
    }
}
