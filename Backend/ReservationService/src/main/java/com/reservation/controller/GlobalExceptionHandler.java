package com.reservation.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reservation.model.ErrorResponse;
import com.reservation.exception.ReservationNotFoundException;

public class GlobalExceptionHandler {

	@ExceptionHandler(value = { ReservationNotFoundException.class })
	public ErrorResponse farmerNotFound(ReservationNotFoundException ex) {

		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(HttpStatus.NOT_FOUND);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getMessage());
		return error;

	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ErrorResponse methodtNotSupport(HttpRequestMethodNotSupportedException ex) {

		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(HttpStatus.METHOD_NOT_ALLOWED);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getMessage());
		return error;

	}

	@ExceptionHandler(value = { Exception.class })
	public ErrorResponse handleException(Exception ex) {

		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getMessage());
		return error;

	}
}
