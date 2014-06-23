package portalxpert.common.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SearchConditionVO implements Serializable {
	
	private static final long serialVersionUID = 1673228398746452009L;

	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    private String dateSt; 			//검색 시작날짜
    private String dateEd; 			//검색 끝날짜
    private String loginedUserId;	//로그인유저ID
    private String userType; 		//유저종류(admin,user)
    private String pageType;		//페이지종류(I,U,V,D)
    private String rnum;			// row number
    private int currentRecordCount; //현재 Record 수
    
    public int getCurrentRecordCount() {
		return currentRecordCount;
	}

	public void setCurrentRecordCount(int currentRecordCount) {
		this.currentRecordCount = currentRecordCount;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDateSt() {
		return dateSt;
	}

	public void setDateSt(String dateSt) {
		this.dateSt = dateSt;
	}

	public String getDateEd() {
		return dateEd;
	}

	public void setDateEd(String dateEd) {
		this.dateEd = dateEd;
	}
	
	public String getLoginedUserId() {
		return loginedUserId;
	}

	public void setLoginedUserId(String loginedUserId) {
		this.loginedUserId = loginedUserId;
	}
	
	
	
	
	
	
	

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

    
}
