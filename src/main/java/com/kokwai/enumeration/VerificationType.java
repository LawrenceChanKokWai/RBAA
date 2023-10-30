package com.kokwai.enumeration;

public enum VerificationType {

    ACCOUNT("ACCOUNT"),
    PERMISSION("PERMISSION");

    private final String type;

    VerificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type.toLowerCase();
    }



}
