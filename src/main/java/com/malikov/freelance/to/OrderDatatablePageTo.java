package com.malikov.freelance.to;

import java.util.List;

public class OrderDatatablePageTo {

    private Long recordsTotal;

    private Long recordsFiltered;

    private List<ProjectTo> data;

    public OrderDatatablePageTo(Long recordsTotal, Long recordsFiltered, List<ProjectTo> data) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<ProjectTo> getData() {
        return data;
    }

    public void setData(List<ProjectTo> data) {
        this.data = data;
    }
}
