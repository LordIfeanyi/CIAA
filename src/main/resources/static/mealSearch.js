import {appendToList, fetchComplexRecipe} from "./common.js";

window.clearRadioButtons = function (name) {
    const checkedRadioButton = document.querySelector('input[name="' + name + '"]:checked');
    checkedRadioButton.checked = false;
}

window.clearCheckBoxes = function () {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = false;
    });
}

window.generateList = async function () {

    // get the div to be dynamically populated with items
    const itemList = document.getElementById("searchResults");
    itemList.innerHTML = '';

    // get the values to be appended to the endpoint
    const query = document.getElementById("query").value;
    let cuisine = null;
    let diet = null;
    let mealType = null;
    const intolerances = [];
    if (document.querySelector('input[name="cuisine"]:checked') !== null) {
        cuisine = document.querySelector('input[name="cuisine"]:checked').value;
    }
    if (document.querySelector('input[name="diet"]:checked') !== null) {
        diet = document.querySelector('input[name="diet"]:checked').value;
    }
    if (document.querySelector('input[name="type"]:checked') !== null) {
        mealType = document.querySelector('input[name="type"]:checked').value;
    }
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    checkboxes.forEach(function (checkbox) {
        intolerances.push(checkbox.value);
    });

    const request = {
        query: query,
        cuisine: cuisine,
        diet: diet,
        mealType: mealType,
        intolerances: intolerances
    }

    // fetch the items by calling the spoonacular endpoint
    const searchResults = await fetchSearchResults("ciaa/meal-search/search", request); // fetch method

    console.log("Search Results: " + searchResults["recipes"])

    const results = searchResults["recipes"];

    // if no results came back
    if (results.length === 0) {
        const noResults = document.createElement("div");
        noResults.textContent = "No results were found. Try making your search less specific.";
        itemList.appendChild(noResults);
        return;
    }

    for (const result of results) {
        const complexRecipe = await fetchComplexRecipe(result.id);
        appendToList(complexRecipe, itemList, document);
    }
}

async function fetchSearchResults(url, request) {
    console.log("Request Body:" + request);
    const response = await fetch(url, {
        body: JSON.stringify(request), method: 'POST',
        headers: {'Content-Type': 'application/json'}
    });
    return await response.json();
}
