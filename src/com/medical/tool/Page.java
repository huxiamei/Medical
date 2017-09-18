package com.medical.tool;

/**
 * ������
 * ���ڱ��浱ǰҳ����ҳ��ÿҳ�������
 * @author Administrator
 *
 */
public class Page {

	//�ڼ�ҳ
	private int pageNo;
	
	//��ҳ��
	private int pageCount;
	
	//ÿҳ�������
	private int rowNo;

	/**
	 * 初始化页面对象
	 * @param pageNo 当前页面
	 * @param count 总数
	 * @param rowNo 页面显示数量
	 */
	public Page(int pageNo,int count,int rowNo)
	{
		this.rowNo = rowNo;
		setPageCount(count);
		this.pageNo = pageNo;
		if(pageNo <= 0)
			this.pageNo = 1;
		else if(pageNo>=pageCount)
			this.pageNo = pageCount;
	} 
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int count) {
		
		if(count == 0 )
		{
			pageCount = 1;
		}
		else
		{
			pageCount = count/rowNo;
			if(count%rowNo != 0)
				pageCount += 1;
		}
		
	}
	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	
	
	
}
