package util;

/**
 * 用户定义的Exception类
 * 
 * @author 钱洋
 * @date 2017年6月6日 下午3:48:32
 */
public class AppException extends Exception {
	private int exceptionCode; // 异常代码
	private String message; // 异常信息

	/**
	 * 构造函数，设置异常信息
	 * 
	 * @param message
	 */
	public AppException(String message) {
		this.message = message;
	}

	/**
	 * 构造函数，设置异常信息和异常代码
	 * 
	 * @param message
	 * @param exceptionCode
	 */
	public AppException(String message, int exceptionCode) {
		this.message = message;
		this.exceptionCode = exceptionCode;
	}

	/**
	 * 获取异常代码
	 * 
	 * @return exceptionCode
	 */
	public int getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * 获取详细异常信息
	 * 
	 * @return detailMessage
	 */
	public String getMessage() {
		String detailMessage = "Detail Message:" + exceptionCode + " " + message;
		return detailMessage;
	}

}
