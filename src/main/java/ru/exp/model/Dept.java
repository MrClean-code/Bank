package ru.exp.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Dept {
    private int id;

    @Min(1)
    private int limit;

    @Min(2)
    private int percent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
