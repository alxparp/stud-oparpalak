package com.eisgroup.layered.controllers;

import com.eisgroup.layered.enums.SearchType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@RequestScoped
public class SearchTypeController {

    private static final String authorName = "Автор";
    private static final String bookName = "Название";

    private static Map<String, SearchType> searchList = new HashMap<String, SearchType>(); // хранит все виды поисков (по автору, по названию)

    public SearchTypeController() {
        searchList.clear();
        searchList.put(authorName, SearchType.AUTHOR);
        searchList.put(bookName, SearchType.TITLE);
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }
}