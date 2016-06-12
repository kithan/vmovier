package com.example.hpb.kunlun.data;

public class HttpError {
        int status;

        public String getErrMsg() {
            return errMsg;
        }

        public int getStatus() {
            return status;
        }

        String errMsg;

        public HttpError(int status,
                         String errMsg) {

            this.status = status;
            this.errMsg = errMsg;
        }

    }