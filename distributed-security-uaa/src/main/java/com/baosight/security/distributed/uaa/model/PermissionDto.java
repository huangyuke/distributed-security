package com.baosight.security.distributed.uaa.model;

import lombok.Data;

@Data
public class PermissionDto {

    private String id;
    private String code;
    private String description;
    private String url;
}
