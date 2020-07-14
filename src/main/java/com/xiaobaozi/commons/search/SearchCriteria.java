package com.xiaobaozi.commons.search;

import java.io.Serializable;

/**
 * 目标：统一的搜索条件。集成搜索条件（参数名和值）、分页、排序、搜索保存和重用等功能。
 * @deprecated 最新修改见com.skyecho.shenzhenairlines.commons.search.SearchCriteria
 */
public class SearchCriteria implements Serializable {

	private static final long	serialVersionUID			= -6898898403105146143L;

	/**
	 * 总页数
	 */
	private int					totalPageCount;														

	/**
	 * 当前页码
	 */
	private int					pageNo=1;														

	/**
	 * 每页的记录数
	 */
	private int					pageSize=10;	
	
	/**
	 * 当前页第一条数据在List中的位置,从0开始
	 */
	private int					startIdx;	

	/**
	 * 总记录数
	 */
	private long				totalCount;
	
	/**
	 * 排序方式
	 */	
	private String				orderBy;
	
	
	/**
	 * 修改页码和每页记录数
	 * 不改变搜索条件，因此可以重用
	 * 
	 */
	public void changePaging(int newPageNo, int newPageSize) {
		pageNo = newPageNo;
		pageSize = newPageSize;
		recalc();
	}
	
	/**
	 * 修改总记录数
	 * 一般是传SC给dao后由dao更新
	 * 
	 * @param in_totalCount
	 */
	public void setTotalCount(long in_totalCount) {
		this.totalCount = in_totalCount;
		recalc();
	}
	
	/**
	 * 重新计算分页值
	 * 
	 */
	private void recalc() {
		if(pageSize > 0){
			totalPageCount = (int) Math.ceil((double) totalCount / (double) pageSize);
			if(pageNo > totalPageCount){
				pageNo = totalPageCount;
			}
			startIdx = (pageNo - 1) * pageSize;
		}else{
			totalPageCount = 1; 
			startIdx = 0;
		}
	}


	
	public int getTotalPageCount() {
		return totalPageCount;
	}
	

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public int getStartIdx() {
		return startIdx;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SearchCriteria [pageCount=" + totalPageCount + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize+ ", startIdx=" + startIdx + ", totalCount=" + totalCount + ", orderBy="+ orderBy + "]";
	}
	
	
	
}
