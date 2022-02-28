package com.kenjie.auth.core.enums;

public enum UserErrorCode {

    USER_100404, // GET User Not Found

    USER_100500, // GET User Unexpected Error

    USER_200422, // POST User Duplicate Error

    USER_200500, // POST User Unexpected Error

    USER_300400, // PUT User Bad Request

    USER_300500, // PUT User Unexpected Error

    USER_400400, // DELETE User Bad Request

    USER_400500, // DELETE User Unexpected Error

    USER_LIST_100400, // GET User List Bad Request

    USER_LIST_100500, // GET User List Unexpected Error
}
