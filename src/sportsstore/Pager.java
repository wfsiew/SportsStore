package sportsstore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Pager {
	private int total;
	private int pageNum;
	private int pageSize;
	private String linkUrl;
	private Map<String, String> requestParam = new HashMap<String, String>();

	public Pager(int total, int pageNum, int pageSize) {
		setTotal(total);
		setPageNum(pageNum);
		setPageSize(pageSize);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (total < pageSize || pageSize < 1)
			this.pageSize = total;
		
		else
			this.pageSize = pageSize;
	}
	
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = String.format("/sportsstore%s", linkUrl);
	}
	
	public String getItemMessage() {
		return Utils.getItemMessage(total, pageNum, pageSize);
	}
	
	public int getLowerBound() {
		return (pageNum - 1) * pageSize;
	}
	
	public int getUpperBound() {
		int upperBound = pageNum * pageSize;
		if (total < upperBound)
			upperBound = total;
		
		return upperBound;
	}
	
	public boolean hasNext() {
		return (total > getUpperBound() ? true : false);
	}
	
	public boolean hasPrev() {
		return (getLowerBound() > 0 ? true : false);
	}
	
	public int getTotalPages() {
		return (int)Math.ceil((double)getTotal() / getPageSize());
	}
	
	public void addRequestParam(String key, String value) {
		if (value != null && value != "")
			requestParam.put(key, value);
	}

	public String getPageLinks() {
		StringBuilder sb = new StringBuilder();
		String q = buildRequestParam();
		for (int i = 1; i <= getTotalPages(); i++) {
			String h = String.format("<a href=\"%s/%d%s\"", getLinkUrl(), i, q);
			sb.append(h);
			if (i == getPageNum()) {
				sb.append(" class=\"selected\"");
			}
			
			sb.append(String.format(">%d</a>", i));
		}
		
		return sb.toString();
	}
	
	private String buildRequestParam() {
		if (requestParam.size() < 1)
			return "";
		
		StringBuilder sb = new StringBuilder();
		for (Iterator iterator = requestParam.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String value = requestParam.get(key);
			if (sb.length() < 1)
				sb.append(String.format("?%s=%s", key, value));
			
			else
				sb.append(String.format("&%s=%s", key, value));
		}
		
		return sb.toString();
	}
}
