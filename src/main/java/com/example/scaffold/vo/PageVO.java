package com.example.scaffold.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Page Response VO
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class PageVO<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Current page number
     */
    private Long current;
    
    /**
     * Page size
     */
    private Long size;
    
    /**
     * Total records
     */
    private Long total;
    
    /**
     * Total pages
     */
    private Long pages;
    
    /**
     * Data list
     */
    private List<T> records;
    
    /**
     * Has previous page
     */
    private Boolean hasPrevious;
    
    /**
     * Has next page
     */
    private Boolean hasNext;
    
    public PageVO() {}
    
    public PageVO(Long current, Long size, Long total, List<T> records) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.records = records;
        this.pages = (total + size - 1) / size;
        this.hasPrevious = current > 1;
        this.hasNext = current < pages;
    }
}