import {appendToList} from './common.js';

window.clearRadioButtons = function (name) {
    const checkedRadioButton = document.querySelector('input[name="' + name + '"]:checked');
    checkedRadioButton.checked = false;
}

window.generateList = async function () {

    // get the div to be dynamically populated with items
    const itemList = document.getElementById("searchResults");
    itemList.innerHTML = '';

    // get the values to be appended to the endpoint
    let type = null;
    if (document.querySelector('input[name="type"]:checked') !== null) {
        type = document.querySelector('input[name="type"]:checked').value; // get the type from this html page
    }

    const suggestResults = await fetchSuggestions(`/ciaa/meal-suggest/suggest`, type);

    // print the results to the console
    console.log(suggestResults);

    const results = suggestResults["recipes"];

    // if no results came back
    if (results.length === 0) {
        const noResults = document.createElement("div");
        noResults.textContent = "No suggestions were found. Try reducing the amount of intolerances you have.";
        itemList.appendChild(noResults);
        return;
    }

    for (const result of results) {
        appendToList(result, itemList, document);
    }
}

async function fetchSuggestions(url, type) {
    const response = await fetch(url, {body: type, method: 'POST'});
    return await response.json();
}