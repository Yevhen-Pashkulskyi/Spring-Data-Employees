package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnumMessage {
    SMTH_WRONG("Something wrong"),
    NO_DATA("No Data"),
    DELETED("Deleted");

    private final String resMsg;
}
