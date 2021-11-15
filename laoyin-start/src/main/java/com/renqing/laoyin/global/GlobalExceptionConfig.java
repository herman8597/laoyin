package com.bat.laoyin.global;

import static com.bat.laoyin.api.common.ErrorCode.P_NOTNULL;

import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bat.laoyin.api.common.ErrorCode;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.common.MessageUtils;
import com.bat.laoyin.web.base.Response;

@RestControllerAdvice
public class GlobalExceptionConfig {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    /**
     * 当请求参数不为空校验失败情况
     * 
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Response bindHandler(Exception e) {
        String msg = null;
        if (e instanceof MethodArgumentNotValidException) {
            msg = MessageUtils
                .get(((MethodArgumentNotValidException)e).getBindingResult().getFieldError().getDefaultMessage());
        } else if (e instanceof BindException) {
            msg = MessageUtils.get(((BindException)e).getBindingResult().getFieldError().getDefaultMessage());
        } else {
            return Response.buildFailure(ErrorCode.SYSTEM_EXCEPTION, MessageUtils.get(ErrorCode.SYSTEM_EXCEPTION));
        }
        logger.error(msg);
        if (StringUtils.isNotBlank(msg)) {
            return Response.buildFailure(P_NOTNULL, msg);
        } else {
            return Response.buildFailure(P_NOTNULL, P_NOTNULL);
        }
    }

    /**
     * 服务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(LaoYinException.class)
    public Response laoyinHandler(LaoYinException e) {
        String errorCode = e.getCode();
        if (StringUtils.isBlank(errorCode)) {
            errorCode = ErrorCode.SYSTEM_EXCEPTION;
            return Response.buildFailure(errorCode, MessageUtils.get(errorCode));
        }
        String msg = e.getMsg();
        if (StringUtils.isNotBlank(msg)) {
            return Response.buildFailure(errorCode, msg);
        }
        msg = MessageUtils.get(errorCode);
        if (StringUtils.isNotBlank(msg)) {
            logger.error(msg);
            return Response.buildFailure(errorCode, MessageUtils.get(errorCode));
        } else {
            logger.error(errorCode);
            return Response.buildFailure(errorCode, errorCode);
        }
    }

    @ExceptionHandler(ExecutionException.class)
    public Response laoyinAsyncHandler(ExecutionException e) {
        String localizedMessage = e.getLocalizedMessage();
        String exceptionNameStr = localizedMessage.substring(0, localizedMessage.indexOf("("));
        if (!LaoYinException.class.getSimpleName().equals(exceptionNameStr)) {
            String errorCode = ErrorCode.SYSTEM_EXCEPTION;
            return Response.buildFailure(errorCode, MessageUtils.get(errorCode));
        }
        String exceptionContentStr =
            localizedMessage.substring(localizedMessage.indexOf("(") + 1, localizedMessage.indexOf(")"));
        String[] split = exceptionContentStr.split(",");
        String[] split1 = split[0].split("=");
        String[] split2 = split[1].split("=");
        String code = split1[1];
        String msg = split2[1];
        if ("null".equals(code)) {
            code = null;
        }
        if ("null".equals(msg)) {
            msg = null;
        }
        return laoyinHandler(LaoYinException.buildException(code, msg));
    }

    public static void main(String[] args) {
        String a = "LaoYinException(code=B_DISPATCH_LIST_ITEM_NOT_ENOUGH, msg=null)";
        String substring = a.substring(a.indexOf("(") + 1, a.indexOf(")"));
        String[] substring1 = substring.split(",");
        String[] split = substring1[0].split("=");
        String[] split1 = substring1[1].split("=");
        System.out.println(split[1]);
        System.out.println(split1[1]);
    }

    /**
     * 未捕获的全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(Exception e) {
        e.printStackTrace();
        return Response.buildFailure(ErrorCode.SYSTEM_EXCEPTION, MessageUtils.get(ErrorCode.SYSTEM_EXCEPTION));
    }
}
