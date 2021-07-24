package com.rainmore.cms.domains.users;

import java.util.Arrays;

public enum AccountStatus {
    ACTIVE,
    SUSPENDED;

    public Boolean isActive() {
        return ACTIVE == this;
    }

    public Boolean isSuspended() {
        return SUSPENDED == this;
    }

}
