/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package portalxpert.common.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**  
 * @Class Name : ImagePaginationRenderer.java
 * @Description : ImagePaginationRenderer Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
public class PortalImgPaginationRenderer extends AbstractPaginationRenderer {
	
    /**
    * PaginationRenderer
	* 
    * @see 개발프레임웍크 실행환경 개발팀
    */
	public PortalImgPaginationRenderer() {

		//String strWebDir = "/egovframework.guideprogram.basic/images/egovframework/cmmn/"; // localhost
		//String strWebDir = "/###ARTIFACT_ID###/images/cmmn/";
		//첫페이지
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"pbtn allprev\"><span class=\"hidden\">처음페이지</span></a>&nbsp;";
		//이전페이지
        previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"pbtn prev\"><span class=\"hidden\">이전페이지</span></a>&nbsp;";
        //선택페이지
        currentPageLabel = "<a href=\"#\" class=\"on\"><span class=\"num\">{0}</span></a>&nbsp;";
        //미선택페이지
        otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\"><span class=\"num\">{2}</span></a>&nbsp;";
        //다음페이지
        nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"pbtn next\"><span class=\"hidden\">다음페이지</span></a>&nbsp;";
        //마지막페이지
        lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"pbtn allnext\"><span class=\"hidden\">마지막페이지</span></a>";
        
        
        
	}
}
