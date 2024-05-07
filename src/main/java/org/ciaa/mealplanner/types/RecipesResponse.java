package org.ciaa.mealplanner.types;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record RecipesResponse(@SerializedName(value = "recipes", alternate = {"results"}) List<Recipe> recipes) { }
