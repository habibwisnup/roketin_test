package com.habib.challenge2.model.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    @NotBlank(message = "Title is required")
    public String title;

    @NotBlank(message = "Description is required")
    public String description;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    public int duration;

    @NotBlank(message = "Artists field is required")
    public String artists;

    @NotBlank(message = "Genres field is required")
    public String genres;
}