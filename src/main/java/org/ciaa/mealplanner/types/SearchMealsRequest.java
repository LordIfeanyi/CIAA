package org.ciaa.mealplanner.types;

import java.util.List;

public record SearchMealsRequest(String query, String cuisine, String diet, String mealType, List<String> intolerances) { }
