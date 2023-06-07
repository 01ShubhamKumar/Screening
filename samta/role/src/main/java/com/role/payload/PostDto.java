package com.role.payload;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Long id;
    private String question;
    private String answer;

    private Date createdAt;
    private Date updatedAt;

}
