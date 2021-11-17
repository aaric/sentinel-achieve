package com.example.stl.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * IdName
 *
 * @author Aaric, created on 2021-11-16T14:07.
 * @version 0.5.1-SNAPSHOT
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "IdName")
public class IdName {

    @ApiModelProperty(position = 1, name = "ID", example = "1")
    private Long id;

    @ApiModelProperty(position = 2, name = "Name", example = "monkey")
    private String name;
}
