package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.TandCP;

import java.util.List;

public class TandCPListModel {

    List<TandCP> table;

    List<TandCP> chair;

    List<TandCP> all;

    public TandCPListModel() {
    }

    public List<TandCP> getTable() {
        return table;
    }

    public void setTable(List<TandCP> table) {
        this.table = table;
    }

    public List<TandCP> getChair() {
        return chair;
    }

    public void setChair(List<TandCP> chair) {
        this.chair = chair;
    }

    public List<TandCP> getAll() {
        return all;
    }

    public void setAll(List<TandCP> all) {
        this.all = all;
    }
}
