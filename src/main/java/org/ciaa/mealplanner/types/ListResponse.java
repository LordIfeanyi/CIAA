package org.ciaa.mealplanner.types;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record ListResponse<T> (@SerializedName(value = "recipes", alternate = {"results"}) List<T> recipes) { }
