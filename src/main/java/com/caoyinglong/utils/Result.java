package com.caoyinglong.utils;

import com.caoyinglong.statusenums.ApiStatus;
import com.caoyinglong.statusenums.Status;

import java.io.Serializable;
import java.util.Objects;

public class Result<T> implements Serializable {
    private int status;
    private String message;
    private T data;
    private Long timestamp;

    public Result() {
        this.status = Status.SUCCESS.getStatus();
        this.message = Status.SUCCESS.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

    public Result(IStatus status) {
        this();
        this.status = status.getStatus();
        this.message = status.getMessage();
    }

    public Result(int status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T data) {
        this();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result(IStatus status,T data) {
        this();
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.data = data;
    }

    public Result(T data) {
        this();
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<T>();
    }

    public static <T> Result<T> success(IStatus status) {
        return new Result<T>(status);
    }

    public static <T> Result<T> success(T t) {
        return new Result<T>(t);
    }

    public static <T> Result<T> error() {
        return new Result<T>(ApiStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> Result<T> error(IStatus status) {
        return new Result<T>(status);
    }

    public static <T> Result<T> error(IStatus status,T data) {
        return new Result<T>(status,data);
    }

    public static <T> Boolean ok(Result<T> result) {
        return Objects.nonNull(result) && result.getStatus() == ApiStatus.SUCCESS.getStatus();
    }
    public static <T> Boolean fail(Result<T> result) {
        return Objects.nonNull(result) && result.getStatus() == ApiStatus.INTERNAL_SERVER_ERROR.getStatus();
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public Result<T> setStatus(final int status) {
        this.status = status;
        return this;
    }

    public Result<T> setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Result<T> setData(final T data) {
        this.data = data;
        return this;
    }

    public Result<T> setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String toString() {
        return "Result(status=" + this.getStatus() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", timestamp=" + this.getTimestamp() + ")";
    }
}
