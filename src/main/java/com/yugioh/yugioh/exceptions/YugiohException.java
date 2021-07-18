package com.yugioh.yugioh.exceptions;

public class YugiohException extends RuntimeException {
	private static final long serialVersionUID = 8633391674318795826L;

	public YugiohException(YugiohExceptionType exceptionType) {
		super(exceptionType.toString());
	}

	public YugiohException(YugiohExceptionType exceptionType, String message) {
		super(exceptionType.toString() + " " + message);
	}
}
