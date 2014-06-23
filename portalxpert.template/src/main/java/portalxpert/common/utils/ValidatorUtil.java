package portalxpert.common.utils;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


/**********************************************************************************
* CLASS 명		:	ValidatorUtil	
* 설명            	: 	Validation 체크
* 작성자         	: 	crossent
* 작성일         	: 	2013-02-25
* 버전          	: 	1.0
* 수정이력       	:    수정자:      DESC: 
***********************************************************************************/

public class ValidatorUtil {
	
	/**
	 * Get validation information
	 * @param BindingResult result
	 * @return ArrayList<FieldVO>
	 */
	public static ArrayList<FieldVO> getErrorsInfo(BindingResult result) {
		ArrayList<FieldVO> list = new ArrayList<FieldVO>();

		for (Object object : result.getAllErrors()) {
			if (object instanceof FieldError) {
				FieldError fieldError = (FieldError) object;
				String fieldName = fieldError.getField();
				String message = fieldError.getDefaultMessage();

				FieldVO fieldVO = new FieldVO(fieldName, message);
				list.add(fieldVO);
			}
		}
		
		return list;
	}
	
	/**
	 * Get validation information
	 * @param BindingResult result
	 * @param modelMap
	 */
	public static void getErrorsInfo(BindingResult result,ModelMap modelMap) {
		modelMap.put("errorsInfo", getErrorsInfo(result));
	}
}

class FieldVO{
	
	private String fieldName ;
	private String message = "No Message!";
	
	public FieldVO(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}