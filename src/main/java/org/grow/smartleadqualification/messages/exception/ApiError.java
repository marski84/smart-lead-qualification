package org.grow.smartleadqualification.messages.exception;

import java.util.List;

record ApiError(
        String path,
        int status,
        String errorDescription,
        List<String> errors
) {
}
