package org.tomis.mvc.model.dto;

/**
 * Project: tomis-mvc
 * @since 16.02.2010
 * @author Dan Persa
 */
public class Paginator implements Dto {

    // private static Logger logger = Logger.getLogger(Paginator.class);
    private long pageSize;
    private long page;
    private long nrOfItems;

    public Paginator() {
    }

    public Paginator(long pageSize, long currentPage, long nrOfItems) {
        this.pageSize = pageSize;
        this.page = currentPage;
        this.nrOfItems = nrOfItems;
        if (currentPage < 0) {
            page = 0;
        }
        if (currentPage > getLastPage()) {
            page = getLastPage();
        }
    }

    public long getLastPage() {
        long x = ( nrOfItems / pageSize );
        if (nrOfItems % pageSize == 0) {
            return x;
        }
        return x + 1;
    }

    public long getPageFirstItem() {
        return page * pageSize + 1;
    }

    public int getPageLastItem() {
        long i = getPageFirstItem() + pageSize - 2;
        long count = getNrOfItems() - 1;
        if (i > count) {
            i = count;
        }
        if (i < 0) {
            i = 0;
        }
        return (int) ( i + 1 );
    }

    public boolean isHasNextPage() {
        return ( page + 1 ) * pageSize + 1 <= getNrOfItems();
    }

    public long getNextPage() {
        if (isHasNextPage()) {
            return page + 1;
        }
        return page;
    }

    public boolean isHasPreviousPage() {
        return page > 0;
    }

    public long getPreviousPage() {
        if (isHasPreviousPage()) {
            return page - 1;
        }
        return page;
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getNrOfItems() {
        return nrOfItems;
    }

    public void setPage(int currentPage) {
        this.page = currentPage;
        if (currentPage < 0) {
            page = 0;
        }
        if (currentPage > getLastPage()) {
            page = getLastPage();
        }
    }

    public long getPage() {
        return this.page;
    }
}
