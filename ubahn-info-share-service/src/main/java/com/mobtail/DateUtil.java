package com.mobtail;

import java.time.Instant;

public interface DateUtil {

    default Instant instantNow(){
        return Instant.now();
    }

}
