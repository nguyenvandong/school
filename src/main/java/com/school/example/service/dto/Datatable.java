package com.school.example.service.dto;

import java.util.List;

/**
 * Created by DONG on 6/19/2018.
 */
public class Datatable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<?> data;

    public Datatable() {
    }

    /**
     *  Constructor with 3 param
     * @param draw
     * @param recordsTotal
     * @param data
     */
    public Datatable(int draw, int recordsTotal, List<?> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
