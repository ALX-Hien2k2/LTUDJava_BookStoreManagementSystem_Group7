/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

/**
 *
 * @author TIN
 */
import pojo.book;
import java.util.*;
import dataaccess.*;

public class book_business {
    public List<book> getAll(){
        book_da da = new book_da();
        return da.getAll();
    }
}

