package com.bdssite.modules.common.dto;



import com.bdssite.modules.common.RequestStatus;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by D on 2017/7/14.
 */
public abstract class BasePagingDto<S> extends BaseDto {

    int page;

    long total;

    public List<S> rows;

    public BasePagingDto(RequestStatus status, Page<S> rows) {
        super(status);
        this.setPage(rows.getNumber() + 1);
        this.setTotal(rows.getTotalElements());
        this.rows = rows.getContent();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<S> getRows() {
        return rows;
    }

    public void setRows(List<S> rows) {
        this.rows = rows;
    }
}
