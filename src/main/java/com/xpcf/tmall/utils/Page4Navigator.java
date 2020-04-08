package com.xpcf.tmall.utils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @program: tmall_springboot
 * @description: no
 * @author: "xpcf"
 * @create: 2020-03-26 18:34
 **/
public class Page4Navigator<T> {
    Page<T> pageFromJPA;

    Integer navigatePages;

    Integer totalPages;

    Integer number;

    Long totalElements;

    Integer size;

    Integer numberOfElements;

    List<T> content;

    Boolean isHasContent;

    Boolean first;

    Boolean last;

    Boolean isHasNext;

    Boolean isHasPrevious;

    Integer[] navigatePageNums;

    public Page4Navigator(){

    }

    public Page4Navigator(Page<T> pageFromJPA,Integer navigatePages){
        this.pageFromJPA = pageFromJPA;
        this.navigatePages = navigatePages;

        totalPages = pageFromJPA.getTotalPages();

        number = pageFromJPA.getNumber();

        totalElements = pageFromJPA.getTotalElements();

        size = pageFromJPA.getSize();

        numberOfElements = pageFromJPA.getNumberOfElements();

        content = pageFromJPA.getContent();

        isHasContent = pageFromJPA.hasContent();

        first = pageFromJPA.isFirst();

        last = pageFromJPA.isLast();

        isHasNext = pageFromJPA.hasNext();

        isHasPrevious = pageFromJPA.hasPrevious();
        calNavigatePageNums();
    }

    private void calNavigatePageNums(){
        Integer navigatePageNums[];

        Integer totalPages = getTotalPages();

        Integer num = getNumber();

        if(totalPages <= navigatePages){
            navigatePageNums = new Integer[totalPages];
            for(int i=0;i<totalPages;++i){
                navigatePageNums[i] = i+1;
            }
        }else {
            navigatePageNums = new Integer[navigatePages];
            Integer startNum = num - navigatePages/2;
            Integer endNum = num + navigatePages/2;
            if(startNum < 1){
                startNum = 1;
                for (int i = 0; i <navigatePages; i++) {
                    navigatePageNums[i] = startNum++;
                }
            }else  if(endNum>totalPages){
                endNum = totalPages;
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatePageNums[i] = endNum--;
                }
            }else {
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNums[i] = startNum++;
                }
            }
            this.navigatePageNums = navigatePageNums;
        }
    }


    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean isHasContent) {
        this.isHasContent = isHasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean isHasNext) {
        this.isHasNext = isHasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean isHasPrevious) {
        this.isHasPrevious = isHasPrevious;
    }

    public Integer[] getNavigatePageNums() {
        return navigatePageNums;
    }

    public void setNavigatePageNums(Integer[] navigatePageNums) {
        this.navigatePageNums = navigatePageNums;
    }





}
