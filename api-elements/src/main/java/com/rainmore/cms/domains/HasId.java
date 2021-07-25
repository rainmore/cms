package com.rainmore.cms.domains;

import java.io.Serializable;

public interface HasId<ID extends Serializable> extends Domain {

    ID getId();

    void setId(ID Id);
}
