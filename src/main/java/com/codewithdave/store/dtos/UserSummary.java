package com.codewithdave.store.dtos;

import com.codewithdave.store.entities.User;

public interface UserSummary {
    Long getId();

    User getUser();
}
