package com.eisgroup.layered.controllers;

import com.eisgroup.layered.beans.Car;
import com.eisgroup.layered.enums.SearchType;
import org.primefaces.component.datatable.DataTable;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class BookListController {

    private String searchString;
    private SearchType selectedSearchType = SearchType.TITLE;
    private DataTable dataTable;
    private List<Car> cars;
    private boolean editModeView;
    private boolean availabilityType;
    private List<String> availabilityList;


    public void fillBooksBySearch() {

//        submitValues(' ', -1);

        if (searchString.trim().length() == 0) {
//            fillBooksAll();
        }

        if (selectedSearchType == SearchType.AUTHOR) {
//            dataHelper.getBooksByAuthor(searchString);
        } else if (selectedSearchType == SearchType.TITLE) {
//            dataHelper.getBooksByName(searchString);
        }

    }

    public BookListController() {
        cars = new ArrayList<>();
        availabilityList = new ArrayList<>();
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public SearchType getSearchType() {
        return selectedSearchType;
    }

    public void setSearchType(SearchType selectedSearchType) {
        this.selectedSearchType = selectedSearchType;
    }

    public void searchStringChanged(ValueChangeEvent e) {
        searchString = e.getNewValue().toString();
    }

    public void searchTypeChanged(ValueChangeEvent e) {
        selectedSearchType = (SearchType) e.getNewValue();
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public List<Car> getCars() {
        cars.clear();
        cars.add(new Car(1, "BMW", 12000, true));
        cars.add(new Car(2, "Mercedes", 22000, false));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        cars.add(new Car(3, "Maybach", 15000, true));
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public boolean isEditModeView() {
        return editModeView;
    }

    public void cancelEditMode() {
        editModeView = false;
    }

    public void switchEditMode() {
        editModeView = true;
    }

    public boolean isAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(boolean availabilityType) {
        this.availabilityType = availabilityType;
    }

    public List<String> getAvailabilityList() {
        availabilityList.clear();
        availabilityList.add("all");
        availabilityList.add("true");
        availabilityList.add("false");
        return availabilityList;
    }

    public void setAvailabilityList(List<String> availabilityList) {
        this.availabilityList = availabilityList;
    }
}
