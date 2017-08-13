package com.bdssite.modules.common.dto;

import com.bdssite.modules.common.RequestStatus;
import org.springframework.data.domain.Page;

/**
 * 带分页的列表返回Dto
 * Created by D on 2017/7/15.
 */
public class PagingDto<S> extends BasePagingDto<S> {

    public PagingDto(RequestStatus status, Page<S> rows) {
        super(status,rows);
    }

}
