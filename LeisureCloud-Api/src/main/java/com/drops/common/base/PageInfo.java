package com.drops.common.base;

import com.drops.common.base.PageInfo;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;


public class PageInfo<T>
        implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list;
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean isFirstPage = false;
    private boolean isLastPage = false;
    private boolean hasPreviousPage = false;
    private boolean hasNextPage = false;
    private int navigatePages;
    private int[] navigatepageNums;

    public PageInfo(List<T> list) {
        this(list, 8);
    }


    public PageInfo(List<T> list, int navigatePages) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = (List<T>) page;
            this.size = page.size();

            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                this.endRow = this.startRow - 1 + this.size;
            }
            this.navigatePages = navigatePages;
            calcNavigatepageNums();
            calcPage();
            judgePageBoudary();
        }
    }


    private void calcNavigatepageNums() {
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];
            for (int i = 0; i < this.pages; i++) {
                this.navigatepageNums[i] = i + 1;
            }
        } else {
            this.navigatepageNums = new int[this.navigatePages];
            int startNum = this.pageNum - this.navigatePages / 2;
            int endNum = this.pageNum + this.navigatePages / 2;
            if (startNum < 1) {
                startNum = 1;
                for (int i = 0; i < this.navigatePages; i++) {
                    this.navigatepageNums[i] = startNum++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;
                for (int i = this.navigatePages - 1; i >= 0; i--) {
                    this.navigatepageNums[i] = endNum--;
                }
            } else {
                for (int i = 0; i < this.navigatePages; i++) {
                    this.navigatepageNums[i] = startNum++;
                }
            }
        }
    }


    private void calcPage() {
        if (this.navigatepageNums != null && this.navigatepageNums.length > 0) {
            this.firstPage = this.navigatepageNums[0];
            this.lastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }
            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }
    }


    private void judgePageBoudary() {
        this.isFirstPage = (this.pageNum == 1);
        this.isLastPage = (this.pageNum == this.pages);
        this.hasPreviousPage = (this.pageNum > 1);
        this.hasNextPage = (this.pageNum < this.pages);
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }
    public int getSize() {
        return this.size;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public long getTotal() {
        return this.total;
    }

    public int getPages() {
        return this.pages;
    }

    public List<T> getList() {
        return this.list;
    }


    public int getFirstPage() {
        return this.firstPage;
    }

    public int getPrePage() {
        return this.prePage;
    }


    public int getNextPage() {
        return this.nextPage;
    }


    public int getLastPage() {
        return this.lastPage;
    }


    public boolean isIsFirstPage() {
        return this.isFirstPage;
    }


    public boolean isIsLastPage() {
        return this.isLastPage;
    }


    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }


    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public int getNavigatePages() {
        return this.navigatePages;
    }


    public int[] getNavigatepageNums() {
        return this.navigatepageNums;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", startRow=").append(this.startRow);
        sb.append(", endRow=").append(this.endRow);
        sb.append(", total=").append(this.total);
        sb.append(", pages=").append(this.pages);
        sb.append(", list=").append(this.list);
        sb.append(", firstPage=").append(this.firstPage);
        sb.append(", prePage=").append(this.prePage);
        sb.append(", nextPage=").append(this.nextPage);
        sb.append(", lastPage=").append(this.lastPage);
        sb.append(", isFirstPage=").append(this.isFirstPage);
        sb.append(", isLastPage=").append(this.isLastPage);
        sb.append(", hasPreviousPage=").append(this.hasPreviousPage);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", navigatePages=").append(this.navigatePages);
        sb.append(", navigatepageNums=");
        if (this.navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < this.navigatepageNums.length; i++) {
                sb.append((i == 0) ? "" : ", ").append(this.navigatepageNums[i]);
            }
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}