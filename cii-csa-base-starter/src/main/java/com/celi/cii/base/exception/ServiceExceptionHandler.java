package com.celi.cii.base.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.celi.cii.common.entity.ResponseDTO;
import com.celi.cii.common.exception.BadRequestException;
import com.celi.cii.common.exception.ServiceCode;
import com.celi.cii.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler implements IServiceExceptionHandler {

    public ResponseEntity handleException(Exception e) {
        log.error(e.getLocalizedMessage(), e);

        String className = e.getClass().getName();

        if (e instanceof ConstraintViolationException) {
            //表单验证错误
            StringBuilder sb = new StringBuilder();

            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                sb.append(item.getMessage());
                break;
            }

            ResponseDTO dto = ResponseDTO.errorMessage(sb.toString());
            return ResponseEntity.badRequest().body(dto);
        } else if (e instanceof BindException) {
            StringBuilder sb = new StringBuilder();
            BindException es = (BindException) e;
            List<ObjectError> errors = es.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage());
            }
            ResponseDTO dto = ResponseDTO.errorMessage(sb.toString());
            return ResponseEntity.badRequest().body(dto);
        } else if (e instanceof BadRequestException) {
            ResponseDTO dto = ResponseDTO.errorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        } else if (e instanceof ServiceException) {
            //服务器错误
            ServiceException se = (ServiceException) e;
            ResponseDTO dto = ResponseDTO.errorMessage(se.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
        } else if (e instanceof ServletRequestBindingException) {
            //请求参数绑定错误
            ResponseDTO dto = ResponseDTO.errorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        } else if (e instanceof NotLoginException) {
            ResponseDTO dto = ResponseDTO.errorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
        } else if (className.contains("AccessDeniedException")) {
            ResponseDTO dto = ResponseDTO.errorMessage("没有访问权限");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
        } else {
            //其他未知错误
            ResponseDTO dto = ResponseDTO.errorCode(ServiceCode.UNKNOWN_FAILURE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
        }
    }
}
